package parking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static parking.VehicleType.CAR;

public class VehicleTest {

    @Test
    public void returnProperInitialization(){
        Wallet wallet = new Wallet(500);

        Vehicle vehicle = new Vehicle(1, CAR,wallet);

        assertEquals(1,vehicle.getVehicleId());
        assertEquals(CAR,vehicle.getVehicleType());
        assertEquals(wallet,vehicle.getWallet());
        assertEquals(500,vehicle.getBalance());
    }
}
