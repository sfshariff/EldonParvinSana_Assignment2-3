package model;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import data.ConnectionPool;

public class Quiz implements Serializable {
	private static final long serialVersionUID = 1L;

	/* Document dom;
	public int currentQuestion=0;	

	public Map selections=new LinkedHashMap();
	public ArrayList questionList = new ArrayList(10);

	public Quiz(String test) throws SAXException,ParserConfigurationException,IOException, URISyntaxException{
		dom=CreateDOM.getDOM(test);
	} */
    private int id;
	private String quizName;
	private String quizDesc;
	private int numQuestions;
	private Hashtable<String, String> errors;
	
	public void updateNumQuestions(int numQ){
		ConnectionPool pool = ConnectionPool.getInstance("mysql");
		Connection conn = null;
		
		Statement pstmt = null;
		
		String sql = "update QUIZ SET numQuestions=" + numQ + " where id=" + this.id;
		
		conn = pool.getConnection();
		
		try {
			conn = pool.getConnection();
			pstmt = conn.createStatement();
			pstmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void decreaseNumQuestions(){
		ConnectionPool pool = ConnectionPool.getInstance("mysql");
		Connection conn = null;
		
		Statement pstmt = null;
		
		String sql = "update QUIZ SET numQuestions = numQuestions - 1 where id=" + this.id;
		
		System.out.println(sql);
		
		conn = pool.getConnection();
		
		try {
			conn = pool.getConnection();
			pstmt = conn.createStatement();
			pstmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setQuizName(String qName) {
		quizName = qName;
	}
	
	public void setQuizDesc (String qDesc) {
		quizDesc = qDesc;
	}
	
	public int getQuizId() {
		return id;
	}
	
	public String getQuizName()
	{
		return quizName;
	}
	
	public String getQuizDesc() {
		return quizDesc;
	}
	
	public int getNumQuestions() {
		return numQuestions;
	}
	
	public void setNumQuestions(int num) {
		this.numQuestions = num;
	}
	
	
	public String getErrorMsg(String s) {
		String errorMsg = errors.get(s.trim());
		return (errorMsg == null) ? "" : errorMsg;
	}
	
	
}
