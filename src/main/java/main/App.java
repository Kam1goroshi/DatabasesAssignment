package main;
import db_agent.CustomersAgent;
import db_agent.Room;
import db_agent.RoomsAgent;

public class App {
    public static void main(String[] args) {
        //Customer customer = new Customer("BJ539933", "Big Jacob", "+5596745223", "bigJacob@hotmail.com", Date.valueOf("1995-03-05"));
        CustomersAgent customersAgent = new CustomersAgent();
        RoomsAgent roomsAgent = new RoomsAgent();
        //CustomersAgent.addCustomer(customer);
        System.out.println(customersAgent.getCustomersByName("Big Jacob"));
        System.out.println(customersAgent.getCustomer("BJ339081"));
        System.out.println(roomsAgent.getRoomByID(3));
        System.out.println(roomsAgent.getRoomByNumber("A233"));
    }
}

