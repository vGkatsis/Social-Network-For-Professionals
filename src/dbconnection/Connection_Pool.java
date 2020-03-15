package dbconnection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Connection_Pool {

    private static DataSource datasource = null;

    protected Connection_Pool() 
    {
    }

    public static synchronized DataSource getConn()
    {
    	if(datasource == null)
    	{
    		try {
    			Context context = new InitialContext();
    			Context envctx = (Context) context.lookup("java:comp/env");
    			datasource = (DataSource) envctx.lookup("jdbc/linkedb");
    		}
    		catch(NamingException e)
    		{
    			System.err.println(e.getMessage());
    		}
    		
    	}
    	
    	return datasource;
    }
}
