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
import model.User;
import model.Relation;
import model.Chat_Utils;
import model.Chatmessage_Utils;
import model.Relation_Utils;

/**
 * Servlet implementation class FriendRequestServlet
 */
@WebServlet("/FriendRequestServlet")
public class FriendRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rdip;
		HttpSession session = request.getSession();
		Connection_Dist conn_d = new Connection_Dist();
		
		String action = request.getParameter("action");
		
		if(Objects.equals(action, "send"))
		{	
			User user = new User();
			Relation rel = new Relation();
			Relation_Utils r_u = new Relation_Utils();
			
			user = (User) session.getAttribute("user");
			
			rel.setUser(new User());
			rel.getUser().setIdusers(user.getIdusers());
			rel.setSeconduserid((int) session.getAttribute("viewuserid"));
			rel.setStatus((byte)0);
			
			if(!r_u.Relation_Exists(conn_d,rel))
			{
				r_u.Insert_Relation(conn_d,rel);
			}
		
			response.sendRedirect("NetworkServlet?action=net");
		}
		if(Objects.equals(action, "accept"))
		{
			int sender = Integer.parseInt(request.getParameter("sender"));
			int receiver = Integer.parseInt(request.getParameter("receiver"));

			Relation_Utils r_u = new Relation_Utils();

			r_u.Make_Relation(conn_d,sender,receiver);

			response.sendRedirect("NetworkServlet?action=net");
		}
		if(Objects.equals(action, "decline"))
		{
			int sender = Integer.parseInt(request.getParameter("sender"));
			int receiver = Integer.parseInt(request.getParameter("receiver"));
			int chatid;
			
			Relation_Utils r_u = new Relation_Utils();
			Chat_Utils c_u = new Chat_Utils();
			Chatmessage_Utils cm_u = new Chatmessage_Utils();
			
			r_u.Delete_Relation(conn_d,sender,receiver);
			chatid = c_u.Delete_Chat(conn_d, sender,receiver);
			cm_u.Delete_ChatMessage(conn_d,chatid);
			
			response.sendRedirect("NetworkServlet?action=net");
		}
			
		conn_d.Close_Connection();
	}

}
