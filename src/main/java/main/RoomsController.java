/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import db_agent.Room;
import db_agent.RoomsAgent;
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
import javafx.scene.chart.PieChart.Data;
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
public class RoomsController implements Initializable {
    private ObservableList<DataModel> data;
    private RoomsAgent roomsAgent = new RoomsAgent();
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
        roomsTable.setItems(data);
        roomsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                roomNumber.setText(newValue.getRoomNumber());
                roomSize.setText(newValue.getRoomSize());
                roomType.setText(newValue.getRoomType());
            }
        });
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
        String searchTerm = searchinput.getText().trim().toLowerCase();
        data.clear();
        for (DataModel item : data) {
            if (item.getRoomID().toLowerCase().contains(searchTerm) ||
                    item.getRoomNumber().toLowerCase().contains(searchTerm) ||
                    item.getRoomSize().toLowerCase().contains(searchTerm) ||
                    item.getRoomType().toLowerCase().contains(searchTerm)) {
                data.add(item);
            }
        }

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
        DataModel selectedData = roomsTable.getSelectionModel().getSelectedItem();

        if (selectedData != null) {
            selectedData.setValue1(""); // Update value1 with the edited value
            selectedData.setValue2(roomNumber.getText()); // Update value2 with the edited value
            selectedData.setValue3(roomSize.getText()); // Update value3 with the edited value
            selectedData.setValue4(roomType.getText()); // Update value3 with the edited value
        }

        clearTextFields();
    }

    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
        DataModel selectedData = roomsTable.getSelectionModel().getSelectedItem();

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

    private void populateData() {
        ArrayList<Room> rooms = roomsAgent.getAllRooms();
        for (Room room : rooms) {
            data.add(new DataModel(room));
        }
    }

    private void clearTextFields() {
        roomNumber.clear();
        roomSize.clear();
        roomType.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public class DataModel {
        private StringProperty roomID;
        private StringProperty roomNumber;
        private StringProperty roomSize;
        private StringProperty roomType;

        public DataModel(Room room) {
            this.roomID = new SimpleStringProperty(Integer.toString(room.getID()));
            this.roomNumber = new SimpleStringProperty(room.getRoom_number());
            this.roomSize = new SimpleStringProperty(Integer.toString(room.getSize()));
            this.roomType = new SimpleStringProperty(Integer.toString(room.getType()));
        }

        public DataModel(String roomID, String roomNumber, String roomSize, String roomType) {
            this.roomID = new SimpleStringProperty(roomID);
            this.roomNumber = new SimpleStringProperty(roomNumber);
            this.roomSize = new SimpleStringProperty(roomSize);
            this.roomType = new SimpleStringProperty(roomType);
        }

        public String getRoomID() {
            return roomID.get();
        }

        public StringProperty value1Property() {
            return roomID;
        }

        public void setValue1(String value1) {
            this.roomID.set(value1);
        }

        public String getRoomNumber() {
            return roomNumber.get();
        }

        public StringProperty value2Property() {
            return roomNumber;
        }

        public void setValue2(String value2) {
            this.roomNumber.set(value2);
        }

        public String getRoomSize() {
            return roomSize.get();
        }

        public StringProperty value3Property() {
            return roomSize;
        }

        public void setValue3(String value3) {
            this.roomSize.set(value3);
        }

        public String getRoomType() {
            return roomType.get();
        }

        public StringProperty value4Property() {
            return roomType;
        }

        public void setValue4(String value4) {
            this.roomType.set(value4);
        }
    }
}
