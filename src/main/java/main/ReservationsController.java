/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import db_agent.ReservationsAgent;
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
public class ReservationsController implements Initializable {

    private ObservableList<DataModel> data;
    ReservationsAgent reservationsAgent = new ReservationsAgent();
    @FXML
    private Button addbtn;
    @FXML
    private TextField checkIn;
    @FXML
    private TextField checkOut;
    @FXML
    private TextField customerID;
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
    private TextField resPaid;
    @FXML
    private TextField resPrice;
    @FXML
    private TextField resStatus;
    @FXML
    private TextField roomID;
    @FXML
    private TableView<DataModel> reservationsTable;    
    @FXML
    private TableColumn<DataModel, String> col1;
    @FXML
    private TableColumn<DataModel, String> col2;
    @FXML
    private TableColumn<DataModel, String> col3;
    @FXML
    private TableColumn<DataModel, String> col4;    
    @FXML
    private TableColumn<DataModel, String> col5;
    @FXML
    private TableColumn<DataModel, String> col6;
    @FXML
    private TableColumn<DataModel, String> col7;    
    @FXML
    private TableColumn<DataModel, String> col8;   
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
        col5.setCellValueFactory(cellData -> cellData.getValue().value5Property());
        col6.setCellValueFactory(cellData -> cellData.getValue().value6Property());
        col7.setCellValueFactory(cellData -> cellData.getValue().value7Property());
        col8.setCellValueFactory(cellData -> cellData.getValue().value8Property());

        // Initialize the data list
        data = FXCollections.observableArrayList();
        data.clear();
        populateData();
        reservationsTable.setItems(data);
        
        reservationsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            customerID.setText(newValue.getValue2());
            roomID.setText(newValue.getValue2());
            checkIn.setText(newValue.getValue2());
            checkOut.setText(newValue.getValue2());
            resPrice.setText(newValue.getValue2());
            resStatus.setText(newValue.getValue2());
            resPaid.setText(newValue.getValue2());
        }
        });
    }    

    private void populateData() {
        
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
    private void handleSearchButtonAction(ActionEvent event) {
        String searchTerm = searchinput.getText().trim().toLowerCase();

        data.clear();
            
        if (data.isEmpty()) {
            showAlert("No Results", "No matching results found.");
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
    private void handleAddButtonAction(ActionEvent event) {
        String value1 = "";
        String value2 = customerID.getText();
        String value3 = roomID.getText();
        String value4 = checkIn.getText();
        String value5 = checkOut.getText();
        String value6 = resPrice.getText();
        String value7 = resStatus.getText();
        String value8 = resPaid.getText();

        DataModel newData = new DataModel(value1, value2, value3, value4, value5, value6, value7, value8);
        data.add(newData);

        clearTextFields();
    }

    @FXML
    private void handleEditButtonAction(ActionEvent event) {
        DataModel selectedData = reservationsTable.getSelectionModel().getSelectedItem();

        if (selectedData != null) {
            selectedData.setValue1(""); // Update value1 with the edited value
            selectedData.setValue2(customerID.getText()); // Update value2 with the edited value
            selectedData.setValue3(roomID.getText()); // Update value3 with the edited value
            selectedData.setValue4(checkIn.getText()); // Update value4 with the edited value
            selectedData.setValue5(checkOut.getText()); // Update value5 with the edited value
            selectedData.setValue6(resPrice.getText()); // Update value6 with the edited value
            selectedData.setValue7(resStatus.getText()); // Update value7 with the edited value
            selectedData.setValue8(resPaid.getText()); // Update value8 with the edited value
        }

        clearTextFields();
    }

    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
        DataModel selectedData = reservationsTable.getSelectionModel().getSelectedItem();

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
        roomID.clear();
        checkIn.clear();
        checkOut.clear();
        resPrice.clear();
        resStatus.clear();
        resPaid.clear();
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
        private StringProperty value5;
        private StringProperty value6;
        private StringProperty value7;
        private StringProperty value8;
    
        
    
        public DataModel(String value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8) {
           

            this.value1 = new SimpleStringProperty(value1);
            this.value2 = new SimpleStringProperty(value2);
            this.value3 = new SimpleStringProperty(value3);
            this.value4 = new SimpleStringProperty(value4);
            this.value5 = new SimpleStringProperty(value5);
            this.value6 = new SimpleStringProperty(value6);
            this.value7 = new SimpleStringProperty(value7);
            this.value8 = new SimpleStringProperty(value8);
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
    public String getValue5() {
        return value5.get();
    }

    public StringProperty value5Property() {
        return value5;
    }

    public void setValue5(String value5) {
        this.value5.set(value5);
    }
    public String getValue6() {
        return value6.get();
    }

    public StringProperty value6Property() {
        return value6;
    }

    public void setValue6(String value6) {
        this.value6.set(value6);
    }
    public String getValue7() {
        return value7.get();
    }

    public StringProperty value7Property() {
        return value7;
    }

    public void setValue7(String value7) {
        this.value7.set(value7);
    }
    public String getValue8() {
        return value8.get();
    }

    public StringProperty value8Property() {
        return value8;
    }

    public void setValue8(String value8) {
        this.value8.set(value8);
    }
    }
}
