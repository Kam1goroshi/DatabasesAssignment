package main;

import java.sql.Date;

import db_agent.CustomersAgent;
import db_agent.Reservation;
import db_agent.ReservationsAgent;
import db_agent.Room;
import db_agent.RoomsAgent;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        // // Customer customer = new Customer("BJ539933", "Big Jacob", "+5596745223",
        // // "bigJacob@hotmail.com", Date.valueOf("1995-03-05"));
        // CustomersAgent customersAgent = new CustomersAgent();
        // RoomsAgent roomsAgent = new RoomsAgent();
        // ReservationsAgent reservationsAgent = new ReservationsAgent();
        // // CustomersAgent.addCustomer(customer);
        // System.out.println(customersAgent.getCustomersByName("Big Jacob"));
        // System.out.println(customersAgent.getCustomer("BJ339081"));
        // System.out.println(roomsAgent.getRoomByID(3));
        // System.out.println(roomsAgent.getRoomByNumber("A233"));
        // System.out.println(roomsAgent.getAvailableRooms(Date.valueOf("2023-04-01"), Date.valueOf("2023-4-3")));
        // System.out.println(reservationsAgent.addReservation(
        //         new Reservation(
        //                 roomsAgent.getRoomByNumber("A233").getID(),
        //                 "BJ339081",
        //                 Date.valueOf("2023-12-05"),
        //                 Date.valueOf("2023-12-07"),
        //                 150.0,
        //                 Reservation.Status.RESERVED,
        //                 false)));
        launch();
    }
}
