/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import db_agent.Customer;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pc
 */
public class CustomersController implements Initializable {

    @FXML
    private Button addbtn;

    @FXML
    private TextField customerDob;

    @FXML
    private TextField customerID;

    @FXML
    private TextField customerName;

    @FXML
    private TextField customerPhone;

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
    private TableView<DataModel> customersTable;
    
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
    private ObservableList<DataModel> filteredData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col1.setCellValueFactory(cellData -> cellData.getValue().value1Property());
        col2.setCellValueFactory(cellData -> cellData.getValue().value2Property());
        col3.setCellValueFactory(cellData -> cellData.getValue().value3Property());
        col4.setCellValueFactory(cellData -> cellData.getValue().value4Property());

        // Initialize the data list
        data = FXCollections.observableArrayList();
        filteredData = FXCollections.observableArrayList();
        customersTable.setItems(filteredData);
        
        // Set the data as the items for the TableView
        customersTable.setItems(data);
        
        customersTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            customerID.setText(newValue.getValue2());
            customerName.setText(newValue.getValue2());
            customerDob.setText(newValue.getValue3());
            customerPhone.setText(newValue.getValue4());
        }
        });
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
        String searchTerm = searchinput.getText().trim().toLowerCase();

        filteredData.clear();

        for (DataModel item : data) {
            if (item.getValue1().toLowerCase().contains(searchTerm) ||
            item.getValue2().toLowerCase().contains(searchTerm) ||
            item.getValue3().toLowerCase().contains(searchTerm) ||
            item.getValue4().toLowerCase().contains(searchTerm)) {
            filteredData.add(item);
            }
        }
            
        if (!filteredData.isEmpty()) {
            customersTable.getSelectionModel().select(filteredData.get(0));
            customersTable.scrollTo(filteredData.get(0));
        } else {
            showAlert("No Results", "No matching results found.");
        }
        
        clearTextFields();
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
        String value1 = customerID.getText();
        String value2 = customerName.getText();
        String value3 = customerDob.getText();
        String value4 = customerPhone.getText();

        DataModel newData = new DataModel(value1, value2, value3, value4);
        data.add(newData);

        clearTextFields();
    }

    @FXML
    private void handleEditButtonAction(ActionEvent event) {
        DataModel selectedData = customersTable.getSelectionModel().getSelectedItem();

        if (selectedData != null) {
            selectedData.setValue1(customerID.getText()); // Update value1 with the edited value
            selectedData.setValue2(customerName.getText()); // Update value2 with the edited value
            selectedData.setValue3(customerDob.getText()); // Update value3 with the edited value
            selectedData.setValue4(customerPhone.getText()); // Update value3 with the edited value
        }

        clearTextFields();
    }

    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
        DataModel selectedData = customersTable.getSelectionModel().getSelectedItem();

        if (selectedData != null) {
            data.remove(selectedData);
        }

        clearTextFields();
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
    
    private void clearTextFields() {
        customerID.clear();
        customerName.clear();
        customerDob.clear();
        customerPhone.clear();
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
        
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
