package model;

import java.sql.*;

public class FundManagement {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gbdb?useTimezone=true&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return con;
	}

	public String readFundmanagementData() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Name</th>" + "<th>Email</th>" + "<th>address</th>"
					+ "<th>fundAmount</th>" + "<th>interestedCategory</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from fundmanagement";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set

			while (rs.next()) {
				String fid = Integer.toString(rs.getInt("fid"));
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String fundAmount = Double.toString(rs.getDouble("fundAmount"));
				String interestedCategory = rs.getString("interestedCategory");
				
				output += "<td>" + name + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + fundAmount + "</td>";
				output += "<td>" + interestedCategory + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='fundmanagement.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='fid' type='hidden' value='" + fid + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading ";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertfundData(String name, String email, String address, String fundAmount, String interestedCategory) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into fundmanagement (`fid`,`name`,`email`,`address`,`fundAmount`,`interestedCategory`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, address);
			preparedStmt.setDouble(5, Double.parseDouble(fundAmount));
			preparedStmt.setString(6, interestedCategory);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatefundData(String fid, String name, String email, String address, String fundAmount,
			String interestedCategory) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE fundmanagement SET name=?,email=?,address=?,fundAmount=?,interestedCategory=? WHERE fid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, address);
			preparedStmt.setDouble(4, Double.parseDouble(fundAmount));
			preparedStmt.setString(5, interestedCategory);
			preparedStmt.setInt(6, Integer.parseInt(fid));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletefundData(String fid) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from fundmanagement where fid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(fid));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
