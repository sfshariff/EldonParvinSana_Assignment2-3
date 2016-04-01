package model;
import model.Result;
public class Result {
	
	private int id;
	private int userId;
	private int quizId;
	private int grade;
	private int hrs;
	private int mins;
	private int secs;
	
	public int getId() {
		return id;
	}
	
	public void setUserId(int uid) {
		userId = uid;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setQuizId(int qid) {
		quizId = qid;
	}
	
	public int getQuizId() {
		return quizId;
	}
	
	public void setGrade(int marks) {
		grade = marks;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public void setHrs (int hours) {
		hrs = hours;
	}
	
	public int getHrs() {
		return hrs;
	}
	
	
	public void setMins (int minutes) {
		mins = minutes;
	}
	
	public int getMins() {
		return mins;
	}
	
	public void setSecs(int seconds) {
		secs = seconds;
	}
	
	public int getSecs() {
		return secs;
	}

	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	
}
