package model;
import java.io.Serializable;
import java.util.Hashtable;

/*
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
*/
public class TextInput implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int questionId;
	private String answer;
	private Hashtable<String, String> errors;
		
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
	
	public String getAnswer() {
		return answer;
	}
	
	public boolean validate() {

		boolean allOk = true;

		if (answer.equals("")) {
			errors.put("answer", "Please enter your last name");
			answer = "";
			allOk = false;
		}

		return allOk;
	}

	public String getErrorMsg(String s) {
		String errorMsg = errors.get(s.trim());
		return (errorMsg == null) ? "" : errorMsg;
	}


	public void setAnswer(String string) {
		// TODO Auto-generated method stub
		answer = string;
	}
	
}
