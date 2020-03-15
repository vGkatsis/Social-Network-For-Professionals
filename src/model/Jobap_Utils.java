package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnection.Connection_Dist;
import dbconnection.PreparedStatements;

public class Jobap_Utils {
	
	private final String SELECT_USERJOBAP = "SELECT * FROM jobap WHERE userid = ?";
	private final String SELECT_JOBAP = "SELECT * FROM jobap WHERE idjobap = ?";
	private final String INSERT_JOBAP = "INSERT INTO jobap(jobname,jobposition,jobcompany,jobincome,jobdescription,userid) VALUES (?,?,?,?,?,?)";
	private final String DELETE_JOBAP = "DELETE FROM jobap WHERE userid = ?";
	
	public String getSELECT_USERJOBAP()
	{
		return this.SELECT_USERJOBAP;
	}
	
	public String getSELECT_JOBAP()
	{
		return this.SELECT_JOBAP;
	}
	
	public String getINSERT_JOBAP()
	{
		return this.INSERT_JOBAP;
	}
	
	public String getDELETE_JOBAP()
	{
		return this.DELETE_JOBAP;
	}
	
	public Object[] GetJobap_Fields(Jobap jobap) {
        Object[] fields = new Object[]{jobap.getJobname(),jobap.getJobposition(),jobap.getJobcompany(),jobap.getJobincome(),jobap.getJobdescription(),jobap.getUser().getIdusers()};
        return fields;
    }
	
	public List <Jobap> Get_UserJobaps(Connection_Dist conn_d, int userid)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		User_Utils u_u = new User_Utils();
		Jobapplication_Utils ja_u = new Jobapplication_Utils();
		List <Jobap> jobaps = new ArrayList<Jobap>();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_USERJOBAP(), false, userid))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
				Jobap jobap = new Jobap();
				jobap.setUser(u_u.Get_UserbyId(conn_d, userid));
				jobap.setIdjobap(resultSet.getInt("idjobap"));
				jobap.setJobname(resultSet.getString("jobname"));
				jobap.setJobposition(resultSet.getString("jobposition"));
				jobap.setJobcompany(resultSet.getString("jobcompany"));
				jobap.setJobincome(resultSet.getInt("jobincome"));
				jobap.setJobdescription(resultSet.getString("jobdescription"));
				jobap.setJobapplications(ja_u.GetJobapplications(conn_d,jobap.getIdjobap()));
				jobaps.add(jobap);
			}
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return jobaps;
	}
	
	public List <Jobap> Get_FriendsJobaps(Connection_Dist conn_d, List <Integer> friendsid)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		User_Utils u_u = new User_Utils();
		Jobapplication_Utils ja_u = new Jobapplication_Utils();
		List <Jobap> jobaps = new ArrayList<Jobap>();
		
		for(int i = 0; i < friendsid.size(); i++)
		{
			try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_USERJOBAP(), false, friendsid.get(i)))
			{
				resultSet = statement.executeQuery();
				while (resultSet.next()) 
				{
					Jobap jobap = new Jobap();
					jobap.setUser(u_u.Get_UserbyId(conn_d, friendsid.get(i)));
					jobap.setIdjobap(resultSet.getInt("idjobap"));
					jobap.setJobname(resultSet.getString("jobname"));
					jobap.setJobposition(resultSet.getString("jobposition"));
					jobap.setJobcompany(resultSet.getString("jobcompany"));
					jobap.setJobincome(resultSet.getInt("jobincome"));
					jobap.setJobdescription(resultSet.getString("jobdescription"));
					jobap.setJobapplications(ja_u.GetJobapplications(conn_d,jobap.getIdjobap()));
					jobaps.add(jobap);
				}
			}
			catch(SQLException se)
			{
				System.err.println(se.getMessage());
			}
		}
		
		return jobaps;
	}
	
	public List <Jobap> Get_Jobaps(Connection_Dist conn_d, List<Integer> jobaplist)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		User_Utils u_u = new User_Utils();
		Jobapplication_Utils ja_u = new Jobapplication_Utils();
		List <Jobap> jobaps = new ArrayList<Jobap>();
		
		for(int i = 0; i < jobaplist.size(); i++)
		{
			try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_JOBAP(), false, jobaplist.get(i)))
			{
				resultSet = statement.executeQuery();
				while (resultSet.next()) 
				{
					Jobap jobap = new Jobap();
					jobap.setUser(u_u.Get_UserbyId(conn_d, resultSet.getInt("userid")));
					jobap.setIdjobap(resultSet.getInt("idjobap"));
					jobap.setJobname(resultSet.getString("jobname"));
					jobap.setJobposition(resultSet.getString("jobposition"));
					jobap.setJobcompany(resultSet.getString("jobcompany"));
					jobap.setJobincome(resultSet.getInt("jobincome"));
					jobap.setJobdescription(resultSet.getString("jobdescription"));
					jobap.setJobapplications(ja_u.GetJobapplications(conn_d,jobap.getIdjobap()));
					jobaps.add(jobap);
				}
			}
			catch(SQLException se)
			{
				System.err.println(se.getMessage());
			}
		}
		
		return jobaps;
	}
	
	public void Insert_Jobap(Connection_Dist conn_d, Jobap jobap)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		Object[] fields = GetJobap_Fields(jobap);
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getINSERT_JOBAP(), true, fields))
		{
			statement.executeUpdate();
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return;
	}
	
	
	public void Delete_Jobap(Connection_Dist conn_d, int userid)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getDELETE_JOBAP(), false, userid))
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
