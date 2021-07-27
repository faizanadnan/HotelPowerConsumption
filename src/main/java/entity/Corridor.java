package entity;

import hotel.Hotel;
import hotel.Movement;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
public class Corridor {
    private int corridorNumber;
    private List<ElectricEquipment> electricEquipments;
    private CorridorType corridorType;

    @Override
    public String toString() {
        return corridorType.corridorType +" " + corridorNumber + " "+ electricEquipments;
    }

    public Double getTotalPowerConsumption(){
        return electricEquipments
                .stream()
                .mapToDouble(electricEquipment-> electricEquipment.getPower())
                .sum();
    }

    public static Optional<Corridor> findCorridor(Hotel hotel, Movement movement, CorridorType corridorType) {
        return hotel.getFloor()
                .stream()
                .filter(floorInfo -> floorInfo.getNumber() == movement.getFloor())
                .map(floorInfo -> floorInfo.getCorridors())
                .flatMap(corridors -> corridors.stream())
                .filter(corridor -> corridor.getCorridorType().equals(corridorType))
                .filter(corridor -> corridor.getCorridorNumber() == movement.getSubCorridorNumber())
                .findFirst();
    }
}
