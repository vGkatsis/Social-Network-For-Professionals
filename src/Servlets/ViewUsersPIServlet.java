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
import model.User;
import model.Pinfo;
import model.PInfo_Utils;

/**
 * Servlet implementation class ViewUsersPIServlet
 */
@WebServlet("/ViewUsersPIServlet")
public class ViewUsersPIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewUsersPIServlet() {
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
		
		Pinfo viewpi = new Pinfo();
		PInfo_Utils p_u = new PInfo_Utils();

		int viewuserid = Integer.parseInt(request.getParameter("viewuserid"));
		
		if(viewuserid == 0)
		{
			User user = (User) session.getAttribute("user");
			viewuserid = user.getIdusers();
		}
		session.setAttribute("viewuserid", viewuserid);
		
		viewpi = p_u.Get_User_Pinfo(conn_d,viewuserid);
		
		session.setAttribute("viewpi", viewpi);
		
		conn_d.Close_Connection();
		
		rdip = request.getRequestDispatcher("PersonalInfoPage.jsp");
		rdip.forward(request, response);
		
	}

}
