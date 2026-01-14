package service;

import exception.InvalidGateException;
import exception.NoAvailableSpotException;
import model.*;
import repository.GateRepository;
import repository.ParkingLotRepository;
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
    private ParkingLotRepository parkingLotRepository;

    public TicketService(GateRepository gateRepository,
                         VehicleRepository vehicleRepository,
                         SpotAssignmentStrategy spotAssignmentStrategy,
                         TicketRepository ticketRepository,
                         ParkingLotRepository parkingLotRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.ticketRepository = ticketRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public Ticket generateTicket(Long gateId, String vehicleNumber, VehicleType vehicleType)throws InvalidGateException, NoAvailableSpotException {

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

        Optional<ParkingLot> parkingLot = parkingLotRepository.getParkingLotOfGate(gate);
        if(parkingLot.isEmpty()){
            throw new RuntimeException("Parking Lot not found");
        }
        Optional<ParkingSpot> parkingSpotOptional = spotAssignmentStrategy.findSpot(vehicleType,parkingLot.get(), gate);

        if(parkingSpotOptional.isEmpty()){
            throw new NoAvailableSpotException("No available spot for the vehicle type");
        }
        ParkingSpot parkingSpot = parkingSpotOptional.get();

        Ticket ticket = new Ticket();
        ticket.setParkingSpot(parkingSpot);
        ticket.setGate(gate);
        ticket.setEntryTime(new Date());
        ticket.setOperator(operator);
        ticket.setVehicle(vehicle);

        return ticketRepository.save(ticket);
    }
}
