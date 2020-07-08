package reviewapp.dal;

import reviewapp.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying Company table in your MySQL
 * instance. This is used to store {@link Persons} into your MySQL instance and retrieve 
 * {@link Persons} from MySQL instance.
 */
public class CompanyDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CompanyDao instance = null;
	protected CompanyDao() {
		connectionManager = new ConnectionManager();
	}
	public static CompanyDao getInstance() {
		if(instance == null) {
			instance = new CompanyDao();
		}
		return instance;
	}

	/**
	 * Save the User instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Company create(Company company) throws SQLException {
		String insertCompany = "INSERT INTO Company(CompanyKey, CompanyName, CompanyDescription) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCompany);
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setInt(1, company.getCompanyKey());
			insertStmt.setString(2, company.getCompanyName());
			insertStmt.setString(3, company.getCompanyDescription());
			// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
			// statements, and it returns an int for the row counts affected (or 0 if the
			// statement returns nothing). For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
			insertStmt.executeUpdate();
			
			// Note 1: if this was an UPDATE statement, then the person fields should be
			// updated before returning to the caller.
			// Note 2: there are no auto-generated keys, so no update to perform on the
			// input param company.
			return company;
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
	 * Delete the user instance.
	 * This runs a DELETE statement.
	 */
	public Company delete(Company company) throws SQLException {
		String deleteCompany = "DELETE FROM Company WHERE CompanyKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCompany);
			deleteStmt.setInt(1, company.getCompanyKey());
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

	/*updateAbout/Content*/
	
	public Company updateContent(Company company, String newAbout) throws SQLException{
		String updateNewAbout = "UPDATE Company SET CompanyDescription=? WHERE CompanyKey=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateNewAbout);
			updateStmt.setString(1, newAbout);
			updateStmt.setInt(2, company.getCompanyKey());
			updateStmt.executeLargeUpdate();
			company.setCompanyDescription(newAbout);
			return company;
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
	/**
	 * Get the Company record by fetching it from my MySQL instance.
	 * This runs a SELECT statement and returns a single Persons instance.
	 */
	public Company getCompanyByCompanyName(String CompanyName) throws SQLException {
		String selectCompany = "SELECT CompanyKey,CompanyName,CompanyDescription FROM Company WHERE CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCompany);
			selectStmt.setString(1, CompanyName);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				int rCompanyKey = results.getInt("CompanyKey");
				String rName = results.getString("CompanyName");
				String rDescription = results.getString("CompanyDescription");
				Company company = new Company(rCompanyKey, rName, rDescription);
				return company;
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
	public List<Company> getCompanysFromCompanyName(String CompanyName) throws SQLException {
		List<Company> company = new ArrayList<Company>();
		String selectCompany =
			"SELECT CompanyKey, Name, Description FROM Company WHERE Name=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCompany);
			selectStmt.setString(1, CompanyName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int rCompanyKey = results.getInt("CompanyKey");
				String rName = results.getString("CompanyName");
				String rDescription = results.getString("CompanyDescription");
				Company companyAdd = new Company(rCompanyKey, rName, rDescription);
				company.add(companyAdd);
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
		return company;
	}
}
