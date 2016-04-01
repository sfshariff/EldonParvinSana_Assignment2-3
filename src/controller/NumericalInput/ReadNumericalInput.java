package controller.NumericalInput;

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
import model.NumericalInput;;

/**
 * Servlet implementation class ReadNumericalInput
 */
@WebServlet("/ReadNumericalInput")
public class ReadNumericalInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionPool pool = ConnectionPool.getInstance("mysql");
	Connection conn = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadNumericalInput() {
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
		
		ArrayList<NumericalInput> numericalInputList = new ArrayList<NumericalInput>();
		String sql = "SELECT id, questionId, answer FROM NUMERICALINPUT where questionID=" + questionId;

		try {
			conn = pool.getConnection();
			System.out.println("Connected to oracle database");
			pstmt = conn.createStatement();
			rset = pstmt.executeQuery(sql);

			while (rset.next()) {
				NumericalInput answer = new NumericalInput();
				answer.setId(rset.getInt(1));
				answer.setQuestionId(rset.getInt(2));
				answer.setAnswer(rset.getDouble(3));
				numericalInputList.add(answer);
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

		request.setAttribute("numericalInputList", numericalInputList);

		this.getServletContext().getRequestDispatcher("/admin/showNumericalInputList.jsp")
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
