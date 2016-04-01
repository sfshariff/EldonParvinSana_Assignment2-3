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
import model.Quiz;

/**
 * Servlet implementation class CreateTextInput
 */
@WebServlet("/CreateOption")
public class CreateOption extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionPool pool = ConnectionPool.getInstance("mysql");
	Connection conn = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOption() {
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
		
		int quizId = Integer.parseInt(request.getParameter("quizId"));
		int numQuestions = Integer.parseInt(request.getParameter("numQuestions"));
		
		int questionId = Integer.parseInt(request.getParameter("questionId"));
        String answer1 = request.getParameter("answer1");
        String option1 = request.getParameter("option1");
        String answer2 = request.getParameter("answer2");
        String option2 = request.getParameter("option2");
        String answer3 = request.getParameter("answer3");
        String option3 = request.getParameter("option3");
        String answer4 = request.getParameter("answer4");
        String option4 = request.getParameter("option4");
        
		String sql1 = "INSERT INTO OPTIONS (questionId, opts, answer) VALUES (" + questionId + ",'" + option1 + "'," + answer1 + ")";
		String sql2 = "INSERT INTO OPTIONS (questionId, opts, answer) VALUES (" + questionId + ",'" + option2 + "'," + answer2 + ")";
		String sql3 = "INSERT INTO OPTIONS (questionId, opts, answer) VALUES (" + questionId + ",'" + option3 + "'," + answer3 + ")";
		String sql4 = "INSERT INTO OPTIONS (questionId, opts, answer) VALUES (" + questionId + ",'" + option4 + "'," + answer4 + ")";
		
		try {
			conn = pool.getConnection();
			System.out.println("Connected to database...to add options");
			pstmt = conn.createStatement();
			pstmt.executeUpdate(sql1);
			pstmt.executeUpdate(sql2);
			pstmt.executeUpdate(sql3);
			pstmt.executeUpdate(sql4);

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

		Quiz q = new Quiz();
		q.setId(quizId);
		q.setNumQuestions(numQuestions);
		
		if (numQuestions < 35){
			
			request.setAttribute("quiz", q);
			this.getServletContext().getRequestDispatcher("/admin/createQuestion.jsp")
			.forward(request, response);
		} else {
			
			q.updateNumQuestions(35);
			
			this.getServletContext().getRequestDispatcher("/viewQuiz.jsp")
			.forward(request, response);
		}
	}

}
