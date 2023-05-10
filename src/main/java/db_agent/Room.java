package db_agent;

import java.sql.ResultSet;

public class Room {
    private int ID = -1; //will be -1 on auto generation cz it's auto increment on db
    private int room_number;
    private int size;
    private int type;
    private String description;

    public Room(int room_number, int size, int type, String description) {
        this.room_number = room_number;
        this.size = size;
        this.type = type;
        this.description = description;
    }

    public Room(ResultSet results) throws Exception {
        this.ID = results.getInt("ID");
        this.room_number = results.getInt("room_number");
        this.size = results.getInt("size");
        this.type = results.getInt("type");
        this.description = results.getString("description");
    }

    public int getID() {
        return ID;
    }

    public int getRoom_number() {
        return room_number;
    }

    public int getSize() {
        return size;
    }

    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
