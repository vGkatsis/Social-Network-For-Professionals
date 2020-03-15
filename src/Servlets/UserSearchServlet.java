package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnection.Connection_Dist;
import model.User;
import model.User_Utils;

/**
 * Servlet implementation class UserSearchServlet
 */
@WebServlet("/UserSearchServlet")
public class UserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		RequestDispatcher rdip;
		
		String name;
		Connection_Dist conn_d = new Connection_Dist();
		HttpSession session = request.getSession();
		
		User_Utils u_u = new User_Utils();
		List <User> users;
		
		name = request.getParameter("usersearch");
		
		users = u_u.Get_Users_byName(conn_d,name,"user");

		session.setAttribute("searchuserslist", users);
		
		conn_d.Close_Connection();

	    rdip = request.getRequestDispatcher("NetworkPage.jsp");
	    rdip.forward(request, response);
	}

}
