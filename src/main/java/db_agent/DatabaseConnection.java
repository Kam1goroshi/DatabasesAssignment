package db_agent;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        InputStream stream = DatabaseConnection.class.getResourceAsStream("/jdbc.properties");
        if(stream == null){
            stream = DatabaseConnection.class.getResourceAsStream("/../../resources/jdbc.properties");
            if(stream == null){
                stream = DatabaseConnection.class.getResourceAsStream("/src/main/resources/jdbc.properties");
            }
        }
        Properties props = new Properties();
        try {
            props.load(stream);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        String jdbcUrl = props.getProperty("jdbc.url");
        String jdbcUser = props.getProperty("jdbc.user");
        String jdbcPassword = props.getProperty("jdbc.password");
        props.clear();
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }
}