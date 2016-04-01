package model;
import java.io.Serializable;


public class NumericalInput implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int questionId;
	private double answer;
		
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setQuestionId(int qid) {
		questionId = qid;
	}
	
	public int getQuestionId() {
		return questionId;
	}
	
	public void setAnswer(Double ans) {
		answer = ans;
	}
	
	public double getAnswer() {
		return answer;
	}
	
}
