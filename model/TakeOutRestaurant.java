package reviewapp.model;


/* Restaurant is the superclass for TakeOutRestaurant */

public class TakeOutRestaurant extends Restaurant {
	protected int TakeOutRestaurantKey;
	protected int MaxWaitMinutes;
	
	
	public TakeOutRestaurant(int TakeOutRestaurantKey, String Name, 
			String Description, String Menu, 
			String ListedHours, boolean IsActive, String Street1, 
			String Street2, String City, String State, 
			String ZipCode, Cuisine Cuisine, int CompanyKey, int MaxWaitMinutes) {
		super(TakeOutRestaurantKey, Name, Description, Menu, ListedHours, IsActive,
				Street1, Street2, City, State, ZipCode, Cuisine, CompanyKey);
		this.TakeOutRestaurantKey = TakeOutRestaurantKey;
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
	public int getTakeOutRestaurantKey() {
		return TakeOutRestaurantKey;
	}
	
	public void setTakeOutRestaurantKey(int TakeOutRestaurantKey) {
		this.TakeOutRestaurantKey = TakeOutRestaurantKey;
	}
	
	public int getMaxWaitMinutes() {
		return MaxWaitMinutes;
	}
	
	public void setMaxWaitMinutes(int MaxWaitMinutes) {
		this.MaxWaitMinutes = MaxWaitMinutes;
	}
}
