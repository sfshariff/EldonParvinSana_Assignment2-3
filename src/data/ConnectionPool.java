package data;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConnectionPool {

	private static ConnectionPool pool = null;
	private static DataSource dataSource = null;

	private ConnectionPool(String db /* mysql or oracle */) {
		try {
			InitialContext ic = new InitialContext();
			if ("mysql".equalsIgnoreCase(db))
				dataSource = (DataSource) ic
						.lookup("java:/comp/env/jdbc/mysql_dps904_161a19");
			else
				dataSource = (DataSource) ic
						.lookup("java:/comp/env/jdbc/oracle_dps904_161a19");
		} catch (NamingException e) {
			System.out.println(e);
		}
	}

	public static synchronized ConnectionPool getInstance(String db /* mysql or oracle */) {
		if (pool == null) {
			pool = new ConnectionPool(db);
		}
		return pool;
	}

	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public void freeConnection(Connection c) {
		try {
				c.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}