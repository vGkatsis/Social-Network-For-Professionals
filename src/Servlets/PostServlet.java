package Servlets;

import java.io.IOException;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.base.Objects;

import dbconnection.Connection_Dist;
import k_n_n.*;
import model.Post;
import model.Postcomment;
import model.Postcomment_Utils;
import model.User_Utils;
import model.Relation_Utils;
import model.Post_Utils;
import model.Postinterest_Utils;
import model.User;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServlet() {
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
	
		List <Post> posts;
		List <Post> interposts;
		List <User> users;
		List <Integer> friends;
		List <Integer> friend_inter_postid;
		List <Integer> users_ids = new ArrayList<Integer>();
		List<Id_Rank> rec_users = new ArrayList<Id_Rank>();
		
		User user = new User();
		User_Utils u_u = new User_Utils();
		Relation_Utils r_u = new Relation_Utils();
		Postinterest_Utils pi_u = new Postinterest_Utils();
		Post_Utils p_u = new Post_Utils();
		Popularity_N_N pnn = new Popularity_N_N();
		
		user = (User) session.getAttribute("user");
		
		friends = r_u.Get_Friends(conn_d, user.getIdusers());
		friends.add(user.getIdusers());
		
		posts = p_u.Get_UsersPosts(conn_d,friends);

		users = u_u.Get_Users(conn_d, "user");
		for(int j = 0; j < users.size(); j++)
		{
			if((!r_u.CheckIfFriend(conn_d,users.get(j).getIdusers(),user.getIdusers())) && (users.get(j).getIdusers() != user.getIdusers()))
			{
				users_ids.add(users.get(j).getIdusers());
			}
		}
		
		if((rec_users = pnn.popularity_nn(5, "posts", users_ids, friends)) != null)
		{
			for(int i = 0; i < rec_users.size() ; i++)
			{
				posts.addAll(p_u.Get_UserPosts(conn_d, rec_users.get(i).getId()));
			}
		}
	
		friend_inter_postid = pi_u.Get_FrientInterPostid(conn_d,friends);
		interposts = p_u.Get_FriendInterPosts(conn_d,friend_inter_postid,friends);
		
		for(int i = 0; i < interposts.size(); i++)
		{
			if(!posts.contains(interposts.get(i)))
			{
				posts.add(interposts.get(i));
			}
		}
		
		Collections.sort(posts, new Comparator<Post>() {
			  public int compare(Post p1, Post p2) {
			      if (p1.getPostdate() == null || p2.getPostdate() == null)
			      {  
			    	  return 0;
			      }
			      return p1.getPostdate().compareTo(p2.getPostdate());
			  }
			});
		
		Collections.reverse(posts);
		
		session.setAttribute("Postlist", posts);
		
		conn_d.Close_Connection();
		
		rdip = request.getRequestDispatcher("NetworkServlet?action=side");
		rdip.forward(request, response);
	}
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Connection_Dist conn_d = new Connection_Dist();
		
		User  user = new User();
		String action = request.getParameter("action");
		
		user  = (User) session.getAttribute("user");
		
		if(Objects.equal(action, "post"))
		{
			Post post = new Post(); 
			Post_Utils p_u = new Post_Utils();
					
			post.setText(request.getParameter("post"));
	
			post.setUser(user);
			
			p_u.Insert_Post(conn_d,post);
		}
		else if(Objects.equal(action, "interest"))
		{
			int postid = Integer.parseInt(request.getParameter("postid"));
			
			Postinterest_Utils pi_u = new Postinterest_Utils();
			
			if(!pi_u.CheckFor_Interest(conn_d,postid,user.getIdusers()))
			{
				pi_u.Insert_Interest(conn_d,postid,user.getIdusers());
			}
		}
		else
		{
			int postid = Integer.parseInt(request.getParameter("postid"));			
			String comment = request.getParameter("comment");
		
			Postcomment postcom = new Postcomment();
			Postcomment_Utils pc_u = new Postcomment_Utils();
		
			postcom.setComment(comment);
			postcom.setPost(new Post());
			postcom.getPost().setIdposts(postid);
			postcom.setUser(new User());
			postcom.getUser().setIdusers(user.getIdusers());
		
			pc_u.Insert_Comment(conn_d,postcom);
		}
		
		conn_d.Close_Connection();
				
		response.sendRedirect("PostServlet");
	}

}
