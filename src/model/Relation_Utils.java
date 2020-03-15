package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnection.Connection_Dist;
import dbconnection.PreparedStatements;

public class Relation_Utils {

	private final String SELECT_RELATION = "SELECT * FROM relations WHERE (firstuserid = ? AND seconduserid = ?) OR (firstuserid = ? AND seconduserid = ?)";
	private final String SELECT_REQUESTS = "SELECT * FROM relations WHERE seconduserid = ? AND status = 0";
	private final String SELECT_FRIENDS = "SELECT * FROM relations WHERE (firstuserid = ? OR seconduserid = ?) AND status = 1";
	private final String SELECT_FRIEND = "SELECT * FROM relations WHERE ((firstuserid = ? AND seconduserid = ?) OR (firstuserid = ? AND seconduserid = ?)) AND status = 1";
	private final String INSERT_RELATION = "INSERT INTO relations(firstuserid,seconduserid,status) VALUES (?,?,?)";
	private final String UPDATE_RELATION1 = "UPDATE relations SET status = 1 WHERE firstuserid = ? AND seconduserid = ?";
	private final String DELETE_RELATION = "DELETE FROM relations WHERE (firstuserid = ? AND seconduserid = ?) OR (firstuserid = ? AND seconduserid = ?)";
	private final String DELETE_ALLRELATIONS = "DELETE FROM relations WHERE firstuserid = ? OR seconduserid = ?";
	
	public String getSELECT_RELATION()
	{
		return this.SELECT_RELATION;
	}
	
	public String getSELECT_REQUESTS()
	{
		return this.SELECT_REQUESTS;
	}
	
	public String getSELECT_FRIENDS()
	{
		return this.SELECT_FRIENDS;
	}
	
	public String getSELECT_FRIEND()
	{
		return this.SELECT_FRIEND;
	}
	
	public String getINSERT_RELATION()
	{
		return this.INSERT_RELATION;
	}

	public String getUPDATE_RELATION1()
	{
		return this.UPDATE_RELATION1;
	}
	
	public String getDELETE_RELATION()
	{
		return this.DELETE_RELATION;
	}
	
	public String getDELETE_ALLRELATIONS()
	{
		return this.DELETE_ALLRELATIONS;
	}
	
	public Object[] GetRelation_Fields(Relation rel) {
        Object[] fields = new Object[]{ rel.getUser().getIdusers(),rel.getSeconduserid(),rel.getStatus()};
        return fields;
    }
	
	public List <Integer> Get_Requests(Connection_Dist conn_d, int userid)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		List <Integer> requests = new ArrayList<Integer>();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_REQUESTS(), false, userid))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
					int req;
					req = resultSet.getInt("firstuserid");
					requests.add(req);
			}
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return requests;
	}
	
	public List <Integer> Get_Friends(Connection_Dist conn_d, int userid)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		Object[] fields = new Object[] {userid,userid};
		List <Integer> friends = new ArrayList<Integer>();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_FRIENDS(), false, fields))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
					int friend;
					if(userid == resultSet.getInt("firstuserid"))
					{	
						friend = resultSet.getInt("seconduserid");
						friends.add(friend);
					}
					else
					{

						friend = resultSet.getInt("firstuserid");
						friends.add(friend);
					}
			}
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return friends;
	}
	
	public void Insert_Relation(Connection_Dist conn_d, Relation rel)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		Object[] fields = GetRelation_Fields(rel);
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getINSERT_RELATION(), true, fields))
		{
			statement.executeUpdate();
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return;
	}
	
	public void Make_Relation(Connection_Dist conn_d, int sender, int receiver)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		Object[] fields = new Object[] {sender,receiver};
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getUPDATE_RELATION1(), false, fields))
		{
			statement.executeUpdate();
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return;
	}
	
	public void Delete_Relation(Connection_Dist conn_d, int sender, int receiver)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		Object[] fields = new Object[] {sender,receiver,receiver,sender};
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getDELETE_RELATION(), false, fields))
		{
			statement.executeUpdate();
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return;
	}

	public void Delete_UserRelations(Connection_Dist conn_d, int userid)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		Object[] fields = new Object[] {userid,userid};
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getDELETE_ALLRELATIONS(), false, fields))
		{
			statement.executeUpdate();
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return;
	}
	
	public boolean CheckIfFriend(Connection_Dist conn_d, int friendid, int userid)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		Object[] fields = new Object[] {userid,friendid,friendid,userid};
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_FRIEND(), false, fields))
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
	
	public boolean Relation_Exists(Connection_Dist conn_d, Relation rel)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		Object[] fields = new Object[] {rel.getUser().getIdusers(),rel.getSeconduserid(),rel.getSeconduserid(),rel.getUser().getIdusers()};
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_RELATION(), false, fields))
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
}
