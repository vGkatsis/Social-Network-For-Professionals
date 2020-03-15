package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import dbconnection.Connection_Dist;
import model.User;
import model.Pinfo;
import model.User_Utils;
import model.Chat_Utils;
import model.Chatmessage_Utils;
import model.Jobapplication_Utils;
import model.Jobap_Utils;
import model.Postinterest_Utils;
import model.Postcomment_Utils;
import model.Post_Utils;
import model.Relation_Utils;
import model.PInfo_Utils;
/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rdip;
		
		Connection_Dist conn_d = new Connection_Dist();
		HttpSession session = request.getSession();
		
		String action = request.getParameter("action");
		
		User_Utils u_u = new User_Utils();
		PInfo_Utils p_u = new PInfo_Utils();
		List <User> users;
		List <Pinfo> pinfos;
			
		users = u_u.Get_Users(conn_d,"user");
			
		session.setAttribute("userslist", users);
		
		conn_d.Close_Connection();
	
	    rdip = request.getRequestDispatcher("AdminPage.jsp");
	    rdip.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection_Dist conn_d = new Connection_Dist();
	
		User_Utils u_u = new User_Utils();
		Chatmessage_Utils cm_u = new Chatmessage_Utils();
		Chat_Utils c_u = new Chat_Utils();
		Jobapplication_Utils ja_u = new Jobapplication_Utils();
		Jobap_Utils j_u = new Jobap_Utils();
		PInfo_Utils p_u = new PInfo_Utils();
		Postinterest_Utils pi_u = new Postinterest_Utils();
		Postcomment_Utils pc_u = new Postcomment_Utils();
		Post_Utils po_u = new Post_Utils();
		Relation_Utils r_u = new Relation_Utils();

		int userid = Integer.parseInt(request.getParameter("userid"));
		
		cm_u.Delete_UserMessage(conn_d, userid);
		c_u.Delete_UserChats(conn_d, userid);
		ja_u.Delete_UserJobapplication(conn_d, userid);
		j_u.Delete_Jobap(conn_d, userid);
		p_u.Delete_UserPinfo(conn_d, userid);
		pi_u.Delete_UserInterest(conn_d, userid);
		pc_u.Delete_UserComment(conn_d, userid);
		po_u.Delete_UserPost(conn_d, userid);
		r_u.Delete_UserRelations(conn_d, userid);
		u_u.Delete_User(conn_d, userid);
		
		conn_d.Close_Connection();
		
		response.sendRedirect("AdminServlet");
	}
}
