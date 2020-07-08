package reviewapp.dal;

import reviewapp.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CreditCardDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CreditCardDao instance = null;
	protected CreditCardDao() {
		connectionManager = new ConnectionManager();
	}
	public static CreditCardDao getInstance() {
		if(instance == null) {
			instance = new CreditCardDao();
		}
		return instance;
	}

/*public CreditCard create(CreditCard creditCard) */
	
	public CreditCard create(CreditCard creditCard) throws SQLException {
		String insertCard = "INSERT INTO CreditCard(CreditCardKey, NameOnCard, CardNum, CardExpiration, UserName) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCard);
			insertStmt.setInt(1, creditCard.getCreditCardKey());
			insertStmt.setString(2, creditCard.getNameOnCard());
			insertStmt.setLong(3, creditCard.getCardNum());;
			insertStmt.setDate(4, creditCard.getCardExpiration());
			insertStmt.setString(5, creditCard.getUserName());
			insertStmt.executeUpdate();
			return creditCard;
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

/*public CreditCard delete(CreditCard creditCard*/

	public CreditCard delete(CreditCard creditCard) throws SQLException {
		String deleteCard = "DELETE FROM CreditCard WHERE CreditCardKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCard);
			deleteStmt.setInt(1, creditCard.getCreditCardKey());
			deleteStmt.executeUpdate();
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
/*updateExpiration*/
	
	public CreditCard updateContent(CreditCard creditCard, java.sql.Date CardExpiration) throws SQLException{
		String updateExpiration = "UPDATE CreditCard SET CardExpiration=? WHERE CreditCardKey=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateExpiration);
			updateStmt.setDate(1, CardExpiration);
			updateStmt.setInt(2, creditCard.getCreditCardKey());
			updateStmt.executeLargeUpdate();
			creditCard.setCardExpiration(CardExpiration);
			return creditCard;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt !=null) {
				updateStmt.close();
			}
		}
	}
	
/*public CreditCard getCreditCardByCardNum*/
	
	public CreditCard getCreditCardByCardNumber(long CardNum) throws SQLException {
		String selectCard = "SELECT CreditCardKey, NameOnCard, CardNum, CardExpiration, UserName FROM CreditCard WHERE CardNum=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCard);
			selectStmt.setLong(1, CardNum);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int rKey = results.getInt("CreditCardKey");
				String rName = results.getString("NameOnCard");
				long rNum= results.getLong("CardNum");
				java.sql.Date rExp = results.getDate("CardExpiration");
				String rUserName = results.getString("UserName");
				CreditCard creditCard = new CreditCard(rKey, rName, rNum, rExp, rUserName);
				return creditCard;
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

/*getCreditCardsbyUserName(String userName)*/
	
	public List<CreditCard> getCreditCardsFromUserName(String userName) throws SQLException {
		List<CreditCard> creditCard = new ArrayList<CreditCard>();
		String selectCard =
			"SELECT CreditCardKey, NameOnCard, CardNum, CardExpiration, UserName FROM CreditCard WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCard);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int rKey = results.getInt("CreditCardKey");
				String rName = results.getString("NameOnCard");
				long rNum= results.getLong("CardNum");
				java.sql.Date rExp = results.getDate("CardExpiration");
				String rUserName = results.getString("UserName");
				CreditCard cardAdd = new CreditCard(rKey, rName, rNum, rExp, rUserName);
				creditCard.add(cardAdd);
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
		return creditCard;
	}
}
