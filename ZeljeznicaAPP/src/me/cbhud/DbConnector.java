package me.cbhud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class DbConnector {

	private String user;
	private String pass;
	private String url;

	public DbConnector(String user, String pass, String host, String port, String database) {
		super();
		this.user = user;
		this.pass = pass;
		this.url = "jdbc:mysql://" + host + ":" + port + "/" + database;
	}

	public Connection open() {
		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean close(Connection conn) {
		try {
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	

}