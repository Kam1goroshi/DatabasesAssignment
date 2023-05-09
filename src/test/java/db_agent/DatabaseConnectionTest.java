package db_agent;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import db_agent.DatabaseConnectionTest;
import java.sql.*;
public class DatabaseConnectionTest {
    
    @Test
    void connection(){
        Connection conn = null;
        try{
            conn = DatabaseConnection.getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
        assertNotNull(conn, "Connection is null");
    }
}
