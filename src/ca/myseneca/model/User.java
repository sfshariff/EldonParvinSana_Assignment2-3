package ca.myseneca.model;

import java.io.Serializable;

public class User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
    private String firstName;
    private String lastName;
    private String password;

    public User() {      
        email = "";
        firstName = "";
        lastName = "";
        password = "";
    }

    public User(String email, String firstName, String lastName, String password) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", password=" + password + "]";
	}
}
