package hotel;

import entity.*;
import module.HotelModule;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class HotelAutomationTest {

    public static final String HAYAT = "Hayat";
    HotelModule hotelModule = new HotelModule();


    @Test
    void automateElectricEquipmentWithPowerOptimizationTest() {
        List<Movement> movements = Arrays.asList(new Movement(1, 1, LocalTime.MIDNIGHT),
                new Movement(1, 2, LocalTime.MIDNIGHT), new Movement(1, 3, LocalTime.MIDNIGHT));

        Corridor mainCorridor = new Corridor(1,Arrays.asList(new AirCondition(State.ON),new Light(State.ON, 1)),CorridorType.MAIN_CORRIDOR);
        Corridor subCorridorOne = new Corridor(1,Arrays.asList(new AirCondition(State.ON),new Light(State.OFF, 1)),CorridorType.SUB_CORRIDOR);
        Corridor subCorridorTwo = new Corridor(2, Arrays.asList(new AirCondition(State.ON),new Light(State.OFF, 1)), CorridorType.SUB_CORRIDOR);
        Corridor subCorridorThree = new Corridor(3, Arrays.asList(new AirCondition(State.ON),new Light(State.OFF, 1)), CorridorType.SUB_CORRIDOR);

        Floor floor = new Floor(Arrays.asList(mainCorridor, subCorridorOne, subCorridorTwo, subCorridorThree),1);
        Hotel hotel = hotelModule.getHotel(HAYAT, Arrays.asList(floor));


        HotelAutomationSystem hotelAutomationSystem = new HotelAutomationSystem(hotel);
        movements.forEach(movement -> {
            hotelAutomationSystem.automateElectricEquipment(movement);
        });
        String name = hotel.getName();
        assertEquals(HAYAT, name);
        Floor floorInfo = hotel
                .getFloor()
                .get(0);
        int number = floorInfo.getNumber();
        assertTrue(number == 1);
        List<Corridor> corridors = floorInfo.getCorridors();
        assertEquals(corridors.size(), 4);

        Corridor expectedMainCorridor = corridors.get(0);
        assertEquals(expectedMainCorridor.getCorridorType(), CorridorType.MAIN_CORRIDOR);
        assertArrayEquals(expectedMainCorridor.getElectricEquipments().toArray(), new Object[]{new AirCondition(State.ON),new Light(State.ON, 1)});


        Corridor expectedSubCorridorOne = corridors.get(1);
        assertArrayEquals(expectedSubCorridorOne.getElectricEquipments().toArray(), new Object[]{new AirCondition(State.OFF),new Light(State.ON, 1)});
        Corridor expectedSubCorridorTwo = corridors.get(2);
        assertArrayEquals(expectedSubCorridorTwo.getElectricEquipments().toArray(), new Object[]{new AirCondition(State.OFF),new Light(State.ON, 1)});
        Corridor expectedSubCorridorThree = corridors.get(3);
        assertArrayEquals(expectedSubCorridorThree.getElectricEquipments().toArray(), new Object[]{new AirCondition(State.OFF),new Light(State.ON, 1)});
    }

    @Test
    void automateElectricEquipmentWithAllowedPowerConsumptionTest() {
        Corridor mainCorridor = new Corridor(1,Arrays.asList(new AirCondition(State.ON),new Light(State.ON, 1)),CorridorType.MAIN_CORRIDOR);
        Floor floor = new Floor(Arrays.asList(mainCorridor),1);
        Hotel hotel = hotelModule.getHotel(HAYAT, Arrays.asList(floor));

        HotelAutomationSystem hotelAutomationSystem = new HotelAutomationSystem(hotel);
            hotelAutomationSystem.automateElectricEquipment(null);


        String name = hotel.getName();
        assertEquals(HAYAT, name);
        Floor floorInfo = hotel
                .getFloor()
                .get(0);
        int number = floorInfo.getNumber();
        assertTrue(number == 1);
        List<Corridor> corridors = floorInfo.getCorridors();
        assertEquals(corridors.size(), 1);

        Corridor expectedMainCorridor = corridors.get(0);
        assertEquals(expectedMainCorridor.getCorridorType(), CorridorType.MAIN_CORRIDOR);
        assertArrayEquals(expectedMainCorridor.getElectricEquipments().toArray(), new Object[]{new AirCondition(State.ON),new Light(State.ON, 1)});
    }


    @Test
    void automateElectricEquipmentInDayTimeTest() {
        Corridor mainCorridor = new Corridor(1,Arrays.asList(new AirCondition(State.OFF),new Light(State.OFF, 1)),CorridorType.MAIN_CORRIDOR);
        Floor floor = new Floor(Arrays.asList(mainCorridor),1);
        Hotel hotel = hotelModule.getHotel(HAYAT, Arrays.asList(floor));
        HotelAutomationSystem hotelAutomationSystem = new HotelAutomationSystem(hotel);
        hotelAutomationSystem.automateElectricEquipment(new Movement(1, 1, LocalTime.MIDNIGHT));

        String name = hotel.getName();
        assertEquals(HAYAT, name);
        Floor floorInfo = hotel
                .getFloor()
                .get(0);
        int number = floorInfo.getNumber();
        assertTrue(number == 1);
        List<Corridor> corridors = floorInfo.getCorridors();
        assertEquals(corridors.size(), 1);

        Corridor expectedMainCorridor = corridors.get(0);
        assertEquals(expectedMainCorridor.getCorridorType(), CorridorType.MAIN_CORRIDOR);
        assertArrayEquals(expectedMainCorridor.getElectricEquipments().toArray(), new Object[]{new AirCondition(State.OFF),new Light(State.OFF, 1)});
    }
}
