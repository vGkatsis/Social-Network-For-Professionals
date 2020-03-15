package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.Connection_Dist;
import dbconnection.PreparedStatements;
import java.util.List;

import java.math.*;
import java.util.ArrayList;

public class User_Utils {

	private final String SELECT_USERS = "SELECT * FROM users WHERE type = ?";
	private final String SELECT_USERSbyID = "SELECT * FROM users WHERE idusers = ?";
	private final String SELECT_ID = "SELECT idusers FROM users WHERE email = ?";
	private final String SELECT_NAME = "SELECT name FROM users WHERE email = ?";
	private final String SELECT_SURNAME = "SELECT surname FROM users WHERE email = ?";
	private final String SELECT_TYPE = "SELECT type FROM users WHERE email = ?";
	private final String SELECT_TEL = "SELECT telnumber FROM users WHERE email = ?";
	private final String SELECT_IMAGE = "SELECT image FROM users WHERE email = ?";
	private final String SELECT_USERS_byNAME = "SELECT * FROM users WHERE (name = ? OR surname = ?) AND type = ?";
	private final String SELECT_NAME_SURNAME ="SELECT idusers FROM users WHERE name = ? AND surname = ?";
	private final String SELECT_EMAIL_PASSWORD ="SELECT idusers FROM users WHERE email = ? AND password = ?";
	private final String SELECT_EMAIL = "SELECT email FROM users WHERE email = ?";
	private final String INSERT_USER = "INSERT INTO users(name,surname,email,password,type,telnumber,image) VALUES (?,?,?,?,?,?,?)";
	private final String UPDATE_USER = "UPDATE users SET name = ?, surname = ?, email = ?, password = ?, type = ?, telnumber = ? WHERE email = ?";
	private final String DELETE_USER = "DELETE FROM users WHERE idusers = ?";
	
	public String getSELECT_USERS()
	{
		return this.SELECT_USERS;
	}
	
	public String getSELECT_USERSbyID()
	{
		return this.SELECT_USERSbyID;
	}
	
	public String getSELECT_ID()
	{
		return this.SELECT_ID;
	}
	
	public String getSELECT_NAME()
	{
		return this.SELECT_NAME;
	}

	public String getSELECT_SURNAME()
	{
		return this.SELECT_SURNAME;
	}
	
	public String getSELECT_USERS_byNAME()
	{
		return this.SELECT_USERS_byNAME;
	}
	
	public String getSELECT_NAME_SURNAME()
	{
		return this.SELECT_NAME_SURNAME;
	}

	public String getSELECT_TYPE()
	{
		return this.SELECT_TYPE;
	}
	
	public String getSELECT_TEL()
	{
		return this.SELECT_TEL;
	}
	
	public String getSELECT_EMAIL_PASSWORD()
	{
		return this.SELECT_EMAIL_PASSWORD;
	}
	
	public String getSELECT_EMAIL()
	{
		return this.SELECT_EMAIL;
	}
	
	public String getSELECT_IMAGE()
	{
		return this.SELECT_IMAGE;
	}
	
	public String getINSERT_USER()
	{
		return this.INSERT_USER;
	}

	public String getUPDATE_USER()
	{
		return this.UPDATE_USER;
	}
	
	public String getDELETE_USER()
	{
		return this.DELETE_USER;
	}
	
	public Object[] GetUser_Fields(User newuser) {
        Object[] fields = new Object[]{ newuser.getName(),newuser.getSurname(),newuser.getEmail(),newuser.getPassword(),newuser.getType(),newuser.getTelnumber(),newuser.getImage()};
        return fields;
    }
	
	public Object[] GetUpdateUser_Fields(User newuser,String oldemail) {
        Object[] fields = new Object[]{ newuser.getName(),newuser.getSurname(),newuser.getEmail(),newuser.getPassword(),newuser.getType(),newuser.getTelnumber(),oldemail};
        return fields;
    }
	
	public User Get_UserbyId(Connection_Dist conn_d, int userid)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		User user = new User();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_USERSbyID(), false, userid))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
					user.setEmail(resultSet.getString("email"));
					user.setName(resultSet.getString("name"));
					user.setSurname(resultSet.getString("surname"));
					user.setPassword(resultSet.getString("password"));
					user.setImage(resultSet.getString("image"));
					user.setIdusers(resultSet.getInt("idusers"));
			}
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return user;
	}
	
	public List <User> Get_Users(Connection_Dist conn_d, String type)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		List <User> users = new ArrayList<User>();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_USERS(), false, type))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
					User user = new User();
					user.setEmail(resultSet.getString("email"));
					user.setName(resultSet.getString("name"));
					user.setSurname(resultSet.getString("surname"));
					user.setPassword(resultSet.getString("password"));
					user.setImage(resultSet.getString("image"));
					user.setIdusers(resultSet.getInt("idusers"));
					users.add(user);
			}
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return users;
	}

	public List <User> Get_UsersbyId(Connection_Dist conn_d, List <Integer> usersid)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		List <User> users = new ArrayList<User>();
		
		for(int i = 0; i < usersid.size(); i++)
		{	
			try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_USERSbyID(), false, usersid.get(i)))
			{
				resultSet = statement.executeQuery();
				while (resultSet.next()) 
				{
						User user = new User();
						user.setEmail(resultSet.getString("email"));
						user.setName(resultSet.getString("name"));
						user.setSurname(resultSet.getString("surname"));
						user.setPassword(resultSet.getString("password"));
						user.setImage(resultSet.getString("image"));
						user.setIdusers(resultSet.getInt("idusers"));
						users.add(user);
				}
			}
			catch(SQLException se)
			{
				System.err.println(se.getMessage());
			}
		}
		return users;
	}
	
	public List <User> Get_Users_byName(Connection_Dist conn_d, String name,String type)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		List <User> users = new ArrayList<User>();
		Object[] fields = new Object[] {name,name,type};
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_USERS_byNAME(), false, fields))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
					User user = new User();
					user.setEmail(resultSet.getString("email"));
					user.setName(resultSet.getString("name"));
					user.setSurname(resultSet.getString("surname"));
					user.setPassword(resultSet.getString("password"));
					user.setImage(resultSet.getString("image"));
					user.setIdusers(resultSet.getInt("idusers"));
					users.add(user);
			}
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return users;
	}
	
	public String Get_User_Name(Connection_Dist conn_d, String email)
	{
		String name = null;
		
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_NAME(), false, email))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
				name = resultSet.getString("name");
	        }
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return name;
	}
	
	public String Get_User_Surname(Connection_Dist conn_d, String email)
	{
		String surname = null;
		
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_SURNAME(), false, email))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
				surname = resultSet.getString("surname");
	        }
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return surname;
	}
	
	public int Get_User_Id(Connection_Dist conn_d, String email)
	{
		int id = 0;
		
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_ID(), false, email))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
				id = resultSet.getInt("idusers");
	        }
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return id;
	}
	
	public BigInteger Get_User_Tel(Connection_Dist conn_d, String email)
	{
		BigInteger tel = null;
		
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_TEL(), false, email))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
				tel = new BigInteger(resultSet.getString("telnumber"));
	        }
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return tel;
	}
	
	public String Get_User_Image(Connection_Dist conn_d, String email)
	{
		String name = null;
		
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_IMAGE(), false, email))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
				name = resultSet.getString("image");
	        }
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return name;
	}
	
	public boolean Check_Name_Surname(Connection_Dist conn_d, String name, String surname)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		Object[] fields = new Object[] {name,surname};
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_NAME_SURNAME(), false, fields))
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
	
	public boolean Check_Email_Password(Connection_Dist conn_d, String email, String pswd)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		Object[] fields = new Object[] {email,pswd};
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_EMAIL_PASSWORD(), false, fields))
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
	
	public boolean Check_Email(Connection_Dist conn_d, String email)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_EMAIL(), false, email))
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

	public String GetUserType(Connection_Dist conn_d, String email)
	{		
		String type = null;
		
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_TYPE(), false, email))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
				type = resultSet.getString("type");
	        }
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return type;
	}
	
	public void NewUser_Insert(Connection_Dist conn_d, User newuser)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		Object[] fields = GetUser_Fields(newuser);
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getINSERT_USER(), true, fields))
		{
			statement.executeUpdate();
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return;
	}

	public void Update_User(Connection_Dist conn_d, User user, String oldemail)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		Object[] fields = GetUpdateUser_Fields(user,oldemail);
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getUPDATE_USER(), false, fields))
		{
			statement.executeUpdate();
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return;
	}

	public void Delete_User(Connection_Dist conn_d, int userid)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getDELETE_USER(), false, userid))
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
