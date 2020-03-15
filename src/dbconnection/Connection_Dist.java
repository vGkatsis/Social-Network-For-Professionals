package dbconnection;

import java.sql.Connection;
import javax.sql.DataSource;

public class Connection_Dist {

	private Connection connection;

	public Connection_Dist()
	{
		DataSource pool = Connection_Pool.getConn();
	
		try {
			connection = pool.getConnection();
		}
		catch(Exception e)
		{
			connection = null;
			System.out.println(e);
		}
	}

	public void Close_Connection()
	{
		try {
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public Connection getConnection()
	{
		return this.connection;
	}
}
