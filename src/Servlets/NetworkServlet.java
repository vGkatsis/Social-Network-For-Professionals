package Servlets;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnection.Connection_Dist;
import model.Relation_Utils;
import model.PInfo_Utils;
import model.User;
import model.Pinfo;

/**
 * Servlet implementation class NetworkServlet
 */
@WebServlet("/NetworkServlet")
public class NetworkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NetworkServlet() {
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
		
		String action = request.getParameter("action");
		
		List <Integer> friendsids;
		List <Pinfo> friendspinfo;
		
		User user = new User();
		PInfo_Utils p_u = new PInfo_Utils();
		Relation_Utils r_u = new Relation_Utils();
		
		user = (User) session.getAttribute("user");
		
		friendsids = r_u.Get_Friends(conn_d,user.getIdusers());
		
		friendspinfo = p_u.Get_Users_Pinfo(conn_d,friendsids);
		
		session.setAttribute("friendslist", friendspinfo);
		
		conn_d.Close_Connection();
		
		if(Objects.equals(action, "net"))
		{	
			rdip = request.getRequestDispatcher("NetworkPage.jsp");
			rdip.forward(request, response);
		}
			
		rdip = request.getRequestDispatcher("UserPage.jsp");
		rdip.forward(request, response);
	}


}
