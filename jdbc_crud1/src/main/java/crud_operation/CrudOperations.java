package crud_operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CrudOperations {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "root");
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.out.println("1)add the students" + "\n" + "2)Update student branch" + "\n"
					+ "3)delete the student details" + "\n" + "4)delete all the students" + "\n"
					+ "5)fetch student details" + "\n" + "6)fetch all the student details" + "\n" + "7)exit");
			System.out.println("select your option");
			switch (scanner.nextInt()) {
			case 1: {
				try {
					PreparedStatement preparedStatement = connection
							.prepareStatement("insert into studentdetails values(?,?,?,?)");
					System.out.println("enter the student id");
					int id = scanner.nextInt();
					scanner.nextLine();
					System.out.println("enter the student name");
					String name = scanner.nextLine();
					System.out.println("enter the student branch");
					String branch = scanner.nextLine();
					System.out.println("enter the student Mobile number");
					long mobile = scanner.nextLong();
					preparedStatement.setInt(1, id);
					preparedStatement.setString(2, name);
					preparedStatement.setString(3, branch);
					preparedStatement.setLong(4, mobile);
					int e = preparedStatement.executeUpdate();
					System.out.println(e + " row is added");
				} catch (SQLException e) {
					System.out.println(e.getMessage());

				}
			}
				break;
			case 2: {
				try {
					PreparedStatement preparedStatement = connection
							.prepareStatement("Update  studentdetails set branch=? where std_id=?");
					System.out.println("enter the student id");
					preparedStatement.setInt(2, scanner.nextInt());
					System.out.println("enter the new branch");
					scanner.nextLine();
					preparedStatement.setString(1, scanner.nextLine());
					int e = preparedStatement.executeUpdate();
					if (e == 1)
						System.out.println("value is updated" + "\n");
					else {
						System.out.println("enter correct id");
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
				break;
			case 3: {
				PreparedStatement preparedStatement = connection
						.prepareStatement("delete from studentdetails  where std_id=?");
				System.out.println("enter the student id");
				preparedStatement.setInt(1, scanner.nextInt());
				int a = preparedStatement.executeUpdate();
				System.out.println(a + " row is deleted");

			}
				break;
			case 4: {
				PreparedStatement preparedStatement = connection.prepareStatement("delete from studentdetails ");
				int a = preparedStatement.executeUpdate();
				System.out.println(a + " row is deleted");
			}
				break;
			case 5: {
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from studentdetails  where std_id=?");
				System.out.println("enter the student id to see the details");
				preparedStatement.setInt(1, scanner.nextInt());
				ResultSet resultSet = preparedStatement.executeQuery();
				boolean flag1 = resultSet.next();
				if (flag1) {
					System.out.println("std_id=" + resultSet.getInt(1) + "\n" + "Name=" + resultSet.getString(2) + "\n"
							+ "branch=" + resultSet.getString(3) + "\n" + "mobile=" + resultSet.getLong(4));
				} else {
					System.out.println("data not available");
				}

			}
				break;
			case 6: {
				PreparedStatement preparedStatement = connection.prepareStatement("select * from studentdetails");
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					System.out.println("std_id=" + resultSet.getInt(1) + "\t" + "Name=" + resultSet.getString(2) + "\t"
							+ "branch=" + resultSet.getString(3));
					System.out.println("**********************(- _ -)******************************");
				}

			}
				break;
			case 7: {
				flag = false;
				System.out.println("thank you");
			}
				break;
			default:
				System.out.println("invalid options");
			}
		}

	}

}
