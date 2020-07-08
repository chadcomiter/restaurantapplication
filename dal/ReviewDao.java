package reviewapp.dal;

import reviewapp.model.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying Persons table in your MySQL
 * instance. This is used to store {@link Persons} into your MySQL instance and retrieve 
 * {@link Persons} from MySQL instance.
 */
public class ReviewDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ReviewDao instance = null;
	protected ReviewDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewDao getInstance() {
		if(instance == null) {
			instance = new ReviewDao();
		}
		return instance;
	}

	/**
	 * Save the Persons instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Review create(Review review) throws SQLException {
		String insertReview = "INSERT INTO Review(ReviewKey, UserName, RestaurantKey, CreatedWhen, WrittenContent, Rating) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview);
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setInt(1, review.getReviewKey());
			insertStmt.setString(2, review.getUserName());
			insertStmt.setInt(3, review.getRestaurantKey());
			insertStmt.setDate(4, review.getCreatedWhen());
			insertStmt.setString(5, review.getWrittenContent());
			insertStmt.setFloat(6, review.getRating());

			// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
			// statements, and it returns an int for the row counts affected (or 0 if the
			// statement returns nothing). For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
			insertStmt.executeUpdate();
			
			// Note 1: if this was an UPDATE statement, then the person fields should be
			// updated before returning to the caller.
			// Note 2: there are no auto-generated keys, so no update to perform on the
			// input param person.
			return review;
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
	 * Delete the Review instance.
	 * This runs a DELETE statement.
	 */
	public Review delete(Review review) throws SQLException {
		String deleteReview= "DELETE FROM Review WHERE ReviewKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getReviewKey());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Review instance.
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
	 * Get the Review record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Persons instance.
	 */
	public Review getReviewById(int ReviewKey) throws SQLException {
		String selectReview = "SELECT ReviewKey, UserName, RestaurantKey, CreatedWhen, WrittenContent, Rating FROM Review WHERE ReviewKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, ReviewKey);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				int rReviewKey = results.getInt("ReviewKey");
				String rUserName = results.getString("UserName");
				int rRestaurantKey = results.getInt("RestaurantKey");
				Date rCreatedWhen = results.getDate("CreatedWhen");
				String rWrittenContent = results.getString("WrittenContent");
				Float rRating = results.getFloat("Rating");
				Review Review = new Review(rReviewKey, rUserName, rRestaurantKey, 
						rCreatedWhen, rWrittenContent, rRating);
				return Review;
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

	/*GetReviewByUserName*/
	public List<Review> getReviewByUserName(String UserName) throws SQLException {
		List<Review> review = new ArrayList<Review>();
		String selectReview =
			"SELECT ReviewKey, UserName, RestaurantKey, CreatedWhen, WrittenContent, Rating FROM Review WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setString(1, UserName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int rReviewKey = results.getInt("ReviewKey");
				String rUserName = results.getString("UserName");
				int rRestaurantKey = results.getInt("RestaurantKey");
				Date rCreatedWhen = results.getDate("CreatedWhen");
				String rWrittenContent = results.getString("WrittenContent");
				Float rRating = results.getFloat("Rating");
				Review reviewAdd = new Review(rReviewKey, rUserName, rRestaurantKey, rCreatedWhen, rWrittenContent, rRating);
				review.add(reviewAdd);
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
		return review;
	}
	
	/*GetReviewByRestaurantKey(ID)*/
	public List<Review> getReviewByRestaurantKey(int RestaurantKey) throws SQLException {
		List<Review> review = new ArrayList<Review>();
		String selectReview =
			"SELECT ReviewKey, UserName, RestaurantKey, CreatedWhen, WrittenContent, Rating FROM Review WHERE RestaurantKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, RestaurantKey);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int rReviewKey = results.getInt("ReviewKey");
				String rUserName = results.getString("UserName");
				int rRestaurantKey = results.getInt("RestaurantKey");
				Date rCreatedWhen = results.getDate("CreatedWhen");
				String rWrittenContent = results.getString("WrittenContent");
				Float rRating = results.getFloat("Rating");
				Review reviewAdd = new Review(rReviewKey, rUserName, rRestaurantKey, rCreatedWhen, rWrittenContent, rRating);
				review.add(reviewAdd);
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
		return review;
	}
}
