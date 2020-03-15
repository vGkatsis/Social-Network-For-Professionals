package model;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.Connection_Dist;
import dbconnection.PreparedStatements;

public class PInfo_Utils {

	private final String SELECT_USERID = "SELECT * FROM pinfo WHERE userid = ?";
	private final String UPDATE_PINFO = "UPDATE pinfo SET school = ?, bachelor = ?, master = ?, doctorate = ?, workspace = ?, position = ?, misc = ? WHERE userid = ?";
	private final String INSERT_PINFO = "INSERT INTO pinfo(school,bachelor,master,doctorate,workspace,position,misc,userid) VALUES (?,?,?,?,?,?,?,?)";
	private final String DELETE_PINFO = "DELETE FROM pinfo WHERE userid = ?";
	
	public String getUPDATE_PINFO()
	{
		return this.UPDATE_PINFO;
	}
	
	public String getINSERT_PINFO()
	{
		return this.INSERT_PINFO;
	}
	
	public String getSELECT_USERID()
	{
		return this.SELECT_USERID;
	}
	
	public String getDELETE_PINFO()
	{
		return this.DELETE_PINFO;
	}
	
	public Object[] GetPinfo_Fields(Pinfo pinfo) {
        Object[] fields = new Object[]{ pinfo.getSchool(),pinfo.getBachelor(),pinfo.getMaster(),pinfo.getDoctorate(),pinfo.getWorkspace(),pinfo.getPosition(),pinfo.getMisc(),pinfo.getUser().getIdusers()};
        return fields;
    }
	
	public boolean Check_Userid(Connection_Dist conn_d, int userid)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_USERID(), false, userid))
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
	
	public Pinfo Get_User_Pinfo(Connection_Dist conn_d, int userid)
	{
		Pinfo pinfo = new Pinfo();
		
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		pinfo.setUser(new User());
		pinfo.getUser().setIdusers(userid);
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_USERID(), false, userid))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
				pinfo.setSchool(resultSet.getString("school"));
				pinfo.setBachelor(resultSet.getString("bachelor"));
				pinfo.setMaster(resultSet.getString("master"));
				pinfo.setDoctorate(resultSet.getString("doctorate"));
				pinfo.setWorkspace(resultSet.getString("workspace"));
				pinfo.setPosition(resultSet.getString("position"));
				pinfo.setMisc(resultSet.getString("misc"));
	        }
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return pinfo;
	}
	
	public List <Pinfo> Get_Users_Pinfo(Connection_Dist conn_d, List <Integer> userids)
	{
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		User_Utils u_u = new User_Utils();
		
		List <Pinfo> pinfos = new ArrayList<Pinfo>();
		
		for(int i = 0; i < userids.size(); i++)
		{	
			try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_USERID(), false, userids.get(i)))
			{
				resultSet = statement.executeQuery();
				if (!resultSet.isBeforeFirst() ) {    
					Pinfo pinfo = new Pinfo();
					pinfo.setUser(u_u.Get_UserbyId(conn_d, userids.get(i)));
					pinfos.add(pinfo);
				}
				while (resultSet.next()) 
				{
					Pinfo pinfo = new Pinfo();
					pinfo.setSchool(resultSet.getString("school"));
					pinfo.setBachelor(resultSet.getString("bachelor"));
					pinfo.setMaster(resultSet.getString("master"));
					pinfo.setDoctorate(resultSet.getString("doctorate"));
					pinfo.setWorkspace(resultSet.getString("workspace"));
					pinfo.setPosition(resultSet.getString("position"));
					pinfo.setMisc(resultSet.getString("misc"));
					pinfo.setUser(u_u.Get_UserbyId(conn_d, userids.get(i)));
					pinfos.add(pinfo);
				}
			}
			catch(SQLException se)
			{
				System.err.println(se.getMessage());
			}
		}
		return pinfos;
	}
	
	public void Update_Pinfo(Connection_Dist conn_d, Pinfo pinfo)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		Object[] fields = GetPinfo_Fields(pinfo);
		
		if(!Check_Userid(conn_d,pinfo.getUser().getIdusers()))
		{		
			try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getINSERT_PINFO(), true, fields))
			{
				statement.executeUpdate();
			}
			catch(SQLException se)
			{
				System.err.println(se.getMessage());
			}
		}
		else
		{
			try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getUPDATE_PINFO(), false, fields))
			{
				statement.executeUpdate();
			}
			catch(SQLException se)
			{
				System.err.println(se.getMessage());
			}
		}
		return;
	}
	
	public void Delete_UserPinfo(Connection_Dist conn_d, int userid)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getDELETE_PINFO(), false, userid))
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
