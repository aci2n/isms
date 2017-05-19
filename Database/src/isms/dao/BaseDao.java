package isms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BaseDao {

	protected interface PreparedStatementOperation {

		public void apply(PreparedStatement stmt) throws SQLException;
	}

	private static final String url = "jdbc:mysql://localhost/isms?user=isms_user&password=isms&useSSL=false";
	private Connection connection;

	protected Connection connection() throws SQLException {
		if (connection == null) {
			connection = DriverManager.getConnection(url);
		}
		return connection;
	}

	protected void run(String sql, PreparedStatementOperation op) {
		PreparedStatement stmt = null;

		try {
			try {
				op.apply(stmt = connection().prepareStatement(sql));
			} finally {
				if (stmt != null) stmt.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("Could not free database resources.");
		}
	}

}
