package Servlets;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnection.Connection_Dist;
import model.PInfo_Utils;
import model.Pinfo;
import model.User;
import model.User_Utils;

/**
 * Servlet implementation class SettingsServlet
 */
@WebServlet("/SettingsServlet")
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rdip;
		Connection_Dist conn_d = new Connection_Dist();
		
		String action = request.getParameter("action");
		
		if(Objects.equals(action, "admin"))
		{
			User user = new User();
			User_Utils u_u = new User_Utils();
			
			String email = request.getParameter("email");
			String pswd = request.getParameter("password");
			
			user.setEmail(email);
			user.setPassword(pswd);
			user.setIdusers(u_u.Get_User_Id(conn_d,email));
			user.setName(u_u.Get_User_Name(conn_d,email));
			user.setSurname(u_u.Get_User_Surname(conn_d,email));
			user.setTelnumber(u_u.Get_User_Tel(conn_d,email));
			user.setImage(u_u.Get_User_Image(conn_d, email));
			user.setType(u_u.GetUserType(conn_d, email));

			HttpSession session = request.getSession();
			session.setAttribute("user", user);
		}
		
		conn_d.Close_Connection();
		
		rdip = request.getRequestDispatcher("SettingsPage.jsp");
		rdip.forward(request, response);
		
	}
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rdip;
		HttpSession session = request.getSession();
		Connection_Dist conn_d = new Connection_Dist();
		
		String oldemail,oldpswd;
		User user = new User();
		User_Utils u_u = new User_Utils();
		
		user = (User) session.getAttribute("user");
	
		if(request.getParameter("oldpassword") != null)
		{
			oldemail = user.getEmail();
			oldpswd = user.getPassword();
			
			if(Objects.equals(request.getParameter("newpassword"),request.getParameter("newpassword2")))
			{
				if(Objects.equals(oldpswd,request.getParameter("oldpassword")))
				{	
					user.setEmail(request.getParameter("newemail"));
					user.setName(request.getParameter("newname"));
					user.setSurname(request.getParameter("newsurname"));
					user.setPassword(request.getParameter("newpassword"));
					if(request.getParameter("image") == null)
					{
						user.setImage("Green Space Invader.png");
					}
					else
					{
						user.setImage(request.getParameter("image"));				
					}
					user.setType("user");
					
					u_u.Update_User(conn_d,user,oldemail);
	
					conn_d.Close_Connection();
	
					session.setAttribute("user",user);
					
					rdip = request.getRequestDispatcher("SettingsPage.jsp");
					rdip.forward(request, response);
				}
	
			}
		}
		else
		{
			return;
		}
		conn_d.Close_Connection();
		
		rdip = request.getRequestDispatcher("SettingsPage.jsp");
		rdip.forward(request, response);
		
	}

}
