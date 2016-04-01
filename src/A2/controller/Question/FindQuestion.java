package A2.controller.Question;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ConnectionPool;
import model.Question;

/**
 * Servlet implementation class FindTextInput
 */
@WebServlet("/FindQuestion")
public class FindQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionPool pool = ConnectionPool.getInstance("mysql");
	Connection conn = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindQuestion() {
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
		// TODO Auto-generated method stub
		Statement pstmt = null;
		ResultSet rset = null;
		
		int id = Integer.parseInt(request.getParameter("id"));
        
		String sql = "SELECT id, typeId, quizId, question, difficulty, hint FROM QUESTION WHERE ID = " + id;

		try {
			conn = pool.getConnection();
			System.out.println("Connected to oracle database...");
			pstmt = conn.createStatement();
			rset = pstmt.executeQuery(sql);
			
			rset.next();
			Question question = new Question();
			question.setId(rset.getInt(1));
			question.setTypeId(rset.getInt(2));
			question.setQuizId(rset.getInt(3));
			question.setQuestion(rset.getString(4));
			question.setDifficulty(rset.getString(5));
			question.setHint(rset.getString(6));
			request.setAttribute("question", question);
			
			this.getServletContext().getRequestDispatcher("/modifyQuestion.jsp")
			.forward(request, response);

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
		
	}

}
