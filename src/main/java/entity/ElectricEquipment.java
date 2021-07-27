package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class ElectricEquipment {
    private State state;

    public abstract Double getPower();

}
