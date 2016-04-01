package controller.TextInput;

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
import model.TextInput;;

/**
 * Servlet implementation class ReadTextInput
 */
@WebServlet("/ReadTextInput")
public class ReadTextInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionPool pool = ConnectionPool.getInstance("mysql");
	Connection conn = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadTextInput() {
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
		
		ArrayList<TextInput> textInputList = new ArrayList<TextInput>();
		String sql = "SELECT id, questionId, answer FROM TEXTINPUT WHERE questionId=" + questionId;
		System.out.println(sql);
		try {
			conn = pool.getConnection();
			System.out.println("Connected to oracle database");
			pstmt = conn.createStatement();
			rset = pstmt.executeQuery(sql);

			while (rset.next()) {
				TextInput answer = new TextInput();
				answer.setId(rset.getInt(1));
				answer.setQuestionId(rset.getInt(2));
				answer.setAnswer(rset.getString(3));
				System.out.println(rset.getString(3));
				textInputList.add(answer);
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

		request.setAttribute("textInputList", textInputList);

		this.getServletContext().getRequestDispatcher("/admin/showTextInputList.jsp")
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
