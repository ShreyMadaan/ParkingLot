package dtos;

public class GenerateTicketResponseDto {
    private Long ticketId;
    private String operatorName;
    private int spotNumber;
    private ResponseStatus status;

    public Long getTicketId() {
        return ticketId;
    }
    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }
    public String getOperatorName() {
        return operatorName;
    }
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
    public int getSpotNumber() {
        return spotNumber;
    }
    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }
    public ResponseStatus getStatus() {
        return status;
    }
    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
}
