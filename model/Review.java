package reviewapp.model;

import java.sql.Date;
/* Review */

public class Review {
	protected int ReviewKey;
	protected String UserName;
	protected int RestaurantKey;
	protected Date CreatedWhen;
	protected String WrittenContent;
	protected Float Rating;
	
	public Review(int ReviewKey, String UserName, int RestaurantKey,
			Date CreatedWhen, String WrittenContent, Float Rating) {
		this.ReviewKey = ReviewKey;
		this.UserName = UserName;
		this.RestaurantKey = RestaurantKey;
		this.CreatedWhen = CreatedWhen;
		this.WrittenContent = WrittenContent;
		this.Rating = Rating;
}
	
	public Review(int ReviewKey) {
		this.ReviewKey = ReviewKey;
	}
	
	public Review(String UserName, int RestaurantKey, Date CreatedWhen, String WrittenContent, Float Rating) {
		this.UserName = UserName;
		this.RestaurantKey = RestaurantKey;
		this.CreatedWhen = CreatedWhen;
		this.WrittenContent = WrittenContent;
		this.Rating = Rating;
	}

	public int getReviewKey() {
		return ReviewKey;
	}
	
	public void setReviewKey(int ReviewKey) {
		this.ReviewKey = ReviewKey;
	}

	public String getUserName() {
		return UserName;
	}
	
	public void setUserName(String UserName) {
		this.UserName = UserName;
	}
	
	public int getRestaurantKey() {
		return RestaurantKey;
	}
	
	public void setRestaurantKey(int RestaurantKey) {
		this.RestaurantKey = RestaurantKey;
	}
	
	public Date getCreatedWhen() {
		return CreatedWhen;
	}
	
	public void setCreatedWhen(Date CreatedWhen) {
		this.CreatedWhen = CreatedWhen;
	}
	
	public String getWrittenContent() {
		return WrittenContent;
	}
	
	public void setWrittenContent(String WrittenContent) {
		this.WrittenContent = WrittenContent;
	}
	
	public Float getRating() {
		return Rating;
	}
	
	public void setRating(Float Rating) {
		this.Rating = Rating;
	}
}
