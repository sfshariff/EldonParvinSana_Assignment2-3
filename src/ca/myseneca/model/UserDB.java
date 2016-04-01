package ca.myseneca.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.ConnectionPool;
import ca.myseneca.model.User;

public class UserDB {

    public static boolean CheckAdmin(User user) {
    	//TODO: Modify this code that check the user from the database
    	boolean ok = false;
    	if ("admin@email.com".equals(user.getEmail()) 
    		  && "admin".equals(user.getPassword())) {
    		ok = true;
    	}
    		
    	return ok;
    }
    
    public static boolean CheckUser(User user){
    	
    	ConnectionPool pool = ConnectionPool.getInstance("mysql");
		Connection conn = null;
		int userId = 0;
    	Statement pstmt = null;
		ResultSet rset = null;
		boolean found=false;
		
		String sql = "SELECT id, email, password FROM USER WHERE email = '" + user.getEmail() + "' and password = '" + user.getPassword() + "'";
    	
		try {
			conn = pool.getConnection();
			System.out.println("Connected to mySql database..fining  user.");
			pstmt = conn.createStatement();
			rset = pstmt.executeQuery(sql);
			
			rset.next();
			
			userId = rset.getInt(1);
			if(userId > 0)
				found = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
					rset = null;
				}
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (pool != null)
					pool.freeConnection(conn);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		return found;
    }
}
