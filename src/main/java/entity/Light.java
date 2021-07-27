package entity;

public class Light extends ElectricEquipment {
    private static final Double CONSUMPTION = Double.valueOf(5);
    private final int number;

    public Light(State state, int number) {
        super(state);
        this.number = number;
    }

    @Override
    public String toString() {
        return "Light "+number+" : " + (getState() == State.ON ? "ON" : "OFF");
    }

    @Override
    public Double getPower() {
        return CONSUMPTION;
    }
}
