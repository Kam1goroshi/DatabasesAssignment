package db_agent;

import java.sql.CallableStatement;
import java.sql.Connection;

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
}
