package isms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import isms.common.Supplier;

public abstract class BaseDao {

	protected interface PreparedStatementOperation {

		public void apply(PreparedStatement stmt) throws SQLException;
	}

	private Supplier<Connection> supplier;

	public void setSupplier(Supplier<Connection> supplier) {
		this.supplier = supplier;
	}

	protected void run(String sql, PreparedStatementOperation op) {
		PreparedStatement stmt = null;
		Connection conn = null;

		try {
			try {
				conn = supplier.get();
				op.apply(stmt = conn.prepareStatement(sql));
			} finally {
				if (conn != null) conn.close();
				if (stmt != null) stmt.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
