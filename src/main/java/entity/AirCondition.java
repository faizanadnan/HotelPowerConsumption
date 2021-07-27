package entity;

public class AirCondition extends ElectricEquipment {
    private static final Double CONSUMPTION = Double.valueOf(10);
    public AirCondition(State state){
        super(state);
    }

    @Override
    public String toString() {
        return "AirCondition : "+ (getState() == State.ON ? "ON" : "OFF");
    }

    @Override
    public Double getPower() {
        return CONSUMPTION;
    }
}
