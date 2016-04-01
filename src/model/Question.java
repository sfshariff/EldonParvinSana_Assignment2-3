package model;
import java.io.Serializable;
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

    private int id;
	private int quizId; //FK
	private int typeId; //FK
    private String question;
    private String difficulty;
    private String hint;
    public Question() {
		super();
	}

	 
	public int getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeid) {
		this.typeId = typeid;
	}
	
	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int id) {
		this.quizId = id;
	}
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	public String getHint() {
		return hint;
	}
	
}
