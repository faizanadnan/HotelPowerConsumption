package hotel;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;

@Data
@EqualsAndHashCode(callSuper=false)
public class HotelAutomationSystem extends SubmissionPublisher<HotelAutomationSystem> {

    public static final int ONE_SEC = 1000;
    public static final int MAX_BUFFER_CAPACITY = 1;

    private Hotel hotel;
    private Movement movement;
    public HotelAutomationSystem(Hotel hotel) {
        super(Executors.newSingleThreadExecutor(), MAX_BUFFER_CAPACITY);
        this.hotel = hotel;
    }

    public void automateElectricEquipment(Movement movement) {
        this.movement = movement;
        HotelAutomationSystem hotelAutomationSystem = new HotelAutomationSystem(hotel);
        hotelAutomationSystem.subscribe(hotel);
        hotelAutomationSystem.submit(this);
        sleep();
        System.out.println(hotel);
    }

    private void sleep() {
        try {
            Thread.sleep(ONE_SEC);
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread Interrupted", e);
        }
    }
}
