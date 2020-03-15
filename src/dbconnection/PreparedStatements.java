package dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PreparedStatements {

	public PreparedStatements()
	{
	}
	
	public PreparedStatement PreparedQuery(Connection_Dist conn_d, String query, boolean GenKeys, Object... fields) throws SQLException
	{
		int counter;
		PreparedStatement pstatement;
		Connection conn;
		
		conn = conn_d.getConnection();
		
		if(GenKeys)
		{
			pstatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		}
		else
		{
			pstatement = conn.prepareStatement(query,Statement.NO_GENERATED_KEYS);			
		}
		for(counter = 0; counter < fields.length; counter++)
		{
			pstatement.setObject(counter + 1,  fields[counter]);
		}
		
		return pstatement;
	}

}
