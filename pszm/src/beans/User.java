package beans;

public class User {
	protected String username;
	protected String email;
	protected String firstname;
	protected String lastname;
	
	public User() {
		this.username = "";
		this.email = "";
		this.firstname = "";
		this.lastname = "";
	}
	
	
	public User(String username, String email, String firstname, String lastname) {
		this.username = username;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public User(UserDB usr) {
		this.username = usr.getUsername();
		this.email = usr.getEmail();
		this.firstname = usr.getFirstname();
		this.lastname = usr.getLastname();
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
