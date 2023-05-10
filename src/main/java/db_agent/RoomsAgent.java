package db_agent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RoomsAgent extends Agent {

    /**
     * Adds object to DB
     */
    @Override
    public boolean addEntity(PhysicalEntity physicalEntity) {
        if (physicalEntity.getClass().getName().equals(Room.class.getName())) {
            return super.addEntity(physicalEntity);
        } else
            return false;
    }

    /**
     *
     * @param ID room ID
     * @return A new room object if given ID is found or null
     */
    public Room getRoomByID(int ID) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM rooms WHERE ID = " + ID + ";");
            if (result.next()) {
                Room room = new Room(result);
                conn.close();
                return room;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param ID room number
     * @return A new room object if given ID is found or null
     */
    public Room getRoomByNumber(String number) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM rooms WHERE room_number = '" + number + "';");
            if (result.next()) {
                Room room = new Room(result);
                conn.close();
                return room;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get rooms by size etc will be implemented later if needed
     * -Giorgos
     */
}
