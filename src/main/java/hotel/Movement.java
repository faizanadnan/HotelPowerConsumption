package hotel;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Movement {
    public static final LocalTime SIX_MORNING = LocalTime.of(06, 00, 00);
    public static final LocalTime MID_NIGHT = LocalTime.of(00, 00);
    public static final LocalTime SIX_EVENING = LocalTime.of(18, 00);

    private final int floor;
    private final int subCorridorNumber;
    private final LocalTime movementTime;


    public boolean isItNight(){
        return (movementTime.isBefore(SIX_MORNING) && movementTime.isAfter(MID_NIGHT) )
                || movementTime.isAfter(SIX_EVENING) && movementTime.isBefore(MID_NIGHT)
                || movementTime.equals(MID_NIGHT);
    }
}
