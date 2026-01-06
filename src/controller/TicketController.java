package controller;

import dtos.GenerateTicketRequestDto;
import dtos.GenerateTicketResponseDto;
import dtos.ResponseStatus;
import model.Gate;
import model.Ticket;
import model.Vehicle;
import model.VehicleType;
import service.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto requestDto) {
        // Implementation for generating a ticket
        String vehicleNumber = requestDto.getVehicleNumber();
        VehicleType vehicleType = requestDto.getVehicleType();
        Long gateId = requestDto.getGateId();

        Ticket ticket = ticketService.generateTicket(gateId, vehicleNumber, vehicleType);
        GenerateTicketResponseDto responseDto = new GenerateTicketResponseDto();
        responseDto.setTicketId(ticket.getId());
        responseDto.setOperatorName(ticket.getOperator().getName());
        responseDto.setSpotNumber(ticket.getParkingSpot().getNumber());
        responseDto.setStatus(ResponseStatus.SUCCESS);

        return responseDto;
    }
}
