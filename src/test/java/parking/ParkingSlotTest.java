package parking;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ParkingSlotTest {
    
    @Test
    public void shouldBeAvailableWhenNoBookingsExistTest() {
        ParkingSlot slot = new ParkingSlot("1", ParkingSlotType.REGULAR);
        LocalDateTime start = LocalDateTime.of(2026, 10, 1, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 10, 1, 12, 0);
        assertTrue(slot.isAvailable(start, end));
    }

    @Test
    public void shouldBeAvailableForNonOverlappingTimesTest() {
        ParkingSlot slot = new ParkingSlot("1", ParkingSlotType.REGULAR);
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 100.0);

        // Existing booking from 12:00 to 14:00
        LocalDateTime existingStart = LocalDateTime.of(2026, 10, 1, 12, 0);
        LocalDateTime existingEnd = LocalDateTime.of(2026, 10, 1, 14, 0);
        Booking booking = new Booking(1, vehicle, slot, existingStart, existingEnd, 20.0);
        slot.getBookings().add(booking);

        // Case A: Strictly before (10:00 to 11:30)
        assertTrue(slot.isAvailable(
                LocalDateTime.of(2026, 10, 1, 10, 0),
                LocalDateTime.of(2026, 10, 1, 11, 30)));

        // Case B: Boundary before (ends exactly when existing starts: 10:00 to 12:00)
        assertTrue(slot.isAvailable(
                LocalDateTime.of(2026, 10, 1, 10, 0),
                LocalDateTime.of(2026, 10, 1, 12, 0)));

        // Case C: Boundary after (starts exactly when existing ends: 14:00 to 16:00)
        assertTrue(slot.isAvailable(
                LocalDateTime.of(2026, 10, 1, 14, 0),
                LocalDateTime.of(2026, 10, 1, 16, 0)));

        // Case D: Strictly after (15:00 to 17:00)
        assertTrue(slot.isAvailable(
                LocalDateTime.of(2026, 10, 1, 15, 0),
                LocalDateTime.of(2026, 10, 1, 17, 0)));
    }

    @Test
    public void shouldNotBeAvailableForOverlappingTimesTest() {


        ParkingSlot slot = new ParkingSlot("1", ParkingSlotType.REGULAR);
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 100.0);

        // Existing booking from 12:00 to 14:00
        LocalDateTime existingStart = LocalDateTime.of(2026, 10, 1, 12, 0);
        LocalDateTime existingEnd = LocalDateTime.of(2026, 10, 1, 14, 0);
        Booking booking = new Booking(1, vehicle, slot, existingStart, existingEnd, 20.0);
        slot.getBookings().add(booking);

        // Case A: Exact match (12:00 to 14:00)
        assertFalse(slot.isAvailable(existingStart, existingEnd));

        // Case B: Starts before, ends inside (11:00 to 13:00)
        assertFalse(slot.isAvailable(
                LocalDateTime.of(2026, 10, 1, 11, 0),
                LocalDateTime.of(2026, 10, 1, 13, 0)));

        // Case C: Starts inside, ends after (13:00 to 15:00)
        assertFalse(slot.isAvailable(
                LocalDateTime.of(2026, 10, 1, 13, 0),
                LocalDateTime.of(2026, 10, 1, 15, 0)));

        // Case D: Completely enclosing (11:00 to 15:00)
        assertFalse(slot.isAvailable(
                LocalDateTime.of(2026, 10, 1, 11, 0),
                LocalDateTime.of(2026, 10, 1, 15, 0)));

        // Case E: Strictly inside (12:30 to 13:30)
        assertFalse(slot.isAvailable(
                LocalDateTime.of(2026, 10, 1, 12, 30),
                LocalDateTime.of(2026, 10, 1, 13, 30)));
    }

}
