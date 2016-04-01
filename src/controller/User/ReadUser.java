package controller.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ConnectionPool;
import model.User;

/**
 * Servlet implementation class ReadUser
 */
@WebServlet("/ReadUser")
public class ReadUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionPool pool = ConnectionPool.getInstance("mysql");
	Connection conn = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Statement pstmt = null;
		ResultSet rset = null;
		ArrayList<User> userList = new ArrayList<User>();
		String sql = "SELECT id, userName, email, password FROM USER";
		try {
			conn = pool.getConnection();
			System.out.println("Connected to mySQL database to read user");
			pstmt = conn.createStatement();
			rset = pstmt.executeQuery(sql);

			while (rset.next()) {
				User user = new User();
				user.setId(rset.getInt(1));
				user.setName(rset.getString(2));
				user.setEmail(rset.getString(3));
				user.setPassword(rset.getString(4));
				userList.add(user);
				System.out.println("Connected to mySQL database to read user");
			}
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

		request.setAttribute("userList", userList);

		this.getServletContext().getRequestDispatcher("/viewUser.jsp")
				.forward(request, response);
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
