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
import model.Chat;
import model.Chatmessage;
import model.Chat_Utils;
import model.Chatmessage_Utils;
import model.User;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatServlet() {
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
	
		int chatid;
		List <Chatmessage> chat;
		List <Chat> chats;
		
		User user = new User();
		Chat_Utils c_u = new Chat_Utils();
		
		user = (User) session.getAttribute("user");
		
		chatid = Integer.parseInt(request.getParameter("chatid"));
		
		chats = c_u.Get_UserChats(conn_d,user.getIdusers());

		if(!chats.isEmpty())
		{
			if(chatid == 0)
			{
				chat = chats.get(0).getChatmessages();		
				session.setAttribute("curchatid", chats.get(0).getIdchats());
				session.setAttribute("chat", chat);
			}
			else if(chatid == -1)
			{
				int curchatid = (Integer) session.getAttribute("curchatid");

				int i = 0;
				while(chats.get(i).getIdchats() != curchatid)
				{
					i++;
				}
				chat = chats.get(i).getChatmessages();		
				session.setAttribute("chat", chat);
			}
			else
			{
				int i = 0;
				while(chats.get(i).getIdchats() != chatid)
				{
					i++;
				}
				chat = chats.get(i).getChatmessages();		
				session.setAttribute("curchatid",chatid);
				session.setAttribute("chat", chat);
			}
		}
		
		session.setAttribute("chatlist", chats);
		
		conn_d.Close_Connection();
		
		rdip = request.getRequestDispatcher("ChatsPage.jsp");
		rdip.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Connection_Dist conn_d = new Connection_Dist();
	
		User user = (User)session.getAttribute("user");
		
		String action = request.getParameter("action");
		
		if(Objects.equals(action, "message"))
		{
			int curchatid = (Integer)session.getAttribute("curchatid");
			String message = request.getParameter("msg");
			
			Chatmessage_Utils c_u = new Chatmessage_Utils();
			
			if(message != null)
			{
				c_u.Insert_Message(conn_d,curchatid,user.getIdusers(),message);
			}
			
			conn_d.Close_Connection();
			
			response.sendRedirect("ChatServlet?chatid=-1");
		}
		else
		{
			int seconduserid = Integer.parseInt(request.getParameter("seconduserid"));
			
			Chat_Utils c_u = new Chat_Utils();
			
			if(!c_u.CheckFor_Chat(conn_d,user.getIdusers(),seconduserid))
			{
				c_u.New_Chat(conn_d,user.getIdusers(),seconduserid);
			}
			
			response.sendRedirect("ChatServlet?chatid=0");			
		}
	}
}
