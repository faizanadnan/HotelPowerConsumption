package service;

import hotel.Hotel;
import hotel.HotelAutomationSystem;
import hotel.Movement;
import entity.*;
import power.optimizer.PowerOptimizer;

import java.util.List;
import java.util.Optional;

public class HotelPowerConsumptionService {

    public void automate(HotelAutomationSystem hotelAutomationSystem) {
        Hotel hotel = hotelAutomationSystem.getHotel();
        Movement movement = getMovement(hotelAutomationSystem);
        if (movement == null) return;
        Optional<Corridor> subCorridor = Corridor.findCorridor(hotel, movement, CorridorType.SUB_CORRIDOR);
        switchOnElectricEquipment(subCorridor);
        optimizePower(hotel);
    }

    private void optimizePower(Hotel hotel) {
        for (Floor floor: hotel.getFloor())
            PowerOptimizer.optimizePower(floor);
    }

    private void switchOnElectricEquipment(Optional<Corridor> subCorridor) {
        subCorridor.ifPresent(corridorInfo-> {
            List<ElectricEquipment> electricEquipments = corridorInfo.getElectricEquipments();
            for (ElectricEquipment electricEquipment: electricEquipments) {
                if (electricEquipment instanceof Light)
                    electricEquipment.setState(State.ON);
            }
        });
    }

    private Movement getMovement(HotelAutomationSystem hotelAutomationSystem) {
        Optional<Movement> optionalMovement = Optional.ofNullable(hotelAutomationSystem.getMovement());
        if (!optionalMovement.isPresent() || !optionalMovement.get().isItNight())
            return null;

        Movement movement = optionalMovement.get();
        return movement;
    }
}
