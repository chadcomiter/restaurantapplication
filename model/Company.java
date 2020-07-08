package reviewapp.model;

/* Company is the superclass for Restaurants */

public class Company {
	protected int CompanyKey;
	protected String CompanyName;
	protected String CompanyDescription;
	
	
public Company(int CompanyKey, String Name, String CompanyDescription) {
	this.CompanyKey = CompanyKey;
	this.CompanyName = Name;
	this.CompanyDescription = CompanyDescription;
}

	public Company(int CompanyKey) {
		this.CompanyKey = CompanyKey;
}

	public int getCompanyKey() {
		return CompanyKey;
	}
	
	public void setCompanyKey(int CompanyKey) {
		this.CompanyKey = CompanyKey;
	}

	public String getCompanyName() {
		return CompanyName;
	}
	
	public void setCompanyName(String Name) {
		this.CompanyName = Name;
	}
	
	public String getCompanyDescription() {
		return CompanyDescription;
	}
	
	public void setCompanyDescription(String Description) {
		this.CompanyDescription = Description;
	}

}
