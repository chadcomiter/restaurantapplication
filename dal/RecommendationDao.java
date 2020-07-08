package reviewapp.dal;

import reviewapp.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class RecommendationDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static RecommendationDao instance = null;
	protected RecommendationDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecommendationDao getInstance() {
		if(instance == null) {
			instance = new RecommendationDao();
		}
		return instance;
	}


	public Recommendation create(Recommendation recommendation) throws SQLException {
		String insertRecommendation = "INSERT INTO Recommendation(RecommendationKey, UserKey, RestaurantKey) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecommendation);
			insertStmt.setInt(1, recommendation.getRecommendationKey());
			insertStmt.setString(2, recommendation.getUserName());
			insertStmt.setInt(3, recommendation.getRestaurantKey());
			insertStmt.executeUpdate();
			return recommendation;
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
	 * Delete the recommendation instance.
	 * This runs a DELETE statement.
	 */
	public Recommendation delete(Recommendation recommendation) throws SQLException {
		String deleteRecommendation = "DELETE FROM Recommendation WHERE RecommendationKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecommendation);
			deleteStmt.setInt(1, recommendation.getRecommendationKey());
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

	/**
	 * Get the Recommendation record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Persons instance.
	 */
	public Recommendation getRecommendationByRecommendationKey(int recommendationKey) throws SQLException {
		String selectRecommendation = "SELECT RecommendationKey, UserKey, RestaurantKey FROM Recommendation WHERE RecommendationKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendation);
			selectStmt.setInt(1, recommendationKey);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int recKey = results.getInt("RecommendationKey");
				String user = results.getString("UserKey");
				int restKey = results.getInt("RestaurantKey");
				Recommendation recommendation = new Recommendation(recKey, user, restKey);
				return recommendation;
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
	


	public List<Recommendation> getRecommendationsByRestaurantKey(int RestaurantKey) throws SQLException {
		List<Recommendation> recommendation = new ArrayList<Recommendation>();
		String selectRecommendation =
			"SELECT RecommendationKey, UserKey, RestaurantKey FROM Recommendation WHERE RestaurantKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendation);
			selectStmt.setInt(1, RestaurantKey);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int recKey = results.getInt("RecommendationKey");
				String user = results.getString("UserKey");
				int restKey = results.getInt("RestaurantKey");
				Recommendation recAdd = new Recommendation(recKey, user, restKey);
				recommendation.add(recAdd);
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
		return recommendation;
	}
	
	public List<Recommendation> getRecommendationsByUserKey(String userKey) throws SQLException {
		List<Recommendation> recommendation = new ArrayList<Recommendation>();
		String selectRecommendation =
				"SELECT RecommendationKey, UserKey, RestaurantKey FROM Recommendation WHERE UserKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendation);
			selectStmt.setString(1, userKey);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int recKey = results.getInt("RecommendationKey");
				String user = results.getString("UserKey");
				int restKey = results.getInt("RestaurantKey");
				Recommendation recAdd = new Recommendation(recKey, user, restKey);
				recommendation.add(recAdd);
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
		return recommendation;
	}
}
