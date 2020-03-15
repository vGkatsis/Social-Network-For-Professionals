package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnection.Connection_Dist;
import java.util.List;
import model.User_Utils;
import model.Post_Utils;
import model.Postcomment_Utils;
import model.Postinterest_Utils;
import model.Relation_Utils;
import model.User;

/**
 * Servlet implementation class NotificationsServlet
 */
@WebServlet("/NotificationsServlet")
public class NotificationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificationsServlet() {
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
		
		List <User> Senders,Commenters,Interested;
		List <Integer> Comments;
		List <Integer> Interests;
		List <Integer> Requests;
		
		User user = new User();
		User_Utils u_u = new User_Utils();
		Post_Utils p_u = new Post_Utils();
		Postcomment_Utils pc_u = new Postcomment_Utils();
		Postinterest_Utils pi_u = new Postinterest_Utils();
		Relation_Utils r_u = new Relation_Utils();
	
		user = (User) session.getAttribute("user");
		
		user.setPosts(p_u.Get_UserPosts(conn_d,user.getIdusers()));
		Comments= pc_u.Get_ComtoUser(conn_d,user.getPosts());
		Interests = pi_u.Get_InttoUser(conn_d,user.getPosts());
		
		Commenters = u_u.Get_UsersbyId(conn_d,Comments);
		Interested = u_u.Get_UsersbyId(conn_d,Interests);
		
		Requests = r_u.Get_Requests(conn_d,user.getIdusers());

		Senders = u_u.Get_UsersbyId(conn_d,Requests);
		
		session.setAttribute("interested", Interested);
		session.setAttribute("commenters", Commenters);
		session.setAttribute("senders", Senders);
		
		conn_d.Close_Connection();
		
		rdip = request.getRequestDispatcher("NotificationsPage.jsp");
		rdip.forward(request, response);
	}

}
