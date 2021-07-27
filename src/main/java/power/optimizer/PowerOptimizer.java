package power.optimizer;

import entity.*;

import java.util.List;

public class PowerOptimizer {

    public static void optimizePower(Floor floor) {

        double thresholdPower = (floor.findAllCorridor(CorridorType.MAIN_CORRIDOR).size() * 15)
                + (floor.findAllCorridor(CorridorType.SUB_CORRIDOR).size() * 10);

        double totalEnergyConsumed = floor.getTotalEnergyConsumed();

        List<Corridor> corridors = floor.getCorridors();
        for (int i = 0; i < corridors.size() && totalEnergyConsumed > thresholdPower; i++) {
            Corridor corridor = corridors.get(i);

            if (corridor.getCorridorType().equals(CorridorType.SUB_CORRIDOR)) {
                List<ElectricEquipment> electricEquipments = corridor.getElectricEquipments();

                for (ElectricEquipment electricEquipment: electricEquipments) {
                    if (electricEquipment instanceof AirCondition && electricEquipment.getState().equals(State.ON)) {
                        electricEquipment.setState(State.OFF);
                        totalEnergyConsumed -= electricEquipment.getPower();
                    }
                }
            }
        }

    }
}
