package reviewapp.model;



/* FoodCartRestaurant inherits from Restaurant */

public class FoodCartRestaurant extends Restaurant {
	protected int FoodCartRestaurantKey;
	protected boolean IsLicensed;
	
public FoodCartRestaurant(int FoodCartRestaurantKey, String Name, 
		String Description, String Menu, 
		String ListedHours, boolean IsActive, String Street1, 
		String Street2, String City, String State, 
		String ZipCode, Cuisine Cuisine, int CompanyKey, boolean IsLicensed) {
	super(FoodCartRestaurantKey, Name, Description, Menu, ListedHours, IsActive,
			Street1, Street2, City, State, ZipCode, Cuisine, CompanyKey);
	this.FoodCartRestaurantKey = FoodCartRestaurantKey;
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

public FoodCartRestaurant(int FoodCartRestaurantKey, String Name, 
		String Description, String Menu, 
		String ListedHours, boolean IsActive, String Street1, 
		String Street2, String City, String State, 
		String ZipCode, Cuisine Cuisine, int CompanyKey, boolean IsLicensed, String CompanyName) {
	super(FoodCartRestaurantKey, Name, Description, Menu, ListedHours, IsActive,
			Street1, Street2, City, State, ZipCode, Cuisine, CompanyKey);
	this.FoodCartRestaurantKey = FoodCartRestaurantKey;
	this.Name = Name;
	this.CompanyKey = CompanyKey;
}


/*Getters and Setters*/

	public int getFoodCartRestaurantKey() {
	return FoodCartRestaurantKey;
}

	public void setFoodCartRestaurantKey(int FoodCartRestaurantKey) {
		this.FoodCartRestaurantKey = FoodCartRestaurantKey;
	}
	
	public boolean getIsLicensed() {
		return IsLicensed;
	}
	
	public void setIsLicensed(boolean IsLicensed) {
		this.IsLicensed = IsLicensed;
	}

}
