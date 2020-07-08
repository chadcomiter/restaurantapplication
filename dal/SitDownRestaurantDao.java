package reviewapp.dal;

import reviewapp.model.*;
import reviewapp.model.Restaurant.Cuisine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SitDownRestaurantDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static SitDownRestaurantDao instance = null;
	protected SitDownRestaurantDao() {
		connectionManager = new ConnectionManager();
	}
	public static SitDownRestaurantDao getInstance() {
		if(instance == null) {
			instance = new SitDownRestaurantDao();
		}
		return instance;
	}

	/**
	 * Save the Persons instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public SitDownRestaurant create(SitDownRestaurant sitDownRestaurant) throws SQLException {
		String insertSitDownRestaurant = "INSERT INTO SitDownRestaurant(SitDownRestaurantKey, Name, Description, Menu, ListedHours, IsActive, Street1, Street2, City, State, ZipCode, Cuisine, CompanyKey, Capacity) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSitDownRestaurant);
			insertStmt.setInt(1, sitDownRestaurant.getSitDownRestaurantKey());
			insertStmt.setString(2, sitDownRestaurant.getName());
			insertStmt.setString(3, sitDownRestaurant.getDescription());
			insertStmt.setString(4, sitDownRestaurant.getMenu());
			insertStmt.setString(5, sitDownRestaurant.getListedHours());
			insertStmt.setBoolean(6, sitDownRestaurant.getIsActive());
			insertStmt.setString(7, sitDownRestaurant.getStreet1());
			insertStmt.setString(8, sitDownRestaurant.getStreet2());
			insertStmt.setString(9, sitDownRestaurant.getCity());
			insertStmt.setString(10, sitDownRestaurant.getState());
			insertStmt.setNString(11, sitDownRestaurant.getZipCode());
			insertStmt.setString(12, sitDownRestaurant.getCuisine().toString());
			insertStmt.setInt(13, sitDownRestaurant.getCompanyKey());
			insertStmt.setInt(14, sitDownRestaurant.getCapacity());
			insertStmt.executeUpdate();
			return sitDownRestaurant;
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
	 * Delete the sitDownRestaurant instance.
	 * This runs a DELETE statement.
	 */
	public SitDownRestaurant delete(SitDownRestaurant sitDownRestaurant) throws SQLException {
		String deleteSitDownRestaurant = "DELETE FROM SitDownRestaurant WHERE SitDownRestaurantKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSitDownRestaurant);
			deleteStmt.setInt(1, sitDownRestaurant.getSitDownRestaurantKey());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the sitDownRestaurant instance.
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

	/**
	 * Get the User record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Persons instance.
	 */
	public SitDownRestaurant getSitDownRestuarantBySitDownRestaurantKey(int sitDownRestaurantKey) throws SQLException {
		String selectSitDownRestaurant = "SELECT SitDownRestaurantKey, Name, Description, Menu, ListedHours, IsActive, Street1, Street2, City, State, ZipCode, Cuisine, CompanyKey, Capacity FROM SitDownRestaurant WHERE SitDownRestaurantKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSitDownRestaurant);
			selectStmt.setInt(1, sitDownRestaurantKey);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				int rKey = results.getInt("SitDownRestaurantKey");
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
				int capacity = results.getInt("Capacity");
				SitDownRestaurant sitDownRestaurant = new SitDownRestaurant(rKey, name, desc, menu, hours, active, street1, street2, city, state, zipCode,cuisineType,cKey, capacity);
				return sitDownRestaurant;
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

	public List<SitDownRestaurant> getSitDownRestaurantsByCompanyName(String companyName) throws SQLException {
		List<SitDownRestaurant> sitDownRestaurant = new ArrayList<SitDownRestaurant>();
		String selectSitDownRestaurant =
			"SELECT SitDownRestaurantKey, Name, Description, Menu, ListedHours, IsActive, Street1, Street2, City, State, ZipCode, Cuisine, SitDownRestaurant.CompanyKey, Capacity "
			+ "FROM SitDownRestaurant INNER JOIN Company "
			+ "ON Company.CompanyKey = SitDownRestaurant.CompanyKey "
			+ "WHERE CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSitDownRestaurant);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int rKey = results.getInt("SitDownRestaurantKey");
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
				int cKey = results.getInt("SitDownRestaurant.CompanyKey");
				int capacity = results.getInt("Capacity");
				
				SitDownRestaurant sitDownRestaurantAdd = new SitDownRestaurant(rKey, name, desc, menu, hours, active, street1, street2, city, state, zipCode, cuisineType, cKey, capacity);
				sitDownRestaurant.add(sitDownRestaurantAdd);
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
		return sitDownRestaurant;
	} 
}
