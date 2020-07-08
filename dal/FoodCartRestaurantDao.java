package reviewapp.dal;

import reviewapp.model.*;
import reviewapp.model.Restaurant.Cuisine;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class FoodCartRestaurantDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static FoodCartRestaurantDao instance = null;
	protected FoodCartRestaurantDao() {
		connectionManager = new ConnectionManager();
	}
	public static FoodCartRestaurantDao getInstance() {
		if(instance == null) {
			instance = new FoodCartRestaurantDao();
		}
		return instance;
	}


	public FoodCartRestaurant create(FoodCartRestaurant foodCartRestaurant) throws SQLException {
		String insertFoodCart = "INSERT INTO FoodCartRestaurant(FoodCartRestaurantKey, Name, Description, Menu, ListedHours, IsActive, Street1, Street2, City, State, ZipCode, Cuisine, CompanyKey, IsLicensed) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFoodCart);
			insertStmt.setInt(1, foodCartRestaurant.getFoodCartRestaurantKey());
			insertStmt.setString(2, foodCartRestaurant.getName());
			insertStmt.setString(3, foodCartRestaurant.getDescription());
			insertStmt.setString(4, foodCartRestaurant.getMenu());
			insertStmt.setString(5, foodCartRestaurant.getListedHours());
			insertStmt.setBoolean(6, foodCartRestaurant.getIsActive());
			insertStmt.setString(7, foodCartRestaurant.getStreet1());
			insertStmt.setString(8, foodCartRestaurant.getStreet2());
			insertStmt.setString(9, foodCartRestaurant.getCity());
			insertStmt.setString(10, foodCartRestaurant.getState());
			insertStmt.setNString(11, foodCartRestaurant.getZipCode());
			insertStmt.setString(12, foodCartRestaurant.getCuisine().toString());
			insertStmt.setInt(13, foodCartRestaurant.getCompanyKey());
			insertStmt.setBoolean(14, foodCartRestaurant.getIsLicensed());
			insertStmt.executeUpdate();
			return foodCartRestaurant;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	public FoodCartRestaurant delete(FoodCartRestaurant foodCartRestaurant) throws SQLException {
		String deleteCart = "DELETE FROM FoodCartRestaurant WHERE FoodCartRestaurantKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCart);
			deleteStmt.setInt(1, foodCartRestaurant.getFoodCartRestaurantKey());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the User instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public FoodCartRestaurant getFoodCartRestaurantById(int foodCartRestaurantKey) throws SQLException {
		String selectCart = "SELECT FoodCartRestaurantKey, Name, Description, Menu, ListedHours, IsActive, Street1, Street2, City, State, ZipCode, Cuisine, CompanyKey, IsLicensed FROM FoodCartRestaurant WHERE FoodCartRestaurantKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCart);
			selectStmt.setInt(1, foodCartRestaurantKey);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				int rKey = results.getInt("FoodCartRestaurantKey");
				String name = results.getString("Name");
				String desc = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("ListedHours");
				boolean active = results.getBoolean("IsActive");
				String street1 = results.getNString("Street1");
				String street2 = results.getNString("Street2");
				String city = results.getNString("City");
				String state = results.getNString("State");
				String zipCode = results.getNString("ZipCode");
				Restaurant.Cuisine cuisineType = Restaurant.Cuisine.valueOf(results.getString("Cuisine"));
				int cKey = results.getInt("CompanyKey");
				boolean license = results.getBoolean("IsLicensed");
				FoodCartRestaurant foodCartRestaurant = new FoodCartRestaurant(rKey, name, desc, menu, hours, active, street1, street2, city, state, zipCode, cuisineType, cKey, license);
				return foodCartRestaurant;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	/**
	 * Get the matching Persons records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching Persons.
	 */
	public List<FoodCartRestaurant> getFoodCartsByCompanyName(String companyName) throws SQLException {
		List<FoodCartRestaurant> foodCartRestaurant = new ArrayList<FoodCartRestaurant>();
		String selectCart =
			"SELECT FoodCartRestaurantKey, Name, Description, Menu, ListedHours, IsActive, Street1, Street2, City, State, ZipCode, Cuisine, FoodCartRestaurant.CompanyKey, IsLicensed "
			+ "FROM FoodCartRestaurant INNER JOIN Company on Company.CompanyKey = FoodCartRestaurant.CompanyKey "
			+ "WHERE CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCart);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int rKey = results.getInt("FoodCartRestaurantKey");
				String name = results.getString("Name");
				String desc = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("ListedHours");
				boolean active = results.getBoolean("IsActive");
				String street1 = results.getNString("Street1");
				String street2 = results.getNString("Street2");
				String city = results.getNString("City");
				String state = results.getNString("State");
				String zipCode = results.getNString("ZipCode");
				Restaurant.Cuisine cuisineType = Restaurant.Cuisine.valueOf(results.getString("Cuisine"));
				int cKey = results.getInt("FoodCartRestaurant.CompanyKey");
				boolean license = results.getBoolean("IsLicensed");
				
				FoodCartRestaurant foodCartRestaurantAdd = new FoodCartRestaurant(rKey, name, desc, menu, hours, active, street1, street2, city, state, zipCode, cuisineType, cKey, license);
				foodCartRestaurant.add(foodCartRestaurantAdd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return foodCartRestaurant;
	}
}
