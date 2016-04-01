package A2.controller.Option;

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
import model.Options;

@WebServlet("/ReadOption")
public class ReadOption extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionPool pool = ConnectionPool.getInstance("mysql");
	Connection conn = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadOption() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Statement pstmt = null;
		ResultSet rset = null;
		
		int questionId = Integer.parseInt(request.getParameter("id"));
		
		ArrayList<Options> optionList = new ArrayList<Options>();
		String sql = "SELECT id, questionId, opts, answer FROM OPTIONS WHERE questionId=" + questionId;

		try {
			conn = pool.getConnection();
			System.out.println("Connected to oracle database to view");
			pstmt = conn.createStatement();
			rset = pstmt.executeQuery(sql);

			while (rset.next()) {
				Options option = new Options();
				option.setId(rset.getInt(1));
				option.setQuestionId(rset.getInt(2));
				option.setOption(rset.getString(3));
				option.setAnswer(rset.getBoolean(4));
				optionList.add(option);
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

		request.setAttribute("optionList", optionList);

		this.getServletContext().getRequestDispatcher("/admin/viewOption.jsp")
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
