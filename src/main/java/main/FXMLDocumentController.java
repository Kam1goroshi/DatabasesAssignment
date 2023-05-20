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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Pc
 */
public class FXMLDocumentController extends PostLoginController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Label label1;
    private TextField searchinput;
    @FXML
    private Button exitbtn;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginbtn;
    
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
    String username = usernameField.getText();
    String password = passwordField.getText();

    // Perform authentication logic here
    boolean isAuthenticated = authenticate(username, password);

    if (isAuthenticated) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PostLogin.fxml"));
            Parent root = loader.load();
            // Create a new scene with the loaded FXML file
            Scene postLoginScene = new Scene(root);

            // Get the stage from the current button's scene
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(postLoginScene);
            stage.setTitle("Post Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        // Show an error message or display an error label
        System.out.println("Login failed");
    }
    username_label.setText(usernameField.getText());
}


private boolean authenticate(String username, String password) {
    // Example implementation: hardcoding the username and password for demonstration purposes
    String validUsername = "admin";
    String validPassword = "admin";

    return username.equals(validUsername) && password.equals(validPassword);
}


    private void handleSearchButtonAction(ActionEvent event) {
        String searchTerm = searchinput.getText();
        System.out.println("Search Works");
        label.setText("Search Works");
    }

    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        System.exit(0);
    }















    
}
