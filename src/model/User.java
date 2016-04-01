package model;
import model.User;

public class User{
	private int id;
	private String userName;
	private String email;
    private String password;
	
	 
	public User() {
		super();
	}

	 // constructor
	

	public int getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}

	public String getName() {
		return userName;
	}

	public void setName(String user) {
		this.userName = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String emal) {
		this.email = emal;
	}
	public String getPassword(){
	     return password;
	}
	public void setPassword(String Pass){
	     this.password = Pass;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + userName + ", email=" + email + ", password=" + password + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
}

