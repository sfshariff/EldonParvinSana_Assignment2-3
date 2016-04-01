package model;

import java.io.Serializable;

public class Options implements Serializable{

	private static final long serialVersionUID = -4790646922331806271L;
	private int id;
	private int questionId; //FK
    private String opts;
    private boolean answer;
    public Options() {
		super();
	}
	  //Default constructor
	 
	 // constructor

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int id) {
		this.questionId = id;
	}
	
	
	public String getOption() {
		return opts;
	}

	public void setOption(String option) {
		this.opts = option;
	}
	public boolean getAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}
	
}
