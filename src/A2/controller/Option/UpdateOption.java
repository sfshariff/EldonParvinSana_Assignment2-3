package A2.controller.Option;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ConnectionPool;


@WebServlet("/UpdateOption")
public class UpdateOption extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionPool pool = ConnectionPool.getInstance("mysql");
	Connection conn = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOption() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Statement pstmt = null;
		
		int id = Integer.parseInt(request.getParameter("id2"));
        boolean answer = Boolean.parseBoolean(request.getParameter("answer"));
        String option = request.getParameter("option");
        
		String sql = "update OPTIONS SET answer=" + answer + " where id=" + id;
		String sql2 = "update OPTIONS SET opts='" + option + "' where id=" + id;
		
		System.out.println(sql);
		System.out.println(sql2);

		try {
			conn = pool.getConnection();
			System.out.println("Connected to oracle database...");
			pstmt = conn.createStatement();
			pstmt.executeUpdate(sql);
			pstmt.executeUpdate(sql2);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
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

			this.getServletContext().getRequestDispatcher("/viewQuiz.jsp")
				.forward(request, response);
	}

}
