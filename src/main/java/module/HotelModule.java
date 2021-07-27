package module;

import entity.Floor;
import hotel.Hotel;
import hotel.HotelAutomationSystem;
import service.HotelPowerConsumptionService;

import java.util.List;

public class HotelModule {

    public Hotel getHotel(String hotelName, List<Floor> floors) {
        return new Hotel(getHotelPowerConsumptionService(),hotelName,floors);
    }

    public HotelPowerConsumptionService getHotelPowerConsumptionService() {
        return new HotelPowerConsumptionService();
    }
}
