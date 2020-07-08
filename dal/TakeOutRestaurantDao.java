package reviewapp.dal;

import reviewapp.model.*;
import reviewapp.model.Restaurant.Cuisine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TakeOutRestaurantDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static TakeOutRestaurantDao instance = null;
	protected TakeOutRestaurantDao() {
		connectionManager = new ConnectionManager();
	}
	public static TakeOutRestaurantDao getInstance() {
		if(instance == null) {
			instance = new TakeOutRestaurantDao();
		}
		return instance;
	}


	public TakeOutRestaurant create(TakeOutRestaurant takeOutRestaurant) throws SQLException {
		String insertTakeOut = "INSERT INTO TakeOutRestaurant(TakeOutRestaurantKey, Name, Description, Menu, ListedHours, IsActive, Street1, Street2, City, State, ZipCode, Cuisine, CompanyKey, MaxWaitMinutes) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTakeOut);
			insertStmt.setInt(1, takeOutRestaurant.getTakeOutRestaurantKey());
			insertStmt.setString(2, takeOutRestaurant.getName());
			insertStmt.setString(3, takeOutRestaurant.getDescription());
			insertStmt.setString(4, takeOutRestaurant.getMenu());
			insertStmt.setString(5, takeOutRestaurant.getListedHours());
			insertStmt.setBoolean(6, takeOutRestaurant.getIsActive());
			insertStmt.setString(7, takeOutRestaurant.getStreet1());
			insertStmt.setString(8, takeOutRestaurant.getStreet2());
			insertStmt.setString(9, takeOutRestaurant.getCity());
			insertStmt.setString(10, takeOutRestaurant.getState());
			insertStmt.setNString(11, takeOutRestaurant.getZipCode());
			insertStmt.setString(12, takeOutRestaurant.getCuisine().toString());
			insertStmt.setInt(13, takeOutRestaurant.getCompanyKey());
			insertStmt.setInt(14, takeOutRestaurant.getMaxWaitMinutes());
			insertStmt.executeUpdate();
			return takeOutRestaurant;
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


	/**
	 * Delete the takeOutRestaurant instance.
	 * This runs a DELETE statement.
	 */
	public TakeOutRestaurant delete(TakeOutRestaurant takeOutRestaurant) throws SQLException {
		String deleteTakeOut = "DELETE FROM TakeOutRestaurant WHERE TakeOutRestaurantKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTakeOut);
			deleteStmt.setInt(1, takeOutRestaurant.getTakeOutRestaurantKey());
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

	public TakeOutRestaurant getTakeOutRestaurantByTakeOutRestaurantKey(int takeOutRestaurantKey) throws SQLException {
		String selectTakeOutRestaurant = "SELECT TakeOutRestaurantKey, Name, Description, Menu, ListedHours, IsActive, Street1, Street2, City, State, ZipCode, Cuisine, CompanyKey, MaxWaitMinutes FROM TakeOutRestaurant WHERE TakeOutRestaurantKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTakeOutRestaurant);
			selectStmt.setInt(1, takeOutRestaurantKey);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				int rKey = results.getInt("TakeOutRestaurantKey");
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
				Restaurant.Cuisine cuisineType = Restaurant.Cuisine.valueOf(results.getNString("Cuisine"));
				int cKey = results.getInt("CompanyKey");
				int wait = results.getInt("MaxWaitMinutes");
				TakeOutRestaurant takeOutRestaurant = new TakeOutRestaurant(rKey, name, desc, menu, hours, active, street1, street2, city, state, zipCode, cuisineType, cKey, wait);
				return takeOutRestaurant;
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

	
	public List<TakeOutRestaurant> getTakeOutRestaurantsByCompanyName(String companyName) throws SQLException {
		List<TakeOutRestaurant> takeOutRestaurant = new ArrayList<TakeOutRestaurant>();
		String selectTakeOutRestaurant =
			"SELECT TakeOutRestaurantKey, Name, Description, Menu, ListedHours, IsActive, Street1, Street2, City, State, ZipCode, Cuisine, TakeOutRestaurant.CompanyKey, MaxWaitMinutes FROM TakeOutRestaurant INNER JOIN Company on TakeOutRestaurant.CompanyKey = Company.CompanyKey "
			+ "WHERE Company.CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTakeOutRestaurant);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int rKey = results.getInt("TakeOutRestaurantKey");
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
				Restaurant.Cuisine cuisineType = Restaurant.Cuisine.valueOf(results.getNString("Cuisine"));
				int cKey = results.getInt("TakeOutRestaurant.CompanyKey");
				int wait = results.getInt("MaxWaitMinutes");
				
				TakeOutRestaurant takeOutRestaurantAdd = new TakeOutRestaurant(rKey, name, desc, menu, hours, active, street1, street2, city, state, zipCode, cuisineType, cKey, wait);
				takeOutRestaurant.add(takeOutRestaurantAdd);
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
		return takeOutRestaurant;
	}
}
