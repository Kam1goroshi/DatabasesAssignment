package db_agent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.Test;

public class CustomerTest {
    
    @Test
    void Customer(){
        Customer customer = new Customer("BJ339081", "Big Jacob", "+559965345223", "bigJacob@jacobs.com", Date.valueOf("1995-03-05"));
        assertEquals("{ID:BJ339081,name:Big Jacob,phone:+559965345223,email:bigJacob@jacobs.com,dob:1995-03-05}", "customer toString bad!",customer.toString());
        assertEquals("'BJ339081','Big Jacob','+559965345223','bigJacob@jacobs.com','1995-03-05'", "customer toString bad!",customer.geteValuesString());
    }
}
