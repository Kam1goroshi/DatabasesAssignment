package main;

import java.sql.Connection;
import java.sql.Date;
import db_agent.Agent;
import db_agent.Customer;
import db_agent.CustomersAgent;
import db_agent.DatabaseConnection;

public class App {
    public static void main(String[] args) {
        //Customer customer = new Customer("BJ539933", "Big Jacob", "+5596745223", "bigJacob@hotmail.com", Date.valueOf("1995-03-05"));
        CustomersAgent agent = new CustomersAgent();
        //CustomersAgent.addCustomer(customer);
        System.out.println(agent.getCustomersByName("Big Jacob"));
        System.out.println(agent.getCustomer("BJ339081"));
    }
}

