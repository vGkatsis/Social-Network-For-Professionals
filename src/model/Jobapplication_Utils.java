package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnection.Connection_Dist;
import dbconnection.PreparedStatements;

public class Jobapplication_Utils {

	private final String SELECT_JOBAPPLICATIONS= "SELECT * FROM jobapplication WHERE jobapid = ?";
	private final String SELECT_USERJOBAPPLICATIONS = "SELECT * FROM jobapplication WHERE jobapid = ? AND userid = ?";
	private final String INSERT_JOBAPPLICATION = "INSERT INTO jobapplication(jobapid,userid) VALUES(?,?)";
	private final String DELETE_JOBAPPLICATION = "DELETE FROM jobapplication WHERE userid = ?";
	
	public String getSELECT_JOBAPPLICATIONS()
	{
		return this.SELECT_JOBAPPLICATIONS;
	}

	public String getSELECT_USERJOBAPPLICATIONS()
	{
		return this.SELECT_USERJOBAPPLICATIONS;
	}
	
	public String getINSERT_JOBAPPLICATION()
	{
		return this.INSERT_JOBAPPLICATION;
	}
	
	public String getDELETE_JOBAPPLICATION()
	{
		return this.DELETE_JOBAPPLICATION;
	}
	
	public List <Jobapplication> GetJobapplications(Connection_Dist conn_d, int jobapid)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		User_Utils u_u = new User_Utils();

		List <Jobapplication> jobaps = new ArrayList<Jobapplication>();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_JOBAPPLICATIONS(), false, jobapid))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
				Jobapplication jobap = new Jobapplication();
				jobap.setUser(u_u.Get_UserbyId(conn_d, resultSet.getInt("userid")));
				jobap.setIdjobapplication(resultSet.getInt("idjobapplication"));
				jobaps.add(jobap);
			}
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return jobaps;
	}

	public boolean CheckFor_Application(Connection_Dist conn_d, int jobapid, int userid)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		Object[] fields = new Object[] {jobapid,userid};
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_USERJOBAPPLICATIONS(), false, fields))
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
	
	public void Insert_Jobapplication(Connection_Dist conn_d, int jobapid, int userid)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		Object[] fields = new Object[] {jobapid,userid};
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getINSERT_JOBAPPLICATION(), true, fields))
		{
			statement.executeUpdate();
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return;
	}

	public void Delete_UserJobapplication(Connection_Dist conn_d, int userid)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getDELETE_JOBAPPLICATION(), false, userid))
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
