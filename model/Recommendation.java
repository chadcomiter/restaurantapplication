package reviewapp.model;


public class Recommendation {
	protected int RecommendationKey;
	protected String UserKey;
	protected int RestaurantKey;
	
	public Recommendation(int RecommendationKey, String UserKey, int RestaurantKey) {
	this.RecommendationKey = RecommendationKey;
	this.UserKey = UserKey;
	this.RestaurantKey = RestaurantKey;
	}

	public Recommendation(int RecommendationKey, String UserKey) {
	this.RecommendationKey = RecommendationKey;
	this.UserKey = UserKey;
	}

	public int getRecommendationKey() {
		return RecommendationKey;
	}
	
	public void setRecommendationKey(int RecommendationKey) {
		this.RecommendationKey = RecommendationKey;
	}
	
	public String getUserName() {
		return UserKey;
	}
	
	public void setUserName(String UserKey) {
		this.UserKey = UserKey;
	}
	
	public int getRestaurantKey() {
		return RestaurantKey;
	}
	
	public void setRestaurantKey(int RestaurantKey) {
		this.RestaurantKey = RestaurantKey;
	}
}
