package A2.controller.Question;

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
 * Servlet implementation class DeleteTextInput
 */
@WebServlet("/DeleteQuestion")
public class DeleteQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionPool pool = ConnectionPool.getInstance("mysql");
	Connection conn = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteQuestion() {
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
		
		int id = Integer.parseInt(request.getParameter("id"));
		int quizId = Integer.parseInt(request.getParameter("quizId"));
        
		String sql = "delete from QUESTION where id=" + id;

		try {
			conn = pool.getConnection();
			System.out.println("Connected to oracle database...");
			pstmt = conn.createStatement();
			pstmt.executeUpdate(sql);

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

		Quiz quiz = new Quiz();
		quiz.setId(quizId);
		quiz.decreaseNumQuestions();
		request.setAttribute("quiz", quiz);
		
		this.getServletContext().getRequestDispatcher("/viewQuestion.jsp")
				.forward(request, response);
	}

}
