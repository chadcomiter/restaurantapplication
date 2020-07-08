package reviewapp.model;

public class Restaurant extends Company {
	protected int RestaurantKey;
	protected String Menu;
	protected String Name;
	protected String Description;
	protected String ListedHours;
	protected boolean IsActive;
	protected String Street1;
	protected String Street2;
	protected String City;
	protected String State;
	protected String ZipCode;
	protected Cuisine Cuisine;
	protected int CompanyKey;
		
	public enum Cuisine {
		african , american, asian, european, hispanic
	}
	
	public Restaurant(int RestaurantKey, String Name, 
			String Description, String Menu, 
			String ListedHours, boolean IsActive, String Street1, 
			String Street2, String City, String State, 
			String ZipCode, Cuisine Cuisine, int CompanyKey) {
		super(CompanyKey);
		this.RestaurantKey = RestaurantKey;
		this.CompanyKey = CompanyKey;
		this.Name = Name;
		this.Description = Description;
		this.Menu = Menu;
		this.ListedHours = ListedHours;	
	}
	
	public Restaurant(int RestaurantKey, String Name, 
			String Description, String Menu, 
			String ListedHours, boolean IsActive, String Street1, 
			String Street2, String City, String State, 
			String ZipCode, int CompanyKey) {
		super(CompanyKey);
		this.RestaurantKey = RestaurantKey;
		this.CompanyKey = CompanyKey;
		this.Name = Name;
		this.Description = Description;
		this.Menu = Menu;
		this.ListedHours = ListedHours;	
	}
	
	public Restaurant(int RestaurantKey, int CompanyKey) {
		super(CompanyKey);
		this.RestaurantKey = RestaurantKey;
	}
	
	/*Getters and Setters. */
	
	public int getRestaurantKey() {
		return RestaurantKey;
	}
	
	public void setRestaurantKey(int RestaurantKey) {
		this.RestaurantKey = RestaurantKey;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String Name) {
		this.Name = Name;
	}
	
	public String getDescription() {
		return Description;
	}
	
	public void setDescription(String Description) {
		this.Description = Description;
	}
	
	public String getMenu() {
		return Menu;
	}
	
	public void setMenu(String Menu) {
		this.Menu = Menu;
	}
	
	public String getListedHours() {
		return ListedHours;
	}
	
	public void setListedHours(String ListedHours) {
		this.ListedHours = ListedHours;
	}
	
	public boolean getIsActive() {
		return IsActive;
	}
	
	public void setIsActive(boolean IsActive) {
		this.IsActive = IsActive;
	}
	
	public String getStreet1() {
		return Street1;
	}
	
	public void setStreet1(String Street1) {
		this.Street1 = Street1;
	}
	
	public String getStreet2() {
		return Street2;
	}
	
	public void setStreet2(String Street2) {
		this.Street2 = Street2;
	}
	
	public String getCity() {
		return City;
	}
	
	public void setCity(String City) {
		this.City = City;
	}
	
	public String getState() {
		return State;
	}
	
	public void setState(String State) {
		this.State = State;
	}
	
	public String getZipCode() {
		return ZipCode;
	}
	
	public void setZipCode(String ZipCode) {
		this.ZipCode = ZipCode;
	}
	
	public Cuisine getCuisine() {
		return Cuisine;
	}
	
	public void setCuisine(Cuisine Cuisine) {
		this.Cuisine = Cuisine;
	}
}
