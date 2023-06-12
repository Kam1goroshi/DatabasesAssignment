/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import org.checkerframework.checker.units.qual.cd;

import db_agent.Customer;
import db_agent.CustomersAgent;
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

    private ObservableList<DataModel> data;
    private CustomersAgent customersAgent = new CustomersAgent();
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
    @FXML
    private Button searchbtn;
    @FXML
    private TextField searchinput;

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
        populateData();

        // Set the data as the items for the TableView
        customersTable.setItems(data);

        customersTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                customerID.setText(newValue.getCustomerID());
                customerName.setText(newValue.getCustomerName());
                customerDob.setText(newValue.getCustomerDob());
                customerPhone.setText(newValue.getCustomerPhoneNumber());
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
        // String searchTerm = searchinput.getText().trim().toLowerCase();

        // filteredData.clear();

        // for (DataModel item : data) {
        // if (item.getCustomerID().toLowerCase().contains(searchTerm) ||
        // item.getCustomerName().toLowerCase().contains(searchTerm) ||
        // item.getCustomerDob().toLowerCase().contains(searchTerm) ||
        // item.getCustomerPhoneNumber().toLowerCase().contains(searchTerm)) {
        // filteredData.add(item);
        // }
        // }

        // if (!filteredData.isEmpty()) {
        // customersTable.getSelectionModel().select(filteredData.get(0));
        // customersTable.scrollTo(filteredData.get(0));
        // } else {
        // showAlert("No Results", "No matching results found.");
        // }

        populateData();
        customersTable.setItems(data);
        if (data.isEmpty()) {
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
        String cID = customerID.getText();
        String cName = customerName.getText();
        String cEmail = "@.com";
        Date cDob = null;
        try{
            cDob = Date.valueOf(customerDob.getText());
        }catch(Exception e){
            showAlert("Failed to register customer", "date of birth must be in sql format: YYYY-MM-DD, with proper values");
            return;
        }
        String cPhone = customerPhone.getText();
        boolean success = false;
        
        //Temporary solution until jfx is update to get email input. There might be collisions and this is programmatistically wrong.
        //Generate random email
        Random rand = new Random();
        for(int i = 0; i < 10; i++){
            char c = (char)('a' + rand.nextInt(26));
            if(rand.nextBoolean())
                c = Character.toUpperCase(c);
            cEmail = c + cEmail;
        }
        
        success = customersAgent.addEntity(new Customer(cID, cName, cPhone, cEmail, cDob));
        if(!success){
            showAlert("Failed to register customer", "validate the inputs or contact the administrator");
        }else{
            populateData();
            customersTable.setItems(data);
        }
        System.out.println(cEmail);
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
        customersAgent.deleteCustomer(selectedData.getCustomerID());
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

    private void populateData() {
        data.clear();
        ArrayList<Customer> customers = customersAgent.getCustomersByName(searchinput.getText().trim());
        for (Customer customer : customers) {
            data.add(new DataModel(customer));
        }
    }

    public class DataModel {
        private StringProperty customerID;
        private StringProperty customerName;
        private StringProperty customerDob;
        private StringProperty customerPhoneNumber;

        public DataModel(Customer customer) {
            this.customerID = new SimpleStringProperty(customer.getID());
            this.customerName = new SimpleStringProperty(customer.getName());
            this.customerDob = new SimpleStringProperty(customer.getDob().toString());
            this.customerPhoneNumber = new SimpleStringProperty(customer.getPhoneNumber());
        }

        public DataModel(String value1, String value2, String value3, String value4) {

            this.customerID = new SimpleStringProperty(value1);
            this.customerName = new SimpleStringProperty(value2);
            this.customerDob = new SimpleStringProperty(value3);
            this.customerPhoneNumber = new SimpleStringProperty(value4);
        }

        public String getCustomerID() {
            return customerID.get();
        }

        public StringProperty value1Property() {
            return customerID;
        }

        public void setValue1(String value1) {
            this.customerID.set(value1);
        }

        public String getCustomerName() {
            return customerName.get();
        }

        public StringProperty value2Property() {
            return customerName;
        }

        public void setValue2(String value2) {
            this.customerName.set(value2);
        }

        public String getCustomerDob() {
            return customerDob.get();
        }

        public StringProperty value3Property() {
            return customerDob;
        }

        public void setValue3(String value3) {
            this.customerDob.set(value3);
        }

        public String getCustomerPhoneNumber() {
            return customerPhoneNumber.get();
        }

        public StringProperty value4Property() {
            return customerPhoneNumber;
        }

        public void setValue4(String value4) {
            this.customerPhoneNumber.set(value4);
        }
    }
}
