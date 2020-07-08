package reviewapp.dal;

import reviewapp.model.*;
import reviewapp.model.Restaurant.Cuisine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class RestaurantDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static RestaurantDao instance = null;
	protected RestaurantDao() {
		connectionManager = new ConnectionManager();
	}
	public static RestaurantDao getInstance() {
		if(instance == null) {
			instance = new RestaurantDao();
		}
		return instance;
	}

	
	public Restaurant create(Restaurant restaurant) throws SQLException {
		String insertRestaurant = "INSERT INTO Restaurant(RestaurantKey, Name, Description, Menu, ListedHours, IsActive, Street1, Street2, City, State, ZipCode, Cuisine, CompanyKey) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRestaurant,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, restaurant.getRestaurantKey());
			insertStmt.setString(2, restaurant.getName());
			insertStmt.setString(3, restaurant.getDescription());
			insertStmt.setString(4, restaurant.getMenu());
			insertStmt.setString(5, restaurant.getListedHours());
			insertStmt.setBoolean(6, restaurant.getIsActive());
			insertStmt.setString(7, restaurant.getStreet1());
			insertStmt.setString(8, restaurant.getStreet2());
			insertStmt.setString(9, restaurant.getCity());
			insertStmt.setString(10, restaurant.getState());
			insertStmt.setNString(11, restaurant.getZipCode());
			insertStmt.setObject(12, restaurant.getCuisine());
			insertStmt.setInt(13, restaurant.getCompanyKey());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int RestaurantKey = -1;
			if(resultKey.next()) {
				RestaurantKey = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			restaurant.setRestaurantKey(RestaurantKey);
			return restaurant;
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


	public Restaurant delete(Restaurant restaurant) throws SQLException {
		String deleteRestaurant = "DELETE FROM Restaurant WHERE RestaurantKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRestaurant);
			deleteStmt.setInt(1, restaurant.getRestaurantKey());
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



	public Restaurant getRestaurantById(int RestaurantKey) throws SQLException {
		String selectRestaurant = "SELECT RestaurantKey, Name, Description,Menu,ListedHours,IsActive,Street1,Street2,City,State,ZipCode,Cuisine,CompanyKey FROM Restaurant WHERE RestaurantKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setInt(1, RestaurantKey);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				int restaurantKey = results.getInt("RestaurantKey");
				String name = results.getString("Name");
				String desc = results.getString("Description");
				String menu = results.getString("Menu");
				String listedHours = results.getString("ListedHours");
				boolean isActive = results.getBoolean("IsActive");
				String street1 = results.getNString("Street1");
				String street2 = results.getNString("Street2");
				String city = results.getNString("City");
				String state = results.getNString("State");
				String zipcode = results.getNString("ZipCode");
				Cuisine cuisineType = (reviewapp.model.Restaurant.Cuisine) results.getObject("Cuisine");
				int companyKey = results.getInt("CompanyKey");
				Restaurant restaurant = new Restaurant(restaurantKey, name, desc, menu, listedHours,
						isActive, street1, street2, city, state, zipcode, cuisineType, companyKey);
				return restaurant;
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
	public List<Restaurant> getRestaurantsByCuisine(Restaurant.Cuisine Cuisine) throws SQLException {
		List<Restaurant> restaurant = new ArrayList<Restaurant>();
		String selectRestaurant =
			"SELECT Name FROM Restaurant WHERE Cuisine=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setString(1, (Cuisine.toString()));
			results = selectStmt.executeQuery();
			while(results.next()) {
				int restaurantKey = results.getInt("RestaurantKey");
				String name = results.getString("Name");
				String desc = results.getString("Description");
				String menu = results.getString("Menu");
				String listedHours = results.getString("ListedHours");
				boolean isActive = results.getBoolean("IsActive");
				String street1 = results.getNString("Street1");
				String street2 = results.getNString("Street2");
				String city = results.getNString("City");
				String state = results.getNString("State");
				String zipcode = results.getNString("ZipCode");
				Restaurant.Cuisine cuisineType = Restaurant.Cuisine.valueOf(results.getString("Cuisine"));
				int companyKey = results.getInt("CompanyKey");
				Restaurant restaurantAdd = new Restaurant(restaurantKey, name, desc, menu, listedHours,
						isActive, street1, street2, city, state, zipcode, cuisineType, companyKey);
				restaurant.add(restaurantAdd);
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
		return restaurant;
	}
	
	public List<Restaurant> getRestaurantsByCompanyName(String companyName) throws SQLException {
		List<Restaurant> restaurant = new ArrayList<Restaurant>();
		String selectCompanyName =
			"SELECT RestaurantKey, Name, Description, Menu, ListedHours, IsActive, Street1, Street2, City, State, ZipCode, Restaurant.CompanyKey "
			+ "FROM Restaurant INNER JOIN Company on Company.CompanyKey = Restaurant.CompanyKey "
			+ "WHERE CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCompanyName);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int rKey = results.getInt("RestaurantKey");
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
				int cKey = results.getInt("Restaurant.CompanyKey");
				Restaurant restaurantAdd = new Restaurant(rKey, name, desc, menu, hours, active, street1, street2, city, state, zipCode, cKey);
				restaurant.add(restaurantAdd);
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
		return restaurant;
	}
}
