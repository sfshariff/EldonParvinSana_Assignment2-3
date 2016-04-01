package A2.controller.Quiz;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ConnectionPool;
import model.Quiz;
import model.Question;
import model.Options;

/**
 * Servlet implementation class TakeQuiz
 */
@WebServlet("/TakeQuiz")
public class TakeQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionPool pool = ConnectionPool.getInstance("mysql");
	Connection conn = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeQuiz() {
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
		
		int id = Integer.parseInt(request.getParameter("id"));
		String quizName = request.getParameter("name");
		ArrayList<Question> questionList = new ArrayList<Question>();
		
		Quiz quiz = new Quiz();
		quiz.setId(id);
		quiz.setQuizName(quizName);
		request.setAttribute("quiz", quiz);
		
		
		String sql = null;

		try {
			conn = pool.getConnection();
			System.out.println("Connected to mySql database...");
			pstmt = conn.createStatement();
			
			sql = "SELECT id, typeId, question, hint FROM QUESTION WHERE quizId = " + id + " and difficulty='Easy'";
			System.out.println(sql);
			
			rset = pstmt.executeQuery(sql);
			
			while (rset.next()) {
				Question question = new Question();
				question.setId(rset.getInt(1));
				question.setTypeId(rset.getInt(2));
				question.setQuestion(rset.getString(3));
				question.setHint(rset.getString(4));
				questionList.add(question);
			}
			
			long seed = System.nanoTime();
			Collections.shuffle(questionList, new Random(seed));
			
			Question easyQ1 = questionList.get(0);
			Question easyQ2 = questionList.get(1);
			Question easyQ3 = questionList.get(2);
			
			questionList.clear();
			
			sql = "SELECT id, typeId, question, hint FROM QUESTION WHERE quizId = " + id + " and difficulty='Medium'";
			
			rset = pstmt.executeQuery(sql);
			
			while (rset.next()) {
				Question question = new Question();
				question.setId(rset.getInt(1));
				question.setTypeId(rset.getInt(2));
				question.setQuestion(rset.getString(3));
				question.setHint(rset.getString(4));
				questionList.add(question);
			}
			
			Collections.shuffle(questionList, new Random(seed));
			
			Question mediumQ4 = questionList.get(0);
			Question mediumQ5 = questionList.get(1);
			
			questionList.clear();
			
			sql = "SELECT id, typeId, question, hint FROM QUESTION WHERE quizId = " + id + " and difficulty='Difficult'";
			
			rset = pstmt.executeQuery(sql);
			
			while (rset.next()) {
				Question question = new Question();
				question.setId(rset.getInt(1));
				question.setTypeId(rset.getInt(2));
				question.setQuestion(rset.getString(3));
				question.setHint(rset.getString(4));
				questionList.add(question);
			}
			
			Collections.shuffle(questionList, new Random(seed));
			
			Question difficultQ6 = questionList.get(0);
			
			questionList.clear();
			
			questionList.add(easyQ1);
			questionList.add(easyQ2);
			questionList.add(easyQ3);
			questionList.add(mediumQ4);
			questionList.add(mediumQ5);
			questionList.add(difficultQ6);
			
			request.setAttribute("questionList", questionList);
			
			ArrayList<Options> optionList = new ArrayList<Options>();
			
			for(int i = 0; i < questionList.size(); i++){
				if(questionList.get(i).getTypeId() <= 3){
					sql = "SELECT id, opts, answer FROM OPTIONS WHERE questionId = " + questionList.get(i).getId();
					rset = pstmt.executeQuery(sql);
					while (rset.next()) {
						Options opts = new Options();
						opts.setId(rset.getInt(1));
						opts.setOption(rset.getString(2));
						optionList.add(opts);
					}
				} else {
					optionList.add(null);
					optionList.add(null);
					optionList.add(null);
					optionList.add(null);
				}
			}
			
			request.setAttribute("optionList", optionList);
			
			this.getServletContext().getRequestDispatcher("/user/generatedQuiz.jsp")
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
