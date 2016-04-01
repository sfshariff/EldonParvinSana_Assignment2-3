package controller.NumericalInput;

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
 * Servlet implementation class CreateNumericalInput
 */
@WebServlet("/CreateNumericalInput")
public class CreateNumericalInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionPool pool = ConnectionPool.getInstance("mysql");
	Connection conn = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNumericalInput() {
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
        double answer = Double.parseDouble(request.getParameter("answer"));
        
		String sql = "INSERT INTO NUMERICALINPUT (questionID, answer) VALUES (" + questionId + "," + answer + ")";
		
		try {
			conn = pool.getConnection();
			System.out.println("Connected to oracle database...Adding VALUES");
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
