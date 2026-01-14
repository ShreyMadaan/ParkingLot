package strategies.spotassignmentstratergy;

import model.Gate;
import model.ParkingLot;
import model.ParkingSpot;
import model.VehicleType;

import java.util.Optional;

public interface SpotAssignmentStrategy {
    Optional<ParkingSpot> findSpot(VehicleType vehicleType, ParkingLot parkingLot, Gate gate);
}
