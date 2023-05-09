package main;

import java.sql.Connection;

import db_agent.DatabaseConnection;

public class App {
    public static void main(String[] args) {
        try{
        Connection conn = DatabaseConnection.getConnection();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        };
    }
}

