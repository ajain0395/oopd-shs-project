package shs;

import java.sql.*;

public class BoilerPlateDb {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SHSDB", "root", "abcd1234");
			// here sonoo is database name, root is username and password
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from dummy");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			}
			
			
			String query = " insert into dummy ( name,age)"
			        + " values ( ?, ?)";

			      // create the mysql insert preparedstatement
			      PreparedStatement preparedStmt = con.prepareStatement(query);
			    //  preparedStmt.setInt (1, 2);
			      preparedStmt.setString (1, "Rubble");
			      preparedStmt.setInt   (2, 46);

			      // execute the preparedstatement
			      preparedStmt.execute();
			
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
