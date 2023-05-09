package main;

import java.sql.Connection;
import java.sql.Date;
import db_agent.CustomersAgent;
import db_agent.Customer;
import db_agent.DatabaseConnection;

public class App {
    public static void main(String[] args) {
        Customer customer = new Customer("BJ339081", "Big Jacob", "+559965345223", "bigJacob@jacobs.com", Date.valueOf("1995-03-05"));
        CustomersAgent.addCustomer(customer);
        System.out.println(CustomersAgent.getCustomer("BJ339081"));
    }
}

