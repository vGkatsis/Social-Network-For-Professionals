package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnection.Connection_Dist;
import dbconnection.PreparedStatements;

public class Chatmessage_Utils {
	
	private final String SELECT_CHATMESSAGES = "SELECT * FROM chatmessages WHERE chatid = ?";
	private final String INSERT_CHATMESSAGE = "INSERT INTO chatmessages(chatid,senderid,message) VALUES(?,?,?)";
	private final String DELETE_CHATMESSAGE = "DELETE FROM chatmessages WHERE senderid = ?";
	private final String DELETE_CHATMESSAGEbyCHAT = "DELETE FROM chatmessages WHERE chatid = ?";
	
	public String getSELECT_CHATMESSAGES()
	{
		return this.SELECT_CHATMESSAGES;
	}
	
	public String getINSERT_CHATMESSAGE()
	{
		return this.INSERT_CHATMESSAGE;
	}
	
	public String getDELETE_CHATMESSAGE()
	{
		return this.DELETE_CHATMESSAGE;
	}
	
	public String getDELETE_CHATMESSAGEbyCHAT()
	{
		return this.DELETE_CHATMESSAGEbyCHAT;
	}
	
	public List <Chatmessage> Get_ChatMessages(Connection_Dist conn_d, int chatid)
	{	
		PreparedStatements pstatement = new PreparedStatements();
		ResultSet resultSet;
		
		List <Chatmessage> messages = new ArrayList<Chatmessage>();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getSELECT_CHATMESSAGES(), false, chatid))
		{
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
				Chatmessage message = new Chatmessage();
				message.setIdchatmessages(resultSet.getInt("idchatmessages"));
				message.setSenderid(resultSet.getInt("senderid"));
				message.setMessage(resultSet.getString("message"));
				message.setChat(new Chat());
				message.getChat().setIdchats(resultSet.getInt("chatid"));
				messages.add(message);
			}
		}	
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		return messages;
	}

	public void Insert_Message(Connection_Dist conn_d, int chatid, int userid, String message)
	{
		PreparedStatements pstatement = new PreparedStatements();

		Object[] fields = new Object[] {chatid,userid,message};
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getINSERT_CHATMESSAGE(), true, fields))
		{
			statement.executeUpdate();
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return;
	}

	public void Delete_UserMessage(Connection_Dist conn_d, int userid)
	{
		PreparedStatements pstatement = new PreparedStatements();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getDELETE_CHATMESSAGE(), false, userid))
		{
			statement.executeUpdate();
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
		return;
	}

	public void Delete_ChatMessage(Connection_Dist conn_d, int chatid)
	{
		PreparedStatements pstatement = new PreparedStatements();
		
		try(PreparedStatement statement = pstatement.PreparedQuery(conn_d, this.getDELETE_CHATMESSAGEbyCHAT(), false, chatid))
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

