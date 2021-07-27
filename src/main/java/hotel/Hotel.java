package hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import entity.Floor;
import service.HotelPowerConsumptionService;

import java.util.List;
import java.util.concurrent.Flow;

@Slf4j
@Getter
public class Hotel implements Flow.Subscriber<HotelAutomationSystem> {

    private final HotelPowerConsumptionService hotelPowerConsumptionService;
    private final String name;
    private List<Floor> floor;

    Flow.Subscription subscription = null;

    public Hotel(HotelPowerConsumptionService hotelPowerConsumptionService, String name, List<Floor> floor) {
        this.hotelPowerConsumptionService = hotelPowerConsumptionService;
        this.name = name;
        this.floor = floor;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(HotelAutomationSystem hotelAutomationSystem) {
        subscription.request(1);
        hotelPowerConsumptionService.automate(hotelAutomationSystem);
    }


    @Override
    public void onError(Throwable throwable) {
        log.error("Failed wih error", throwable);
    }

    @Override
    public void onComplete() {
        log.debug("Process completed");
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < floor.size(); i++) {
            Floor floor = this.floor.get(0);
            stringBuffer.append("Floor " + floor.getNumber() + "\n");

            for (int j = 0; j < floor.getCorridors().size(); j++) {
                stringBuffer.append(floor.getCorridors().get(j).toString() + "\n");
            }

        }
        return stringBuffer.toString();
    }
}
