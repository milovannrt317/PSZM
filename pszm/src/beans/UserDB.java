package beans;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class UserDB extends User{
	private String salt; 
	private String password; 
	
	public UserDB() {
		super();
		this.username = "";
		this.password = "";
		this.email = "";
		this.firstname = "";
		this.lastname = "";
		this.salt = "";
	}
	
	
	public UserDB(String username, String password, String email, String firstname, String lastname, String salt) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.salt = salt;
	}
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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


	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String cipherPass(String pass, String slt) throws Exception {
		byte[] salt;
		SecureRandom random=new SecureRandom();
		if(slt==null) {
	        salt = new byte[16];
	        random.nextBytes(salt);
	        setSalt(Base64.getEncoder().encodeToString(salt));
		}
		else {
			salt = Base64.getDecoder().decode(slt);
		}
        
        MessageDigest md=MessageDigest.getInstance("SHA-512");
        md.update(salt);
        
        byte[] hashedpass=md.digest(pass.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashedpass);
	}
	
}