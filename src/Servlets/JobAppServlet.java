package Servlets;

import java.io.IOException;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnection.Connection_Dist;
import model.Jobap_Utils;
import model.Jobap;
import model.User;
import model.User_Utils;
import model.Relation_Utils;
import model.Jobapplication_Utils;

import k_n_n.*;
/**
 * Servlet implementation class JobAppServlet
 */
@WebServlet("/JobAppServlet")
public class JobAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobAppServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rdip;
		HttpSession session = request.getSession();
		Connection_Dist conn_d = new Connection_Dist();
	
		User user = (User) session.getAttribute("user");
		
		List <Jobap> jobap;
		List <Integer> friends;
		
		Jobap_Utils j_u = new Jobap_Utils();
		Relation_Utils r_u = new Relation_Utils();
		String action = request.getParameter("action");
		
		if(Objects.equals(action, "net"))
		{
			friends = r_u.Get_Friends(conn_d, user.getIdusers());
			jobap = j_u.Get_FriendsJobaps(conn_d,friends);
		}
		else if(Objects.equals(action, "recommended"))
		{
			User_Utils u_u = new User_Utils();
			Popularity_N_N pnn = new Popularity_N_N();
			JobAppSimilarity_N_N jnn = new JobAppSimilarity_N_N();
			
			List<Integer> jobapid_list = new ArrayList<Integer>();
			List<User> users = new ArrayList<User>();
			List<Integer> users_ids = new ArrayList<Integer>();
			List<Id_Rank> rec_users = new ArrayList<Id_Rank>();
			List<Id_Rank> rec_apps = new ArrayList<Id_Rank>();

			users = u_u.Get_Users(conn_d, "user");
			for(int j = 0; j < users.size(); j++)
			{
				if((!r_u.CheckIfFriend(conn_d,users.get(j).getIdusers(),user.getIdusers())) && (users.get(j).getIdusers() != user.getIdusers()))
				{
					users_ids.add(users.get(j).getIdusers());
				}
			}
			
			friends = r_u.Get_Friends(conn_d, user.getIdusers());
			
			if((rec_users = pnn.popularity_nn(5, "ads", users_ids, friends)) != null)
			{
				if((rec_apps = jnn.appsimilarity_nn(5, rec_users, user.getIdusers())) != null)
				{
					for(int i = 0; i < rec_apps.size() ; i++)
					{
						jobapid_list.add(rec_apps.get(i).getId());
					}
			
					jobap = j_u.Get_Jobaps(conn_d, jobapid_list);
				}
				else
				{
					jobap = new ArrayList<Jobap>();
				}
			}
			else
			{
				jobap = new ArrayList<Jobap>();
			}
		}
		else
		{
			jobap = j_u.Get_UserJobaps(conn_d,user.getIdusers());
		}
		
		session.setAttribute("jobapplications", jobap);
		
		conn_d.Close_Connection();
		
		rdip = request.getRequestDispatcher("AdsPage.jsp");
		rdip.forward(request, response);
	}
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Connection_Dist conn_d = new Connection_Dist();
		
		User  user = new User();
		
		String action = request.getParameter("action");
		user  = (User) session.getAttribute("user");

		if(Objects.equals(action, "jobap"))
		{	
			Jobap jobap = new Jobap(); 
			Jobap_Utils j_u = new Jobap_Utils();
	
			jobap.setJobname(request.getParameter("nameofad"));
			jobap.setJobposition(request.getParameter("position"));
			jobap.setJobcompany(request.getParameter("company"));
			jobap.setJobincome(Integer.parseInt(request.getParameter("income")));
			jobap.setJobdescription(request.getParameter("description"));		
	
			jobap.setUser(user);
			
			j_u.Insert_Jobap(conn_d,jobap);
		}
		else
		{
			int jobapid = Integer.parseInt(request.getParameter("jobapid"));
			
			Jobapplication_Utils ja_u = new Jobapplication_Utils();
			
			if(!ja_u.CheckFor_Application(conn_d, jobapid, user.getIdusers()))
			{
				ja_u.Insert_Jobapplication(conn_d,jobapid,user.getIdusers());
			}
		}
		
		conn_d.Close_Connection();
				
		response.sendRedirect("JobAppServlet?action=my");
	}

}
