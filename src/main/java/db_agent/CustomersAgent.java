package db_agent;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomersAgent {

    /**
     * Adds a customer to the database
     * @param customer
     * @return boolean that indicates success
     */
    public static boolean addCustomer(Customer customer){
        try{
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO customers VALUES(" 
            + customer.geteValuesString() + ");");
        stmt.close();
        conn.close();
        return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves single customer from database
     * @param ID civilian ID, unique
     * @return the unique customer or NULL
     */
    public static Customer getCustomer(String ID){
        try{
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM customers WHERE ID='"+ID+"';");
        if(result.next()){
            Customer customer = new Customer(result);
            stmt.close();
            result.close();
            return customer;
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all the customers with provided name as a substring in their name and returns them as arraylist
     * @param name civilian name
     * @return Customer arraylist, size 0 on no customers, or null on fail connection
     */
    public static ArrayList<Customer> getCustomersByName(String name){
        Connection conn;
        Statement stmt;
        ArrayList<Customer> list = null;
        Customer customer = null;
        try{
        conn = DatabaseConnection.getConnection();
        stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM customers WHERE customers.name LIKE '%"+name+"%';");
        list = new ArrayList<>();
        while(result.next()){
            System.out.println("hello???");
            customer = new Customer(result);
            list.add(customer);
        }
        conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
