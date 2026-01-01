package model;

import java.util.Date;

public class Ticket extends BaseModel{
    private ParkingSpot parkingSpot;
    private Date entryTime;
    private Vehicle vehicle;
    private Gate gate;
    private Operator operator;

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
    public void setParkingSpot(ParkingSpot parkingSpot) {}
    public Date getEntryTime() {
        return entryTime;
    }
    public void setEntryTime(Date entryTime) {}
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {}
    public Gate getGate() {
        return gate;
    }
    public void setGate(Gate gate) {}
    public Operator getOperator() {
        return operator;
    }
    public void setOperator(Operator operator) {}

}
