package parking;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {
    @Test
    void constructor_shouldCreateActiveBooking() {

        // Step 1: Create a Vehicle
        Vehicle vehicle = new Vehicle(
                1,
                VehicleType.CAR,
                1000.0
        );

        // Step 2: Create a ParkingSlot
        ParkingSlot parkingSlot = new ParkingSlot(
                "S1",
                ParkingSlotType.REGULAR
        );

        // Step 3: Create start time
        LocalDateTime startTime =
                LocalDateTime.of(2026, 9, 29, 10, 0);

        // Step 4: Create end time
        LocalDateTime endTime =
                LocalDateTime.of(2026, 9, 29, 12, 0);

        // Step 5: Set booking ID
        int bookingId = 101;

        // Step 6: Set booking amount
        double amount = 200.0;

        // Step 7: Create Booking
        Booking booking = new Booking(
                bookingId,
                vehicle,
                parkingSlot,
                startTime,
                endTime,
                amount
        );

        // Step 8: Check every constructor value
        assertEquals(bookingId, booking.getBookingId());
        assertSame(vehicle, booking.getVehicle());
        assertSame(parkingSlot, booking.getParkingSlot());
        assertEquals(startTime, booking.getStartTime());
        assertEquals(endTime, booking.getEndTime());
        assertEquals(amount, booking.getAmount());

        // Step 9: New bookings must initially be ACTIVE
        assertEquals(BookingStatus.ACTIVE, booking.getBookingStatus());
    }

    @Test
    void getBookingId_shouldReturnBookingId() {

        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 1000.0);
        ParkingSlot parkingSlot =
                new ParkingSlot("S1", ParkingSlotType.REGULAR);

        LocalDateTime start =
                LocalDateTime.of(2026, 9, 29, 10, 0);

        LocalDateTime end =
                LocalDateTime.of(2026, 9, 29, 12, 0);

        Booking booking =
                new Booking(101, vehicle, parkingSlot,
                        start, end, 200.0);

        assertEquals(101, booking.getBookingId());
    }

    @Test
    void completeBooking_shouldChangeStatusToCompleted() {

        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 1000.0);
        ParkingSlot parkingSlot =
                new ParkingSlot("S1", ParkingSlotType.REGULAR);

        LocalDateTime start =
                LocalDateTime.of(2026, 9, 29, 10, 0);

        LocalDateTime end =
                LocalDateTime.of(2026, 9, 29, 12, 0);

        Booking booking =
                new Booking(101, vehicle, parkingSlot,
                        start, end, 200.0);

        assertEquals(BookingStatus.ACTIVE,
                booking.getBookingStatus());

        booking.completeBooking();

        assertEquals(BookingStatus.COMPLETED,
                booking.getBookingStatus());
    }

    @Test
    void cancelBooking_shouldChangeStatusToCancelled() {

        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 1000.0);
        ParkingSlot parkingSlot =
                new ParkingSlot("S1", ParkingSlotType.REGULAR);

        LocalDateTime start =
                LocalDateTime.of(2026, 9, 29, 10, 0);

        LocalDateTime end =
                LocalDateTime.of(2026, 9, 29, 12, 0);

        Booking booking =
                new Booking(101, vehicle, parkingSlot,
                        start, end, 200.0);

        assertEquals(BookingStatus.ACTIVE,
                booking.getBookingStatus());

        booking.cancelBooking();

        assertEquals(BookingStatus.CANCELLED,
                booking.getBookingStatus());
    }
    @Test
    void toString_shouldContainBookingInformation() {

        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 1000.0);
        ParkingSlot parkingSlot =
                new ParkingSlot("S1", ParkingSlotType.REGULAR);

        LocalDateTime start =
                LocalDateTime.of(2026, 9, 29, 10, 0);

        LocalDateTime end =
                LocalDateTime.of(2026, 9, 29, 12, 0);

        Booking booking =
                new Booking(101, vehicle, parkingSlot,
                        start, end, 200.0);

        String result = booking.toString();

        assertTrue(result.contains("bookingId=101"));
        assertTrue(result.contains("vehicle="));
        assertTrue(result.contains("parkingSlot="));
        assertTrue(result.contains("startTime="));
        assertTrue(result.contains("endTime="));
        assertTrue(result.contains("amount=200.0"));
        assertTrue(result.contains("bookingStatus=ACTIVE"));
    }
    @Test
    void getVehicle_shouldReturnVehicle() {
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 1000.0);
        ParkingSlot parkingSlot = new ParkingSlot("S1", ParkingSlotType.REGULAR);

        LocalDateTime start = LocalDateTime.of(2026, 9, 29, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 29, 12, 0);

        Booking booking = new Booking(101, vehicle, parkingSlot, start, end, 200.0);

        assertSame(vehicle, booking.getVehicle());
    }

    @Test
    void getParkingSlot_shouldReturnParkingSlot() {
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 1000.0);
        ParkingSlot parkingSlot = new ParkingSlot("S1", ParkingSlotType.REGULAR);

        LocalDateTime start = LocalDateTime.of(2026, 9, 29, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 29, 12, 0);

        Booking booking = new Booking(101, vehicle, parkingSlot, start, end, 200.0);

        assertSame(parkingSlot, booking.getParkingSlot());
    }

    @Test
    void getStartTime_shouldReturnStartTime() {
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 1000.0);
        ParkingSlot parkingSlot = new ParkingSlot("S1", ParkingSlotType.REGULAR);

        LocalDateTime start = LocalDateTime.of(2026, 9, 29, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 29, 12, 0);

        Booking booking = new Booking(101, vehicle, parkingSlot, start, end, 200.0);

        assertEquals(start, booking.getStartTime());
    }

    @Test
    void getEndTime_shouldReturnEndTime() {
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 1000.0);
        ParkingSlot parkingSlot = new ParkingSlot("S1", ParkingSlotType.REGULAR);

        LocalDateTime start = LocalDateTime.of(2026, 9, 29, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 29, 12, 0);

        Booking booking = new Booking(101, vehicle, parkingSlot, start, end, 200.0);

        assertEquals(end, booking.getEndTime());
    }

    @Test
    void getAmount_shouldReturnAmount() {
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 1000.0);
        ParkingSlot parkingSlot = new ParkingSlot("S1", ParkingSlotType.REGULAR);

        LocalDateTime start = LocalDateTime.of(2026, 9, 29, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 29, 12, 0);

        Booking booking = new Booking(101, vehicle, parkingSlot, start, end, 200.0);

        assertEquals(200.0, booking.getAmount());
    }

    @Test
    void getBookingStatus_shouldInitiallyReturnActive() {
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 1000.0);
        ParkingSlot parkingSlot = new ParkingSlot("S1", ParkingSlotType.REGULAR);

        LocalDateTime start = LocalDateTime.of(2026, 9, 29, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 29, 12, 0);

        Booking booking = new Booking(101, vehicle, parkingSlot, start, end, 200.0);

        assertEquals(BookingStatus.ACTIVE, booking.getBookingStatus());
    }
}
