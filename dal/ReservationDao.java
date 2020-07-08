package reviewapp.dal;

import reviewapp.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



public class ReservationDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ReservationDao instance = null;
	protected ReservationDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReservationDao getInstance() {
		if(instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	}


	public Reservation create(Reservation reservation) throws SQLException {
		String insertReservation = "INSERT INTO Reservation(UserName,SitDownRestaurantKey,ReservationKey,Start,End,PartySize) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReservation);
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setString(1, reservation.getUserName());
			insertStmt.setInt(2, reservation.getSitDownRestaurantKey());
			insertStmt.setInt(3,  reservation.getReservationKey());
			insertStmt.setDate(4, reservation.getStart());
			insertStmt.setDate(5, reservation.getEnd());
			insertStmt.setInt(6, reservation.getPartySize());
			insertStmt.executeUpdate();
			return reservation;
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


	public Reservation delete(Reservation reservation) throws SQLException {
		String deleteReservation = "DELETE FROM Reservation WHERE ReservationKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReservation);
			deleteStmt.setInt(1, reservation.getReservationKey());
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

	public Reservation getReservationById(int ReservationKey) throws SQLException {
		String selectUser = "SELECT UserName,SitDownRestaurantKey,ReservationKey,Start,End,PartySize FROM Reservation WHERE ReservationKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setInt(1, ReservationKey);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				String rUser = results.getString("UserName");
				int restKey = results.getInt("SitDownRestaurantKey");
				int revKey = results.getInt("ReservationKey");
				Date start = results.getDate("Start");
				Date end = results.getDate("End");
				int pSize = results.getInt("PartySize");
				Reservation reservation= new Reservation(rUser, restKey, revKey, start, end, pSize);
			return reservation;
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


	public List<Reservation> getReservationsByUserName(String UserName) throws SQLException {
		List<Reservation> reservation = new ArrayList<Reservation>();
		String selectReservationsByUserName =
			"SELECT UserName,SitDownRestaurantKey,ReservationKey,Start,End,PartySize FROM Reservation WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservationsByUserName);
			selectStmt.setString(1, UserName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String rUser = results.getString("UserName");
				int restKey = results.getInt("SitDownRestaurantKey");
				int revKey = results.getInt("ReservationKey");
				Date start = results.getDate("Start");
				Date end = results.getDate("End");
				int pSize = results.getInt("PartySize");
				Reservation resAdd = new Reservation(rUser, restKey, revKey, start, end, pSize);
				reservation.add(resAdd);
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
		return reservation;
	}
	
	public List<Reservation> getReservationsBySitDownRestaurantKey(int SitDownRestaurantKey) throws SQLException {
		List<Reservation> reservation = new ArrayList<Reservation>();
		String selectReservationsBySitDownRestaurantKey =
			"SELECT UserName,SitDownRestaurantKey,ReservationKey,Start,End,PartySize FROM Reservation WHERE SitDownRestaurantKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservationsBySitDownRestaurantKey);
			selectStmt.setInt(1, SitDownRestaurantKey);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String rUser = results.getString("UserName");
				int restKey = results.getInt("SitDownRestaurantKey");
				int revKey = results.getInt("ReservationKey");
				Date start = results.getDate("Start");
				Date end = results.getDate("End");
				int pSize = results.getInt("PartySize");
				Reservation resAdd = new Reservation(rUser, restKey, revKey, start, end, pSize);
				reservation.add(resAdd);
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
		return reservation;
	}
}
