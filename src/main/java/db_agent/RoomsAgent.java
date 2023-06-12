package db_agent;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.checkerframework.checker.units.qual.A;

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
    public ArrayList<Room> getRoomsByNumber(String number) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM rooms WHERE room_number LIKE \"%" + number + "%\";");
            ArrayList<Room> rooms = new ArrayList<>();
            while(rs.next()) {
                rooms.add(new Room(rs));
            }
            conn.close();
            return rooms;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Room> getAvailableRooms(Date date_start, Date date_end){
        try {
            Connection conn = DatabaseConnection.getConnection();
            CallableStatement stmt = conn.prepareCall("{CALL getAvailableRooms('" + date_start + "', '" + date_end + "')}");
            ResultSet rs = stmt.executeQuery();
            ArrayList<Room> rooms = new ArrayList<>();
            while (rs.next()) {
                rooms.add(new Room(rs));
            }
            rs.close();
            stmt.close();
            conn.close();
            return rooms;
        } catch (Exception e) {
            // handle the exception here
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deletes a room row from the database
     * @param reservationId row id
     * @return true if affected rows > 1
     */
    public boolean deleteRoom(String reservationId){
        return deleteRow(reservationId, "rooms");
    }
}
