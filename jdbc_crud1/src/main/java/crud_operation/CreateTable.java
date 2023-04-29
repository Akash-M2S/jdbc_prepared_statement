package crud_operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CreateTable {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "root");
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"create table studentdetails(std_id int primary key,name varchar(50) not null,branch varchar(20),phone bigint unique)");
			preparedStatement.execute();
			System.out.println("table created");
			connection.close();
		} catch (SQLException e) {
			System.out.println("table exist");
		}
	}
}
