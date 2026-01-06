package service;

import exception.InvalidGateException;
import model.*;
import repository.GateRepository;
import repository.TicketRepository;
import repository.VehicleRepository;
import strategies.spotassignmentstratergy.SpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private SpotAssignmentStrategy spotAssignmentStrategy;
    private TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository,
                         VehicleRepository vehicleRepository,
                         SpotAssignmentStrategy spotAssignmentStrategy,
                         TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.ticketRepository = ticketRepository;
    }

    public Ticket generateTicket(Long gateId, String vehicleNumber, VehicleType vehicleType) {

        Optional<Gate> gateOptional = gateRepository.findGateById(gateId);
        if (gateOptional.isEmpty()) {
            throw new InvalidGateException("Invalid Gate Id");
        }
        Gate gate = gateOptional.get();
        Operator operator = gate.getCurrentOperator();

        Optional<Vehicle> vehicleOptional = vehicleRepository.findVehicleByNumber(vehicleNumber);
        Vehicle vehicle;
        if(vehicleOptional.isEmpty()){
            vehicle = new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setVehicleType(vehicleType);
            vehicle = vehicleRepository.save(vehicle);
        }else{
            vehicle = vehicleOptional.get();
        }

        ParkingSpot parkingSpot = spotAssignmentStrategy.findSpot();

        Ticket ticket = new Ticket();
        ticket.setParkingSpot(parkingSpot);
        ticket.setGate(gate);
        ticket.setEntryTime(new Date());
        ticket.setOperator(operator);
        ticket.setVehicle(vehicle);

        return ticketRepository.save(ticket);
    }
}
