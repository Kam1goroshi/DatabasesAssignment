package db_agent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.scene.chart.PieChart.Data;

public class CustomersAgent extends Agent {

    /**
     * Adds object to DB
     */
    @Override
    public boolean addEntity(PhysicalEntity physicalEntity) {
        if (physicalEntity.getClass().getName().equals(Customer.class.getName())) {
            return super.addEntity(physicalEntity);
        } else
            return false;
    }

    /**
     *
     * @param ID citizen ID
     * @return A new Customer object if given ID is found or null
     */
    public Customer getCustomer(String ID) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM customers WHERE ID = '" + ID + "';");
            if (result.next()) {
                Customer customer = new Customer(result);
                conn.close();
                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all the customers with provided name as a substring in their name
     * and returns them as arraylist
     * 
     * @param name civilian name
     * @return Customer arraylist, size 0 on no customers, or null on fail
     *         connection
     */
    public ArrayList<Customer> getCustomersByName(String name) {
        Connection conn;
        Statement stmt;
        ArrayList<Customer> list = null;
        Customer customer = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM customers WHERE customers.name LIKE '%" + name + "%';");
            list = new ArrayList<>();
            while (result.next()) {
                customer = new Customer(result);
                list.add(customer);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
