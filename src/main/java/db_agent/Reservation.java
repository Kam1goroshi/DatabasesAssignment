package db_agent;

import java.sql.Date;

public class Reservation {
    public static enum Status {
        RESERVED,
        INHABITATED,
        CLOSED // finished|over
    }

    private int reservation_id = -1; // Will be auto increment from db
    private int room_id;
    private String customer_id;
    private Date date_start;
    private Date date_end;
    private double value;
    private Status status = Status.RESERVED;
    private boolean is_paid;

    public Reservation(int room_id, String customer_id, Date date_start, Date date_end, double value, Status status,
            boolean is_paid) {
        this.room_id = room_id;
        this.customer_id = customer_id;
        this.date_start = date_start;
        this.date_end = date_end;
        this.value = value;
        this.status = status;
        this.is_paid = is_paid;
    }

    public Reservation(Reservation r) {
        this.reservation_id = r.reservation_id;
        this.room_id = r.room_id;
        this.customer_id = r.customer_id;
        this.date_start = r.date_start;
        this.date_end = r.date_end;
        this.value = r.value;
        this.status = r.status;
        this.is_paid = r.is_paid;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public Date getDate_start() {
        return date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public double getValue() {
        return value;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isPaid() {
        return is_paid;
    }
}