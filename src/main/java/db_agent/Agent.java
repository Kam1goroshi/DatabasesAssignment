package db_agent;

import java.sql.Connection;
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
     * Deletes a row from the database for this agent
     * @param rowId
     * @return true if atleast 1 row was affected
     */
    public boolean deleteCustomer(String rowId){
        if()
        try{
            Connection conn = DatabaseConnection.getConnection();
            int affectedRowsCount = conn.createStatement().executeUpdate("DELETE FROM customers WHERE ID=\"" + rowId + "\"");
            conn.close();
            if(affectedRowsCount > 0)
                return true;
            else
                return false;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /** 
     * Will implement things like updates to physical entities later if needed
     * -Giorgos
     */
}
