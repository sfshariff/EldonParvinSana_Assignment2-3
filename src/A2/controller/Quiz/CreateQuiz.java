package A2.controller.Quiz;

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
import model.Quiz;

/**
 * Servlet implementation class CreateQuiz
 */
@WebServlet("/CreateQuiz")
    public class CreateQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionPool pool = ConnectionPool.getInstance("mysql");
	Connection conn = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuiz() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	   doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
       Statement pstmt = null;
       ResultSet rset = null;
		
        String quizName = request.getParameter("quizName");
        String quizDesc = request.getParameter("quizDesc");
        String sql = "INSERT INTO QUIZ (quizName, quizDesc, numQuestions) VALUES ('" + quizName + "','" + quizDesc + "', 0)";
        
        
		try {
			conn = pool.getConnection();
			System.out.println("Connected to mySQL database...Add Quiz");
			pstmt = conn.createStatement();
			pstmt.executeUpdate(sql);
			sql = "SELECT LAST_INSERT_ID()";
			rset = pstmt.executeQuery(sql);
			
			rset.next();
			Quiz quiz = new Quiz();
			quiz.setId(rset.getInt(1));
			quiz.setQuizName(quizName);
			quiz.setQuizDesc(quizDesc);
			quiz.setNumQuestions(0);
			request.setAttribute("quiz", quiz);
			
			this.getServletContext().getRequestDispatcher("/admin/createQuestion.jsp")
			.forward(request, response);
          
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
	}

}
