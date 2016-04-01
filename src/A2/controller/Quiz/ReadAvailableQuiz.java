package A2.controller.Quiz;

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
import model.Quiz;

/**
 * Servlet implementation class ReadAvailableQuiz
 */
@WebServlet("/ReadAvailableQuiz")
public class ReadAvailableQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionPool pool = ConnectionPool.getInstance("mysql");
	Connection conn = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadAvailableQuiz() {
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
				ArrayList<Quiz> quizList = new ArrayList<Quiz>();
				String sql = "SELECT id, quizName, quizDesc, numQuestions FROM QUIZ WHERE numQuestions = 35";
				try {
					conn = pool.getConnection();
					System.out.println("Connected to mySQL database to read");
					pstmt = conn.createStatement();
					rset = pstmt.executeQuery(sql);

					while (rset.next()) {
						Quiz quiz = new Quiz();
						quiz.setId(rset.getInt(1));
						quiz.setQuizName(rset.getString(2));
						quiz.setQuizDesc(rset.getString(3));
						quiz.setNumQuestions(rset.getInt(4));
						quizList.add(quiz);
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

				request.setAttribute("quizList", quizList);

				this.getServletContext().getRequestDispatcher("/user/takeQuiz.jsp")
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
