package isms.services;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.PoolProperties;

import isms.common.Supplier;

public class ConnectionSupplier implements Supplier<Connection> {

	private static final DataSource source = init();

	private static DataSource init() {
		PoolProperties p = new PoolProperties();
		p.setUrl("jdbc:mysql://localhost/isms?user=isms_user&password=isms&useSSL=false");
		p.setDriverClassName("com.mysql.cj.jdbc.Driver");

		return new org.apache.tomcat.jdbc.pool.DataSource(p);
	}

	public Connection get() {
		Connection conn = null;
		try {
			conn = source.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return conn;
	}
}
