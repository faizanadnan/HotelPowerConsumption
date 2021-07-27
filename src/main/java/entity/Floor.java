package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Floor {
    private List<Corridor> corridors;
    private int number;


    public double getTotalEnergyConsumed() {
        return corridors.stream().mapToDouble(corr -> corr.getTotalPowerConsumption()).sum();
    }

    public List<Corridor> findAllCorridor(CorridorType corridorType){
        return corridors.stream()
                .filter(corridor -> corridor.getCorridorType().equals(corridorType))
                .collect(Collectors.toList());
    }
}
