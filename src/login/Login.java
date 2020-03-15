package login;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.User_Utils;
import dbconnection.Connection_Dist;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rdip;
		
		String email = request.getParameter("email");
		String pswd  = request.getParameter("password");
		
		Connection_Dist conn_d = new Connection_Dist();
		User_Utils u_u = new User_Utils();
		
		if(u_u.Check_Email_Password(conn_d, email, pswd))
		{
			User user = new User();
			
			user.setEmail(email);
			user.setPassword(pswd);
			user.setIdusers(u_u.Get_User_Id(conn_d,email));
			user.setName(u_u.Get_User_Name(conn_d,email));
			user.setSurname(u_u.Get_User_Surname(conn_d,email));
			user.setTelnumber(u_u.Get_User_Tel(conn_d,email));
			user.setImage(u_u.Get_User_Image(conn_d, email));
			user.setType(u_u.GetUserType(conn_d, email));
			
			HttpSession session = request.getSession();
			
			if(Objects.equals(user.getType(),"user"))
			{
				session.setAttribute("user",user);
				
				conn_d.Close_Connection();
				
				rdip = request.getRequestDispatcher("PostServlet");
				rdip.forward(request, response);
				return;
			}
			else
			{
				session.setAttribute("admin",user);

				conn_d.Close_Connection();

				rdip = request.getRequestDispatcher("AdminServlet");
				rdip.forward(request, response);
				return;
			}
		}

		conn_d.Close_Connection();
		
		rdip = request.getRequestDispatcher("LoginError.html");
		rdip.forward(request, response);
		return;
    }

}
