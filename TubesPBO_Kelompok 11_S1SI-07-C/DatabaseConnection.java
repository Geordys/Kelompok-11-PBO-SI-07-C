
package TubesPBO;

/**
 *
 * @author Geordy Sipho Samuel Damanik
 * 2311103112
 * S1SI-07-C
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
     private static final String URL = "jdbc:mysql://localhost:3306/db_rumahsakit";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println("Error koneksi: " + e.getMessage());
            return null;
        }
    }
}
