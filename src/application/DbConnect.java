package application;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
//singletion onnection
public class DbConnect {
private static  Connection connection;
public static Connection getConnect () throws SQLException {

		try {
			
			Class.forName("com.mysql.jdbc.Driver");
		connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/banqueprojetfx?zeroDateTimeBehavior=convertToNull", "root","");
           System.out.print("connexion reussite");
			return connection;

		} catch (ClassNotFoundException e) {
			System.err.print("Errreur de chargement de connexion");
			return null;
		}

	}

	/*
	 * Connection obtenirconnexion() { return connect; }
	 */

}


