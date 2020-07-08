package reviewapp.model;



public class SitDownRestaurant extends Restaurant {
	protected int SitDownRestaurantKey;
	protected int Capacity;
	
public SitDownRestaurant(int SitDownRestaurantKey, String Name, 
		String Description, String Menu, 
		String ListedHours, boolean IsActive, String Street1, 
		String Street2, String City, String State, 
		String ZipCode, Cuisine Cuisine, int CompanyKey, int Capacity) {
	super(SitDownRestaurantKey, Name, Description, Menu, ListedHours, IsActive,
			Street1, Street2, City, State, ZipCode, Cuisine, CompanyKey);
	this.SitDownRestaurantKey = SitDownRestaurantKey;
	this.Name = Name;
	this.Description = Description;
	this.Menu = Menu;
	this.ListedHours = ListedHours;
	this.IsActive = IsActive;
	this.Street1 = Street1;
	this.Street2 = Street2;
	this.City = City;
	this.State = State;
	this.ZipCode = ZipCode;
	this.Cuisine = Cuisine;
	this.CompanyKey = CompanyKey;
}

public SitDownRestaurant(int SitDownRestaurantKey, String Name, 
		String Description, String Menu, 
		String ListedHours, boolean IsActive, String Street1, 
		String Street2, String City, String State, 
		String ZipCode, Cuisine Cuisine, int CompanyKey, String CompanyName) {
	super(SitDownRestaurantKey, Name, Description, Menu, ListedHours, IsActive,
			Street1, Street2, City, State, ZipCode, Cuisine, CompanyKey);
	this.SitDownRestaurantKey = SitDownRestaurantKey;
	this.Name = Name;
	this.CompanyKey = CompanyKey;
}

/*Getters and Setters*/


	public int getSitDownRestaurantKey() {
		return SitDownRestaurantKey;
	}
	
	public void setSitDownRestaurantKey(int SitDownRestaurantKey) {
		this.SitDownRestaurantKey = SitDownRestaurantKey;
	}
	
	public int getCapacity() {
		return Capacity;
	}
	
	public void setCapacity(int Capacity) {
		this.Capacity = Capacity;
	}
}