/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Pc
 */
public class PostLoginController implements Initializable {
    
    @FXML
    private Label label1;
    @FXML
    private Button exitbtn;
    @FXML
    private Button logoutbtn;
    @FXML
    private Label idlabel1;
    @FXML
    private Button userinfobtn;
    @FXML
    private Button customersbtn;
    @FXML
    private Button roomsbtn;
    @FXML
    private Button reservationsbtn;
    
    public static Label username_label;  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        username_label = idlabel1;
    }    

    @FXML
    private void handleCustomersButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Customers.fxml"));
            Parent root = loader.load();
            // Create a new scene with the loaded FXML file
            Scene customersScene = new Scene(root);
        
            // Get the stage from the current button's scene
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(customersScene);
            stage.setTitle("Customers");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRoomsButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Rooms.fxml"));
            Parent root = loader.load();
            // Create a new scene with the loaded FXML file
            Scene roomsScene = new Scene(root);
        
            // Get the stage from the current button's scene
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(roomsScene);
            stage.setTitle("Rooms");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleReservationsButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reservations.fxml"));
            Parent root = loader.load();
            // Create a new scene with the loaded FXML file
            Scene reservationsScene = new Scene(root);
        
            // Get the stage from the current button's scene
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(reservationsScene);
            stage.setTitle("Reservations");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleLogoutButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLDocument.fxml"));
            Parent root = loader.load();
            // Create a new scene with the loaded FXML file
            Scene loginScene = new Scene(root);
        
            // Get the stage from the current button's scene
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(loginScene);
            stage.setTitle("FXML Document");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        System.exit(0);
    }
    
}
