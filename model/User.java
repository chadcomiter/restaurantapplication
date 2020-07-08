package reviewapp.model;


public class User {
	protected String UserName;
	protected String PasswordHash;
	protected String FirstName;
	protected String LastName;
	protected String Email;
	protected String PhoneNum;
	
	public User(String UserName, String PasswordHash, String FirstName, String LastName, String Email, String PhoneNum) {
		this.UserName = UserName;
		this.PasswordHash = PasswordHash;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Email = Email;
		this.PhoneNum = PhoneNum;
	}
	
	public User(String UserName) {
		this.UserName = UserName;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String UserName) {
		this.UserName = UserName;
	}
	
	public String getPasswordHash() {
		return PasswordHash;
	}
	
	public void setPasswordHash(String PasswordHash) {
		this.PasswordHash = PasswordHash;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String LastName) {
		this.LastName = LastName;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String Email) {
		this.Email = Email;
	}
	
	public String getPhoneNum() {
		return PhoneNum;
	}
	
	public void setPhoneNum(String PhoneNum) {
		this.PhoneNum = PhoneNum;
	}
}
