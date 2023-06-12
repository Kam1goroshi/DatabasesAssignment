/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import db_agent.Reservation;
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
                customerID.setText(newValue.getrRoomId());
                roomID.setText(newValue.getrRoomId());
                checkIn.setText(newValue.getrRoomId());
                checkOut.setText(newValue.getrRoomId());
                resPrice.setText(newValue.getrRoomId());
                resStatus.setText(newValue.getrRoomId());
                resPaid.setText(newValue.getrRoomId());
            }
        });
    }

    private void populateData() {
        ArrayList<Reservation> reservations = reservationsAgent.getReservations(searchinput.getText().trim());
        for (Reservation reservation : reservations) {
            data.add(new DataModel(reservation));
        }
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
        data.clear();
        populateData();
        // will get reservations based on room number or customer ID sorted by newest
        // first
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
        private StringProperty rId;
        private StringProperty rRoomId;
        private StringProperty rCustomerId;
        private StringProperty rDateStart;
        private StringProperty rDateEnd;
        private StringProperty rValue;
        private StringProperty rStatus;
        private StringProperty rIsPaid;

        public DataModel(String rId, String rRoomId, String rCustomerId, String rDateStart, String rDateEnd,
                String rValue, String rStatus, String rIsPaid) {
            this.rId = new SimpleStringProperty(rId);
            this.rRoomId = new SimpleStringProperty(rRoomId);
            this.rCustomerId = new SimpleStringProperty(rCustomerId);
            this.rDateStart = new SimpleStringProperty(rDateStart);
            this.rDateEnd = new SimpleStringProperty(rDateEnd);
            this.rValue = new SimpleStringProperty(rValue);
            this.rStatus = new SimpleStringProperty(rStatus);
            this.rIsPaid = new SimpleStringProperty(rIsPaid);
        }

        public DataModel(Reservation reservation) {
            this.rId = new SimpleStringProperty(Integer.toString(reservation.getReservation_id()));
            this.rRoomId = new SimpleStringProperty(Integer.toString(reservation.getRoom_id()));
            this.rCustomerId = new SimpleStringProperty(reservation.getCustomer_id().toString());
            this.rDateStart = new SimpleStringProperty(reservation.getDate_start().toString());
            this.rDateEnd = new SimpleStringProperty(reservation.getDate_end().toString());
            this.rValue = new SimpleStringProperty(Double.toString(reservation.getValue()));
            this.rStatus = new SimpleStringProperty(Integer.toString(reservation.getStatus().ordinal()));
            this.rIsPaid = new SimpleStringProperty(Boolean.toString(reservation.isPaid()));
        }

        public String getrId() {
            return rId.get();
        }

        public StringProperty value1Property() {
            return rId;
        }

        public void setValue1(String value1) {
            this.rId.set(value1);
        }

        public String getrRoomId() {
            return rRoomId.get();
        }

        public StringProperty value2Property() {
            return rRoomId;
        }

        public void setValue2(String value2) {
            this.rRoomId.set(value2);
        }

        public String getrCustomerId() {
            return rCustomerId.get();
        }

        public StringProperty value3Property() {
            return rCustomerId;
        }

        public void setValue3(String value3) {
            this.rCustomerId.set(value3);
        }

        public String getrDateStart() {
            return rDateStart.get();
        }

        public StringProperty value4Property() {
            return rDateStart;
        }

        public void setValue4(String value4) {
            this.rDateStart.set(value4);
        }

        public String getrDateEnd() {
            return rDateEnd.get();
        }

        public StringProperty value5Property() {
            return rDateEnd;
        }

        public void setValue5(String value5) {
            this.rDateEnd.set(value5);
        }

        public String getrValue() {
            return rValue.get();
        }

        public StringProperty value6Property() {
            return rValue;
        }

        public void setValue6(String value6) {
            this.rValue.set(value6);
        }

        public String getrStatus() {
            return rStatus.get();
        }

        public StringProperty value7Property() {
            return rStatus;
        }

        public void setValue7(String value7) {
            this.rStatus.set(value7);
        }

        public String getrIsPaid() {
            return rIsPaid.get();
        }

        public StringProperty value8Property() {
            return rIsPaid;
        }

        public void setValue8(String value8) {
            this.rIsPaid.set(value8);
        }
    }
}
