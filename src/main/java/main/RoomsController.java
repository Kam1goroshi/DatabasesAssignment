/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import db_agent.Room;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pc
 */
public class RoomsController implements Initializable {

    @FXML
    private Button addbtn;

    @FXML
    private Button deletebtn;

    @FXML
    private Button editbtn;

    @FXML
    private Button exitbtn;

    @FXML
    private Button homebtn;

    @FXML
    private Label label1;

    @FXML
    private Button logoutbtn;

    @FXML
    private TextField roomNumber;

    @FXML
    private TextField roomSize;

    @FXML
    private TextField roomType;

    @FXML
    private TableView<DataModel> roomsTable;
    
    @FXML
    private TableColumn<DataModel, String> col1;

    @FXML
    private TableColumn<DataModel, String> col2;

    @FXML
    private TableColumn<DataModel, String> col3;

    @FXML
    private TableColumn<DataModel, String> col4;
    
    private ObservableList<DataModel> data;
    
    @FXML
    private Button searchbtn;

    @FXML
    private TextField searchinput;


    /**
     * Initializes the controller class.
     */
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addbtn.setOnAction(this::handleAddButtonAction);
        col1.setCellValueFactory(cellData -> cellData.getValue().value1Property());
        col2.setCellValueFactory(cellData -> cellData.getValue().value2Property());
        col3.setCellValueFactory(cellData -> cellData.getValue().value3Property());
        col4.setCellValueFactory(cellData -> cellData.getValue().value4Property());

        // Initialize the data list
        data = FXCollections.observableArrayList();
        
        // Populate the data list with example data
        //data.add(new DataModel("Value1-1", "Value2-1", "Value3-1", "Value4-1"));
        //data.add(new DataModel("Value1-2", "Value2-2", "Value3-2", "Value4-2"));
        //data.add(new DataModel("Value1-3", "Value2-3", "Value3-3", "Value4-3"));

       
        
          
        // Set the data as the items for the TableView
        roomsTable.setItems(data);
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
    private void handleSearchButtonAction(ActionEvent event) {
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
    private void handleAddButtonAction(ActionEvent event) {
        String value1 = "";
        String value2 = roomNumber.getText();
        String value3 = roomSize.getText();
        String value4 = roomType.getText();

        DataModel newData = new DataModel(value1, value2, value3, value4);
        data.add(newData);

        clearTextFields();
    }

    @FXML
    private void handleEditButtonAction(ActionEvent event) {
    }

    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
    }

    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleHomeButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PostLogin.fxml"));
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
    }

    private void clearTextFields() {
        roomNumber.clear();
        roomSize.clear();
        roomType.clear();
    }
;
    
    public class DataModel {
        private StringProperty value1;
        private StringProperty value2;
        private StringProperty value3;
        private StringProperty value4;
    
        
    
        public DataModel(String value1, String value2, String value3, String value4) {
           

            this.value1 = new SimpleStringProperty(value1);
            this.value2 = new SimpleStringProperty(value2);
            this.value3 = new SimpleStringProperty(value3);
            this.value4 = new SimpleStringProperty(value4);
    }

    public String getValue1() {
        return value1.get();
    }

    public StringProperty value1Property() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1.set(value1);
    }

    public String getValue2() {
        return value2.get();
    }

    public StringProperty value2Property() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2.set(value2);
    }

    public String getValue3() {
        return value3.get();
    }

    public StringProperty value3Property() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3.set(value3);
    }

    public String getValue4() {
        return value4.get();
    }

    public StringProperty value4Property() {
        return value4;
    }

    public void setValue4(String value4) {
        this.value4.set(value4);
    }
    }
}
    

