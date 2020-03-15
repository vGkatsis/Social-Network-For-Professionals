package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnection.Connection_Dist;
import dbconnection.PreparedStatements;

public class Post_Utils {
	
	private final String SELECT_USERPOSTS = "SELECT * FROM posts WHERE userid = ?";
	private final String SELECT_POSTS = "SELECT * FROM posts WHERE idposts = ?";
	private final String INSERT_POST = "INSERT INTO posts(text,userid) VALUES (?,?)";
	private final String DELETE_POSTS = "DELETE FROM posts WHERE userid = ?";

	public String getSELECT_USERPOSTS()
	{
		return this.SELECT_USERPOSTS;
	}
	
	public String getSELECT_POSTS()
	{
		return this.SELECT_POSTS;
	}
	
	public String getINSERT_POST()
	{
		return this.INSERT_POST;
	}
	
	public String getDELETE_POSTS()
	{
		return this.DELETE_POSTS;
	}
	
	public List <Post> Get_UserPosts(Connection_Dist conn_d, int userid)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		User_Utils u_u = new User_Utils();
		Postinterest_Utils pi_u = new Postinterest_Utils();
		Postcomment_Utils pc_u = new Postcomment_Utils();
		ResultSet resultSet;
		
		List <Post> posts = new ArrayList<Post>();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_USERPOSTS(), false, userid))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
					Post post = new Post();
					post.setUser(u_u.Get_UserbyId(conn_d, resultSet.getInt("userid")));
					post.setIdposts(resultSet.getInt("idposts"));
					post.setText(resultSet.getString("text"));
					post.setPostcomments(pc_u.Get_PostComments(conn_d,post.getIdposts()));
					post.setPostinterests(pi_u.Get_PostInterest(conn_d,post.getIdposts()));
					posts.add(post);
			}
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return posts;
	}
	
	public List <Post> Get_UsersPosts(Connection_Dist conn_d, List <Integer> userids)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		User_Utils u_u = new User_Utils();
		Postinterest_Utils pi_u = new Postinterest_Utils();
		Postcomment_Utils pc_u = new Postcomment_Utils();
		ResultSet resultSet;
		
		List <Post> posts = new ArrayList<Post>();
		
		for(int i = 0; i < userids.size(); i++)	
		{	
			try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_USERPOSTS(), false, userids.get(i)))
			{
				resultSet = statement.executeQuery();
				while (resultSet.next()) 
				{
						Post post = new Post();
						post.setUser(u_u.Get_UserbyId(conn_d, userids.get(i)));
						post.setIdposts(resultSet.getInt("idposts"));
						post.setText(resultSet.getString("text"));
						post.setPostcomments(pc_u.Get_PostComments(conn_d,post.getIdposts()));
						post.setPostinterests(pi_u.Get_PostInterest(conn_d,post.getIdposts()));
						posts.add(post);
				}
			}
			catch(SQLException se)
			{
				System.err.println(se.getMessage());
			}
		}
		return posts;
	}
	
	public List <Post> Get_FriendInterPosts(Connection_Dist conn_d, List <Integer> postids, List <Integer> friendsids)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		User_Utils u_u = new User_Utils();
		Postinterest_Utils pi_u = new Postinterest_Utils();
		Postcomment_Utils pc_u = new Postcomment_Utils();
		ResultSet resultSet;
		
		List <Post> posts = new ArrayList<Post>();
		
		for(int i = 0; i < postids.size(); i++)	
		{	
			try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_POSTS(), false, postids.get(i)))
			{
				resultSet = statement.executeQuery();
				while (resultSet.next()) 
				{
						if(!friendsids.contains(resultSet.getInt("userid")));
						{
							Post post = new Post();
							post.setUser(u_u.Get_UserbyId(conn_d,resultSet.getInt("userid")));
							post.setIdposts(resultSet.getInt("idposts"));
							post.setText(resultSet.getString("text"));
							post.setPostcomments(pc_u.Get_PostComments(conn_d,post.getIdposts()));
							post.setPostinterests(pi_u.Get_PostInterest(conn_d,post.getIdposts()));
							posts.add(post);
						}
				}
			}
			catch(SQLException se)
			{
				System.err.println(se.getMessage());
			}
		}
		return posts;
	}
	
	public void Insert_Post(Connection_Dist conn_d, Post post)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		Object[] fields = new Object[] {post.getText(),post.getUser().getIdusers()};
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getINSERT_POST(), true, fields))
		{
			statement.executeUpdate();
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return;
	}

	public void Delete_UserPost(Connection_Dist conn_d, int userid)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getDELETE_POSTS(), false, userid))
		{
			statement.executeUpdate();
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return;
	}
}
