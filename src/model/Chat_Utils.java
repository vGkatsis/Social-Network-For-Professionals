package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnection.Connection_Dist;
import dbconnection.PreparedStatements;

public class Chat_Utils {
	
	private final String SELECT_USERCHATS = "SELECT * FROM chats WHERE  firstuserid = ? OR seconduserid = ?";
	private final String SELECT_USERCHAT = "SELECT * FROM chats WHERE (firstuserid = ? AND seconduserid = ?) OR (firstuserid = ? AND seconduserid = ?) ";
	private final String SELECT_CHATID = "SELECT idchats FROM chats WHERE (firstuserid = ? AND seconduserid = ?) OR (firstuserid = ? AND seconduserid = ?) ";
	private final String INSERT_CHAT = "INSERT INTO chats(firstuserid,seconduserid) VALUES(?,?)";
	private final String DELETE_USERCHATS = "DELETE FROM chats WHERE firstuserid = ? OR seconduserid = ?";
	private final String DELETE_CHAT = "DELETE FROM chats WHERE idchats = ?";
	
	public String getSELECT_USERCHATS()
	{
		return this.SELECT_USERCHATS;
	}
	
	public String getSELECT_USERCHAT()
	{
		return this.SELECT_USERCHAT;
	}
	
	public String getSELECT_CHATID()
	{
		return this.SELECT_CHATID;
	}
	
	public String getINSERT_CHAT()
	{
		return this.INSERT_CHAT;
	}
	
	public String getDELETE_USERCHATS()
	{
		return this.DELETE_USERCHATS;
	}
	
	public String getDELETE_CHAT()
	{
		return this.DELETE_CHAT;
	}
	
	public List <Chat> Get_UserChats(Connection_Dist conn_d, int userid)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		User_Utils u_u = new User_Utils();
		Chatmessage_Utils cm_u = new Chatmessage_Utils();
		
		Object[] fields = new Object[] {userid,userid};
		List <Chat> chats = new ArrayList<Chat>();
		
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_USERCHATS(), false, fields))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
				Chat chat = new Chat();
				chat.setIdchats(resultSet.getInt("idchats"));
				if(userid == resultSet.getInt("seconduserid"))
				{
					chat.setSeconduserid(resultSet.getInt("seconduserid"));
					chat.setUser(u_u.Get_UserbyId(conn_d,resultSet.getInt("firstuserid")));
				}
				else
				{
					chat.setSeconduserid(resultSet.getInt("firstuserid"));
					chat.setUser(u_u.Get_UserbyId(conn_d,resultSet.getInt("seconduserid")));
				}
				chat.setChatmessages(cm_u.Get_ChatMessages(conn_d,chat.getIdchats()));
				chats.add(chat);
			}
		}	
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		return chats;
	}
	
	public int Get_Chatid(Connection_Dist conn_d, int sender, int receiver)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		Object[] fields = new Object[] {sender,receiver,receiver,sender};
		int chatid = 0;
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_CHATID(), false, fields))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
				chatid = resultSet.getInt("idchats");
			}
		}	
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		return chatid;
	}
	
	public boolean CheckFor_Chat(Connection_Dist conn_d, int firstuserid, int seconduserid)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		Object[] fields = new Object[] {firstuserid,seconduserid,seconduserid,firstuserid};
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_USERCHAT(), false, fields))
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
	
	public void New_Chat(Connection_Dist conn_d, int firstuserid, int seconduserid)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		Object[] fields = new Object[] {firstuserid, seconduserid};
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getINSERT_CHAT(), true, fields))
		{
			statement.executeUpdate();
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return;
	}
	
	public int Delete_Chat(Connection_Dist conn_d, int sender, int receiver)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		int chatid = this.Get_Chatid(conn_d, sender, receiver);
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getDELETE_CHAT(), false, chatid))
		{
			statement.executeUpdate();
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return chatid;
	}
	
	public void Delete_UserChats(Connection_Dist conn_d, int userid)
	{		
		PreparedStatements pstatement = new PreparedStatements();
		
		Object[] fields = new Object[] {userid,userid};
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getDELETE_USERCHATS(), false, fields))
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
