package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnection.Connection_Dist;
import dbconnection.PreparedStatements;

public class Postcomment_Utils {

	private final String SELECT_POSTCOMMENTS = "SELECT * FROM postcomment WHERE postid = ?";
	private final String SELECT_USERCOMMENTS = "SELECT * FROM postcomment WHERE userid = ?";
	private final String SELECT_USERPOSTCOMMENTS = "SELECT * FROM postcomment WHERE postid = ? AND userid = ?";
	private final String INSERT_POSTCOMMENT = "INSERT INTO postcomment(postid,userid,comment) VALUES (?,?,?)";
	private final String DELETE_POSTCOMMENT = "DELETE FROM postcomment WHERE userid = ?";
	
	public String getSELECT_POSTCOMMENTS()
	{
		return this.SELECT_POSTCOMMENTS;
	}

	public String getSELECT_USERCOMMENTS()
	{
		return this.SELECT_USERCOMMENTS;
	}
	
	public String getSELECT_USERPOSTCOMMENTS()
	{
		return this.SELECT_USERPOSTCOMMENTS;
	}
	
	public String getINSERT_POSTCOMMENT()
	{
		return this.INSERT_POSTCOMMENT;
	}
	
	public String getDELETE_POSTCOMMENT()
	{
		return this.DELETE_POSTCOMMENT;
	}
	
	public Object[] GetPostcomment_Fields(Postcomment postcom) {
        Object[] fields = new Object[]{postcom.getPost().getIdposts(),postcom.getUser().getIdusers(),postcom.getComment()};
        return fields;
    }
	
	public List <Postcomment> Get_PostComments(Connection_Dist conn_d, int postid)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		User_Utils u_u = new User_Utils();
		ResultSet resultSet;
		
		List <Postcomment> comments = new ArrayList<Postcomment>();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_POSTCOMMENTS(), false, postid))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
					Postcomment comment = new Postcomment();
					comment.setIdpostcomment(resultSet.getInt("idpostcomment"));
					comment.setComment(resultSet.getString("comment"));
					comment.setUser(u_u.Get_UserbyId(conn_d, resultSet.getInt("userid")));
					comments.add(comment);
			}
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		return comments;
	}
	
	public int Get_UserCommentsMult(Connection_Dist conn_d, int userid)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		int comments = 0;
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_USERCOMMENTS(), false, userid))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
				comments++;
			}
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		return comments;
	}
	
	public int Get_UserPostCommentsMult(Connection_Dist conn_d, int postid, int userid)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		Object[] fields = new Object[] {postid,userid};
		
		int comments = 0;
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_USERPOSTCOMMENTS(), false, fields))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
				comments++;
			}
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		return comments;
	}
	
	public int Get_UserCommentedPostsMult(Connection_Dist conn_d, int userid)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		List <Integer> postlist = new ArrayList <Integer>();
		int posts = 0;
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_USERCOMMENTS(), false, userid))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
				if(!postlist.contains(resultSet.getInt("postid")))
				{
					postlist.add(resultSet.getInt("postid"));
					posts++;
				}
			}
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		return posts;
	}
	
	public List <Integer> Get_ComtoUser(Connection_Dist conn_d, List <Post> posts)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		int i,postid;
		List <Integer> comments = new ArrayList<Integer>();
		
		for(i = 0; i < posts.size(); i++)
		{	
			postid = posts.get(i).getIdposts();
			try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_POSTCOMMENTS(), false, postid))
			{
				resultSet = statement.executeQuery();
				while (resultSet.next()) 
				{
						int comment;
						comment = resultSet.getInt("userid");
						comments.add(comment);
				}
			}
			catch(SQLException se)
			{
				System.err.println(se.getMessage());
			}
		}
		return comments;
	}

	public void Insert_Comment(Connection_Dist conn_d, Postcomment postcom)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		Object[] fields = GetPostcomment_Fields(postcom);
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getINSERT_POSTCOMMENT(), true, fields))
		{
			statement.executeUpdate();
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return;
	}

	public void Delete_UserComment(Connection_Dist conn_d, int userid)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getDELETE_POSTCOMMENT(), false, userid))
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
