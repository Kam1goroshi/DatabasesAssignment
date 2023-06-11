package db_agent;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ReservationsAgent{
    public boolean addReservation(Reservation reservation){
        try{
            Connection conn = DatabaseConnection.getConnection();
            CallableStatement statement = conn.prepareCall("CALL add_room_reservation(?, ?, ?, ?, ?, ?, ?);");
            // Set the input parameters
            statement.setInt(1, reservation.getRoom_id());
            statement.setString(2, reservation.getCustomer_id());
            statement.setDate(3, reservation.getDate_start());
            statement.setDate(4, reservation.getDate_end());
            statement.setDouble(5, reservation.getValue());
            statement.setInt(6, reservation.getStatus().ordinal());
            statement.setBoolean(7, reservation.isPaid());

            // Execute the stored procedure
            statement.execute();

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 
     * @param searchTerm
     * @return Reservation ArrayList where searchTerm is a substring of either customer's ID or room number in database
     */
    public ArrayList<Reservation> getReservations(String searchTerm){
        ArrayList<Reservation> reservations = new ArrayList<>();
        try{
            Connection conn = DatabaseConnection.getConnection();
            StringBuilder sb = new StringBuilder("SELECT * FROM room_reservations WHERE");
            sb.append(" customer_id LIKE \"%").append(searchTerm);
            sb.append("%\" OR room_id LIKE \"%").append(searchTerm);
            sb.append("%\" OR ID LIKE \"%").append(searchTerm).append("%\"");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sb.toString());
            while(rs.next()){
                reservations.add(new Reservation(rs));
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return reservations;
    }
}
