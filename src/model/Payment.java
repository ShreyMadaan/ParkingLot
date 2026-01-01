package model;

import java.util.Date;

public class Payment extends BaseModel{
    private int amount;
    private PaymentMode mode;
    private PaymentStatus status;
    private Bill bill;
    private String refId;
    private Date time;

    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public Bill getBill() {
        return bill;
    }
    public void setBill(Bill bill) {
        this.bill = bill;
    }
    public String getRefId() {
        return refId;
    }
    public void setRefId(String refId) {}
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public PaymentMode getMode() {
        return mode;
    }
    public void setMode(PaymentMode mode) {}
    public PaymentStatus getStatus() {
        return status;
    }
    public void setStatus(PaymentStatus status) {}

}
