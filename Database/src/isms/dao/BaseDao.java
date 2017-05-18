package isms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDao {

	protected interface PreparedStatementOperation {

		public ResultSet apply(PreparedStatement stmt) throws SQLException;
	}

	protected Connection connection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost/isms?user=isms_user&password=isms&useSSL=false");
	}

	protected ResultSet run(String sql, PreparedStatementOperation op) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet res = null;

		// Nested tries! If it gets to the second one it should shut down.
		try {
			try {
				conn = connection();
				stmt = conn.prepareStatement(sql);
				res = op.apply(stmt);
			} finally {
				if (conn != null) conn.close();
				if (stmt != null) stmt.close();
				if (res != null) res.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("Could not free database resources.");
		}

		return res;
	}

}
