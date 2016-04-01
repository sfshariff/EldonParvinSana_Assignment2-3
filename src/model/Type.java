package model;
public class Type {

	private int id;
	private String typeDesc;
	
	  //Default constructor
	 
	public Type() {
		super();
	}

	 // constructor
	 	public Type(int id, String typeDesc) {
		super();
		this.id = id;
		this.typeDesc = typeDesc;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String typeDesc() {
		return typeDesc;
	}
	public void setDesc(String desc) {
		this.typeDesc = desc;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", typeDesc=" + typeDesc + "]";
	}
	

}
