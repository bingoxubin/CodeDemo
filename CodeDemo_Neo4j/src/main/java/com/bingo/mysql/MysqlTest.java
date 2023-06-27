package com.bingo.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author bingoabin
 * @date 2023/6/27 16:56
 * @Description:
 */
public class MysqlTest {
	public static void main(String[] args){
		// addRow();
		deleteRow();
	}

	public static void addRow(){
		String url = "jdbc:mysql://localhost:3306/neo4j";
		String username = "root";
		String password = "111111";

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			String sql = "INSERT INTO Users (username, email, address) VALUES (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "bingo");
			statement.setString(2, "test@example.com");
			statement.setString(3, "test address");
			statement.executeUpdate();
			System.out.println("Data inserted successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteRow(){
		String url = "jdbc:mysql://localhost:3306/neo4j";
		String username = "root";
		String password = "111111";

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			String sql = "DELETE FROM Users WHERE userID = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, 2); // Assuming the user ID to delete is 1
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Data deleted successfully.");
			} else {
				System.out.println("No matching data found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
