package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnection.Connection_Dist;
import dbconnection.PreparedStatements;

public class Postinterest_Utils {

	private final String SELECT_POSTINTERESTbyPOST = "SELECT * FROM postinterest WHERE postid = ?";
	private final String SELECT_POSTINTERESTbyUSER = "SELECT * FROM postinterest WHERE userid = ?";
	private final String SELECT_POSTINTEREST = "SELECT * FROM postinterest WHERE postid = ? and userid = ?";
	private final String INSERT_POSTINTEREST = "INSERT INTO postinterest(postid,userid) VALUES(?,?)";
	private final String DELETE_POSTINTEREST = "DELETE FROM postinterest WHERE userid = ?";
	
	public String getSELECT_POSTINTERESTbyPOST()
	{
		return this.SELECT_POSTINTERESTbyPOST;
	}

	public String getSELECT_POSTINTERESTbyUSER()
	{
		return this.SELECT_POSTINTERESTbyUSER;
	}
	
	public String getSELECT_POSTINTEREST()
	{
		return this.SELECT_POSTINTEREST;
	}
	
	public String getINSERT_POSTINTEREST()
	{
		return this.INSERT_POSTINTEREST;
	}
	
	public String getDELETE_POSTINTEREST()
	{
		return this.DELETE_POSTINTEREST;
	}
	
	public List <Postinterest> Get_PostInterest(Connection_Dist conn_d, int postid)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		User_Utils u_u = new User_Utils();
		ResultSet resultSet;
		
		List <Postinterest> interests = new ArrayList<Postinterest>();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_POSTINTERESTbyPOST(), false, postid))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
					Postinterest interest = new Postinterest();
					interest.setIdpostinterest(resultSet.getInt("idpostinterest"));
					interest.setUser(u_u.Get_UserbyId(conn_d, resultSet.getInt("userid")));
					interests.add(interest);
			}
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		return interests;
	}
	
	public List <Integer> Get_InttoUser(Connection_Dist conn_d, List <Post> posts)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		int i,postid;
		List <Integer> interests = new ArrayList<Integer>();
		
		for(i = 0; i < posts.size(); i++)
		{	
			postid = posts.get(i).getIdposts();
			try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_POSTINTERESTbyPOST(), false, postid))
			{
				resultSet = statement.executeQuery();
				while (resultSet.next()) 
				{
						int interest;
						interest = resultSet.getInt("userid");
						interests.add(interest);
				}
			}
			catch(SQLException se)
			{
				System.err.println(se.getMessage());
			}
		}
		return interests;
	}
	
	public List <Integer> Get_FrientInterPostid(Connection_Dist conn_d, List <Integer> friends)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		int i;
		List <Integer> interests = new ArrayList<Integer>();
		
		for(i = 0; i < friends.size(); i++)
		{	
			try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_POSTINTERESTbyUSER(), false, friends.get(i)))
			{
				resultSet = statement.executeQuery();
				while (resultSet.next()) 
				{
						int interest;
						interest = resultSet.getInt("postid");
						interests.add(interest);
				}
			}
			catch(SQLException se)
			{
				System.err.println(se.getMessage());
			}
		}
		return interests;
	}

	public boolean CheckFor_Interest(Connection_Dist conn_d, int postid, int userid)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		Object[] fields = new Object[] {postid,userid};
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_POSTINTEREST(), false, fields))
		{
			resultSet = statement.executeQuery();
			if (!resultSet.isBeforeFirst() ) {    
			    return false; 
			}
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return true;
	}
	
	public void Insert_Interest(Connection_Dist conn_d, int postid, int userid)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		Object[] fields = new Object[] {postid,userid};
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getINSERT_POSTINTEREST(), true, fields))
		{
			statement.executeUpdate();
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return;
	}

	public void Delete_UserInterest(Connection_Dist conn_d, int userid)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getDELETE_POSTINTEREST(), false, userid))
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
