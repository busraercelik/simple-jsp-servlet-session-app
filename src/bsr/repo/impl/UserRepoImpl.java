package bsr.repo.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bsr.model.User;
import bsr.repo.UserRepo;

public class UserRepoImpl implements UserRepo {

	public String driver = "com.mysql.cj.jdbc.Driver";
	public String DB_URL = "jdbc:mysql://localhost:3306/personel_note_db";
	public String DB_USERNAME = "root";
	public String DB_PASSWORD = "root";

	public User readAllUser() {
		User user = new User();
		try {
			// className
			Class.forName(driver);
			/*
			 * Attempts to establish a connection to the given database URL. The
			 * DriverManager attempts to select an appropriate driver from the set of
			 * registered JDBC drivers.
			 */
			Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			// Creates a Statement object for sendingSQL statements to the database
			Statement statement = con.createStatement();
			System.out.println("Connected to db!");

			String sql = "SELECT * FROM USER";
			// Executes the given SQL statement, which returns a single ResultSet object.
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				String username = rs.getString("USERNAME");
				user.setUsername(username);
				System.out.println("username: " + username);
			}
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public void insertUser(User user) {
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

			String sql = "INSERT INTO USER (USERNAME, PASSWORD, EMAIL) VALUES (?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());

			ps.executeUpdate();

			con.close();
			System.out.println("User added");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public User getUserByUsername(String username) {
		User user = null;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			String query = "select * from user where username=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setUsername(username);
				user.setPassword(rs.getString("password"));
				user.setId(rs.getLong("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return user;
	}

}
