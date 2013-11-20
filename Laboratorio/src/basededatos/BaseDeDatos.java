
package basededatos;

import java.sql.*;

public class BaseDeDatos {
    
    protected static Connection instance = null;

    public static Connection getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            String url = "jdbc:postgresql://127.0.0.1:5432/VideoClub";
            String driver = "org.postgresql.Driver";
            String user = "postgres";
            String pass = "123456";
            Class.forName(driver);
            instance = DriverManager.getConnection(url, user, pass);
        }
        return instance;
    }  
}
