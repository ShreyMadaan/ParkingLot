import controller.TicketController;
import repository.GateRepository;
import repository.ParkingLotRepository;
import repository.TicketRepository;
import repository.VehicleRepository;
import service.TicketService;
import strategies.spotassignmentstratergy.RandomSpotAssignmentStrategy;
import strategies.spotassignmentstratergy.SpotAssignmentStrategy;

public class ParkingLotApplication {
    public static void main(String[] args) {
        GateRepository gateRepository = new GateRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        TicketRepository ticketRepository = new TicketRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();

        SpotAssignmentStrategy spotAssignmentStrategy = new RandomSpotAssignmentStrategy();

        TicketService ticketService = new TicketService(gateRepository, vehicleRepository, spotAssignmentStrategy, ticketRepository, parkingLotRepository);
        TicketController ticketController = new TicketController(ticketService);

        System.out.println("Application has started on port:8080");
    }
}
