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
import model.Quiz;

/**
 * Servlet implementation class CreateTextInput
 */
@WebServlet("/CreateQuestion")
public class CreateQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionPool pool = ConnectionPool.getInstance("mysql");
	Connection conn = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuestion() {
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
		ResultSet rset = null;
		
		int quizId = Integer.parseInt(request.getParameter("quizId"));
		int numQuestions = Integer.parseInt(request.getParameter("numQuestions"));
		String difficulty = null;
		
		if (numQuestions < 20) {
			// 0 - 19
			difficulty = "Easy";
		} else if (numQuestions < 30){
			// 20-29
			difficulty = "Medium";
		} else {
			// 30-34
			difficulty = "Difficult";
		}
		
		int typeId = Integer.parseInt(request.getParameter("typeId"));
        String question = request.getParameter("question");
        String hint = request.getParameter("hint");
		String sql = "INSERT INTO QUESTION (typeId, quizId, question, difficulty, hint) VALUES ('" + typeId + "','" + quizId + "','" + question + "','"  + difficulty + "','" +  hint + "')";

		try {
			System.out.println("creating question");
			conn = pool.getConnection();
			System.out.println("Connected to database...");
			pstmt = conn.createStatement();
			System.out.println(quizId);
			System.out.println(typeId);
			pstmt.executeUpdate(sql);
			sql = "SELECT LAST_INSERT_ID()";
			rset = pstmt.executeQuery(sql);
			System.out.println("creating question");
			rset.next();
			Question q = new Question();
			q.setId(rset.getInt(1));
			q.setQuizId(quizId);
			q.setTypeId(typeId);
			q.setQuestion(question);
			q.setDifficulty(difficulty);
			q.setHint(hint);
			
			request.setAttribute("question", q);
			
			Quiz quiz = new Quiz();
			quiz.setId(quizId);
			quiz.setNumQuestions(numQuestions+1);
			quiz.updateNumQuestions(numQuestions+1);

			request.setAttribute("quiz", quiz);
			
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
		
		this.getServletContext().getRequestDispatcher("/admin/createAnswer.jsp")
				.forward(request, response);
	}

}
