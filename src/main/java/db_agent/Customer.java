package db_agent;

import java.sql.Date;
import java.sql.ResultSet;

public class Customer extends PhysicalEntity{
    static private final String PK = "ID";
    static private final String tablename ="customers";

    private String ID;
    private String name;
    private String phone_number;
    private String email;
    private Date dob;

    public Customer(String ID, String name, String phone_number, String email, Date dob) {
        this.ID = ID;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.dob = dob;
    
        //Validation functions would be nice
        //Might not implement at all since this is homework
    }

    public Customer(ResultSet resultSet) throws Exception{
        this.ID = resultSet.getString("ID");
        this.name = resultSet.getString("name");
        this.phone_number = resultSet.getString("phone_number");
        this.email = resultSet.getString("email");
        this.dob = Date.valueOf(resultSet.getString("dob"));
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public Date getDob() {
        return dob;
    }

    public String getPrimaryKeyQuery(){
        return "SELECT * FROM customers WHERE ID='"+ID+"';";
    }

    public String getInsertStatement(){
        return "INSERT INTO customers VALUES('" + ID + "','" + name + "','"+ phone_number + "','" + email + "','" + dob + "');";
    }

    public static String getPk() {
        return PK;
    }

    public static String getTablename() {
        return tablename;
    }

    @Override
    public String toString(){
        return new StringBuilder("{ID:").append(ID).append(",name:").append(name).append(",phone:").append(phone_number)
        .append(",email:").append(email).append(",dob:").append(dob.toString()).append("}").toString();
    }
}