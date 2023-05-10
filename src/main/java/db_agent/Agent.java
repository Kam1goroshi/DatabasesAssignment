package db_agent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class Agent {
    /**
     * Adds a customer to the database
     * @param PhysicalEntity
     * @return boolean that indicates success
     */
    public boolean addEntity(PhysicalEntity physicalEntity){
        try{
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(physicalEntity.getInsertStatement());
        stmt.close();
        conn.close();
        return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /** 
     * Will implement things like setters to update physical entities later if you need them
     * -Giorgos
     */
}
