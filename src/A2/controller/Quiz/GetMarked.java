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

/**
 * Servlet implementation class GetMarked
 */
@WebServlet("/GetMarked")
public class GetMarked extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionPool pool = ConnectionPool.getInstance("mysql");
	Connection conn = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMarked() {
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
		String sql = null;
		
		int questionId = 0;
		int typeId = 0;
		int mark = 0;
		
		int intUserAns = 0;
		String strUserAns = null;
		
		try {
			conn = pool.getConnection();
			pstmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (int i = 0; i < 6; i++){
			
			questionId = Integer.parseInt(request.getParameter("questionId" + i));
			typeId = Integer.parseInt(request.getParameter("typeId" + i));
			
			if (typeId <= 3){
				strUserAns = request.getParameter("answer" + i);
				
				sql = "SELECT id, opts, answer FROM OPTIONS WHERE questionId = " + questionId;
				System.out.println(sql);
				System.out.println(strUserAns);
				try {
					rset = pstmt.executeQuery(sql);
					
					while (rset.next()) {
						if(rset.getString(2).equals(strUserAns)){
							System.out.println("inloop");
							if(rset.getBoolean(3)){
								mark++;
								System.out.println(i + " is correct");
							}
						}
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						if (rset != null) {
							rset.close();
							rset = null;
						}
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					}
				}
				
			} else if (typeId == 4) {
				intUserAns = Integer.parseInt(request.getParameter("answer" + i));
				
				sql = "SELECT answer FROM NUMERICALINPUT WHERE questionId = " + questionId;
				
				try {
					rset = pstmt.executeQuery(sql);
					
					rset.next();
					
					if(rset.getInt(1) == intUserAns){
						mark++;
						System.out.println(i + " is correct");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						if (rset != null) {
							rset.close();
							rset = null;
						}
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					}
				}
				
			} else {
				strUserAns = request.getParameter("answer" + i);
				
				sql = "SELECT answer FROM TEXTINPUT WHERE questionId = " + questionId;
				
				try {
					rset = pstmt.executeQuery(sql);
					
					rset.next();
					
					if(rset.getString(1).equals(strUserAns)){
						mark++;
						System.out.println(i + " is correct");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						if (rset != null) {
							rset.close();
							rset = null;
						}
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					}
				}
			}
			
		}
		
		request.setAttribute("mark", mark);
		
		this.getServletContext().getRequestDispatcher("/user/displayResult.jsp")
		.forward(request, response);
	}

}
