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
 * Servlet implementation class PersonalInfoServlet
 */
@WebServlet("/PersonalInfoServlet")
public class PersonalInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Connection_Dist conn_d = new Connection_Dist();
		
		Pinfo pinfo = new Pinfo(); 
		User  user = new User();
		PInfo_Utils p_u = new PInfo_Utils();
		
		user = (User) session.getAttribute("user");
	
		pinfo.setSchool(request.getParameter("school"));
		pinfo.setBachelor(request.getParameter("bachelor"));
		pinfo.setMaster(request.getParameter("master"));
		pinfo.setDoctorate(request.getParameter("doctorate"));
		pinfo.setWorkspace(request.getParameter("currentworkspace"));		
		pinfo.setPosition(request.getParameter("position"));
		pinfo.setMisc(request.getParameter("misc"));
		
		pinfo.setUser(user);
		
		user.setPinfo(pinfo);
		
		p_u.Update_Pinfo(conn_d,pinfo);

		conn_d.Close_Connection();

		session.setAttribute("user",user);
				
		response.sendRedirect("ViewUsersPIServlet?viewuserid=0");
		
	}

}
