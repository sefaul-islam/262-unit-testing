package parking;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSystemTest {


    @Test
    public void singleTonInstanceTest(){
        ParkingSystem system1 = ParkingSystem.getInstance();
        ParkingSystem system2 = ParkingSystem.getInstance();
        assertSame(system1, system2);
    }
    @Test
    public void shouldBookParkingBicycleInAllSlotsTest() {
        ParkingSystem system = ParkingSystem.getInstance();
        system.resetForTesting();
        Vehicle vehicle = new Vehicle(1, VehicleType.BICYCLE, 100.0);
        double vehicleTypeRate = 0.2;

        // 1. COMPACT testing (multiplier = 0.8)
        LocalDateTime startCompact = LocalDateTime.of(2026, 10, 1, 10, 0);
        LocalDateTime endCompact = LocalDateTime.of(2026, 10, 1, 11, 0);
        double hoursCompact = java.time.Duration.between(startCompact, endCompact).toHours();
        ParkingSlot compactParkingSlot = new ParkingSlot("1", ParkingSlotType.COMPACT);
        Booking compactBooking = system.book(vehicle, compactParkingSlot, startCompact, endCompact);
        double priceCompact = hoursCompact * 10.0 * vehicleTypeRate * 0.8; // 1 * 10 * 0.2 * 0.8 = 1.6
        assertEquals(priceCompact, compactBooking.getAmount(), 0.001);

        // 2. REGULAR testing (multiplier = 1.0)
        LocalDateTime startRegular = LocalDateTime.of(2026, 10, 1, 11, 1);
        LocalDateTime endRegular = LocalDateTime.of(2026, 10, 1, 12, 1);
        double hoursRegular = java.time.Duration.between(startRegular, endRegular).toHours();
        ParkingSlot regularParkingSlot = new ParkingSlot("2", ParkingSlotType.REGULAR);
        Booking regularBooking = system.book(vehicle, regularParkingSlot, startRegular, endRegular);
        double priceRegular = hoursRegular * 10.0 * vehicleTypeRate * 1.0; // 1 * 10 * 0.2 * 1.0 = 2.0
        assertEquals(priceRegular, regularBooking.getAmount(), 0.001);

        // 3. LARGE testing (multiplier = 1.5)
        LocalDateTime startLarge = LocalDateTime.of(2026, 10, 1, 12, 2);
        LocalDateTime endLarge = LocalDateTime.of(2026, 10, 1, 13, 2);
        double hoursLarge = java.time.Duration.between(startLarge, endLarge).toHours();
        ParkingSlot largeParkingSlot = new ParkingSlot("3", ParkingSlotType.LARGE);
        Booking largeBooking = system.book(vehicle, largeParkingSlot, startLarge, endLarge);
        double priceLarge = hoursLarge * 10.0 * vehicleTypeRate * 1.5; // 1 * 10 * 0.2 * 1.5 = 3.0
        assertEquals(priceLarge, largeBooking.getAmount(), 0.001);

        // 4. HANDICAPPED testing (multiplier = 1.2)
        LocalDateTime startHandicapped = LocalDateTime.of(2026, 10, 1, 13, 3);
        LocalDateTime endHandicapped = LocalDateTime.of(2026, 10, 1, 14, 3);
        double hoursHandicapped = java.time.Duration.between(startHandicapped, endHandicapped).toHours();
        ParkingSlot handicappedParkingSlot = new ParkingSlot("4", ParkingSlotType.HANDICAPPED);
        Booking handicappedBooking = system.book(vehicle, handicappedParkingSlot, startHandicapped, endHandicapped);
        double priceHandicapped = hoursHandicapped * 10.0 * vehicleTypeRate * 1.2; // 1 * 10 * 0.2 * 1.2 = 2.4
        assertEquals(priceHandicapped, handicappedBooking.getAmount(), 0.001);

        // 5. Final balance and booking count verification
        // Total cost: 1.6 + 2.0 + 3.0 + 2.4 = 9.0
        double totalCost = priceCompact + priceRegular + priceLarge + priceHandicapped;
        assertEquals(100.0 - totalCost, vehicle.getBalance(), 0.001); // 91.0
        assertEquals(totalCost, system.getBalance(), 0.001);          // 9.0
        assertEquals(4, system.getBookings().size());
    }

    @Test
    public void shouldBookParkingMotorcycleInAllSlotsTest() {
        ParkingSystem system = ParkingSystem.getInstance();
        system.resetForTesting();
        Vehicle vehicle = new Vehicle(2, VehicleType.MOTORCYCLE, 100.0);
        double vehicleTypeRate = 0.5;

        // 1. COMPACT testing (multiplier = 0.8)
        LocalDateTime startCompact = LocalDateTime.of(2026, 10, 1, 10, 0);
        LocalDateTime endCompact = LocalDateTime.of(2026, 10, 1, 11, 0);
        double hoursCompact = java.time.Duration.between(startCompact, endCompact).toHours();
        ParkingSlot compactParkingSlot = new ParkingSlot("1", ParkingSlotType.COMPACT);
        Booking compactBooking = system.book(vehicle, compactParkingSlot, startCompact, endCompact);
        double priceCompact = hoursCompact * 10.0 * vehicleTypeRate * 0.8; // 1 * 10 * 0.5 * 0.8 = 4.0
        assertEquals(priceCompact, compactBooking.getAmount(), 0.001);

        // 2. REGULAR testing (multiplier = 1.0)
        LocalDateTime startRegular = LocalDateTime.of(2026, 10, 1, 11, 1);
        LocalDateTime endRegular = LocalDateTime.of(2026, 10, 1, 12, 1);
        double hoursRegular = java.time.Duration.between(startRegular, endRegular).toHours();
        ParkingSlot regularParkingSlot = new ParkingSlot("2", ParkingSlotType.REGULAR);
        Booking regularBooking = system.book(vehicle, regularParkingSlot, startRegular, endRegular);
        double priceRegular = hoursRegular * 10.0 * vehicleTypeRate * 1.0; // 1 * 10 * 0.5 * 1.0 = 5.0
        assertEquals(priceRegular, regularBooking.getAmount(), 0.001);

        // 3. LARGE testing (multiplier = 1.5)
        LocalDateTime startLarge = LocalDateTime.of(2026, 10, 1, 12, 2);
        LocalDateTime endLarge = LocalDateTime.of(2026, 10, 1, 13, 2);
        double hoursLarge = java.time.Duration.between(startLarge, endLarge).toHours();
        ParkingSlot largeParkingSlot = new ParkingSlot("3", ParkingSlotType.LARGE);
        Booking largeBooking = system.book(vehicle, largeParkingSlot, startLarge, endLarge);
        double priceLarge = hoursLarge * 10.0 * vehicleTypeRate * 1.5; // 1 * 10 * 0.5 * 1.5 = 7.5
        assertEquals(priceLarge, largeBooking.getAmount(), 0.001);

        // 4. Incompatible slot check: HANDICAPPED should be rejected
        LocalDateTime startHandicapped = LocalDateTime.of(2026, 10, 1, 13, 3);
        LocalDateTime endHandicapped = LocalDateTime.of(2026, 10, 1, 14, 3);
        ParkingSlot handicappedParkingSlot = new ParkingSlot("4", ParkingSlotType.HANDICAPPED);
        assertThrows(IllegalArgumentException.class, () -> {
            system.book(vehicle, handicappedParkingSlot, startHandicapped, endHandicapped);
        });

        // 5. Final balance and booking count verification
        // Total cost: 4.0 + 5.0 + 7.5 = 16.5
        double totalCost = priceCompact + priceRegular + priceLarge;
        assertEquals(100.0 - totalCost, vehicle.getBalance(), 0.001); // 83.5
        assertEquals(totalCost, system.getBalance(), 0.001);          // 16.5
        assertEquals(3, system.getBookings().size());
    }

    @Test
    public void shouldBookParkingMicrocarInAllSlotsTest() {
        ParkingSystem system = ParkingSystem.getInstance();
        system.resetForTesting();
        Vehicle vehicle = new Vehicle(3, VehicleType.MICROCAR, 100.0);
        double vehicleTypeRate = 1.5;

        // 1. COMPACT testing (multiplier = 0.8)
        LocalDateTime startCompact = LocalDateTime.of(2026, 10, 1, 10, 0);
        LocalDateTime endCompact = LocalDateTime.of(2026, 10, 1, 11, 0);
        double hoursCompact = java.time.Duration.between(startCompact, endCompact).toHours();
        ParkingSlot compactParkingSlot = new ParkingSlot("1", ParkingSlotType.COMPACT);
        Booking compactBooking = system.book(vehicle, compactParkingSlot, startCompact, endCompact);
        double priceCompact = hoursCompact * 10.0 * vehicleTypeRate * 0.8; // 1 * 10 * 1.5 * 0.8 = 12.0
        assertEquals(priceCompact, compactBooking.getAmount(), 0.001);

        // 2. REGULAR testing (multiplier = 1.0)
        LocalDateTime startRegular = LocalDateTime.of(2026, 10, 1, 11, 1);
        LocalDateTime endRegular = LocalDateTime.of(2026, 10, 1, 12, 1);
        double hoursRegular = java.time.Duration.between(startRegular, endRegular).toHours();
        ParkingSlot regularParkingSlot = new ParkingSlot("2", ParkingSlotType.REGULAR);
        Booking regularBooking = system.book(vehicle, regularParkingSlot, startRegular, endRegular);
        double priceRegular = hoursRegular * 10.0 * vehicleTypeRate * 1.0; // 1 * 10 * 1.5 * 1.0 = 15.0
        assertEquals(priceRegular, regularBooking.getAmount(), 0.001);

        // 3. Incompatible slot check: LARGE should be rejected
        LocalDateTime startLarge = LocalDateTime.of(2026, 10, 1, 12, 2);
        LocalDateTime endLarge = LocalDateTime.of(2026, 10, 1, 13, 2);
        ParkingSlot largeParkingSlot = new ParkingSlot("3", ParkingSlotType.LARGE);
        assertThrows(IllegalArgumentException.class, () -> {
            system.book(vehicle, largeParkingSlot, startLarge, endLarge);
        });

        // 4. Incompatible slot check: HANDICAPPED should be rejected
        LocalDateTime startHandicapped = LocalDateTime.of(2026, 10, 1, 13, 3);
        LocalDateTime endHandicapped = LocalDateTime.of(2026, 10, 1, 14, 3);
        ParkingSlot handicappedParkingSlot = new ParkingSlot("4", ParkingSlotType.HANDICAPPED);
        assertThrows(IllegalArgumentException.class, () -> {
            system.book(vehicle, handicappedParkingSlot, startHandicapped, endHandicapped);
        });

        // 5. Final balance and booking count verification
        // Total cost: 12.0 + 15.0 = 27.0
        double totalCost = priceCompact + priceRegular;
        assertEquals(100.0 - totalCost, vehicle.getBalance(), 0.001); // 73.0
        assertEquals(totalCost, system.getBalance(), 0.001);          // 27.0
        assertEquals(2, system.getBookings().size());
    }

    @Test
    public void shouldBookParkingBusInAllSlotsTest() {
        ParkingSystem system = ParkingSystem.getInstance();
        system.resetForTesting();
        Vehicle vehicle = new Vehicle(4, VehicleType.BUS, 100.0);
        double vehicleTypeRate = 2.0;

        // 1. LARGE testing (multiplier = 1.5) - only compatible slot for BUS
        LocalDateTime startLarge = LocalDateTime.of(2026, 10, 1, 10, 0);
        LocalDateTime endLarge = LocalDateTime.of(2026, 10, 1, 11, 0);
        double hoursLarge = java.time.Duration.between(startLarge, endLarge).toHours();
        ParkingSlot largeParkingSlot = new ParkingSlot("1", ParkingSlotType.LARGE);
        Booking largeBooking = system.book(vehicle, largeParkingSlot, startLarge, endLarge);
        double priceLarge = hoursLarge * 10.0 * vehicleTypeRate * 1.5; // 1 * 10 * 2.0 * 1.5 = 30.0
        assertEquals(priceLarge, largeBooking.getAmount(), 0.001);

        // 2. Incompatible slot check: COMPACT should be rejected
        LocalDateTime startCompact = LocalDateTime.of(2026, 10, 1, 11, 1);
        LocalDateTime endCompact = LocalDateTime.of(2026, 10, 1, 12, 1);
        ParkingSlot compactParkingSlot = new ParkingSlot("2", ParkingSlotType.COMPACT);
        assertThrows(IllegalArgumentException.class, () -> {
            system.book(vehicle, compactParkingSlot, startCompact, endCompact);
        });

        // 3. Incompatible slot check: REGULAR should be rejected
        LocalDateTime startRegular = LocalDateTime.of(2026, 10, 1, 12, 2);
        LocalDateTime endRegular = LocalDateTime.of(2026, 10, 1, 13, 2);
        ParkingSlot regularParkingSlot = new ParkingSlot("3", ParkingSlotType.REGULAR);
        assertThrows(IllegalArgumentException.class, () -> {
            system.book(vehicle, regularParkingSlot, startRegular, endRegular);
        });

        // 4. Incompatible slot check: HANDICAPPED should be rejected
        LocalDateTime startHandicapped = LocalDateTime.of(2026, 10, 1, 13, 3);
        LocalDateTime endHandicapped = LocalDateTime.of(2026, 10, 1, 14, 3);
        ParkingSlot handicappedParkingSlot = new ParkingSlot("4", ParkingSlotType.HANDICAPPED);
        assertThrows(IllegalArgumentException.class, () -> {
            system.book(vehicle, handicappedParkingSlot, startHandicapped, endHandicapped);
        });

        // 5. Final balance and booking count verification
        // Total cost: 30.0
        assertEquals(100.0 - priceLarge, vehicle.getBalance(), 0.001); // 70.0
        assertEquals(priceLarge, system.getBalance(), 0.001);          // 30.0
        assertEquals(1, system.getBookings().size());
    }

    @Test
    public void shouldBookParkingCarInAllSlotsTest() {
        ParkingSystem system = ParkingSystem.getInstance();
        system.resetForTesting();
        Vehicle vehicle = new Vehicle(5, VehicleType.CAR, 100.0);
        double vehicleTypeRate = 1.0;

        // 1. REGULAR (1 hr * 10 * 1.0 * 1.0 = 10.0)
        LocalDateTime start1 = LocalDateTime.of(2026, 10, 1, 10, 0);
        LocalDateTime end1 = LocalDateTime.of(2026, 10, 1, 11, 0);
        ParkingSlot regularSlot = new ParkingSlot("1", ParkingSlotType.REGULAR);
        Booking regularBooking = system.book(vehicle, regularSlot, start1, end1);
        assertEquals(10.0, regularBooking.getAmount(), 0.001);

        // 2. LARGE (1 hr * 10 * 1.0 * 1.5 = 15.0)
        LocalDateTime start2 = LocalDateTime.of(2026, 10, 1, 11, 1);
        LocalDateTime end2 = LocalDateTime.of(2026, 10, 1, 12, 1);
        ParkingSlot largeSlot = new ParkingSlot("2", ParkingSlotType.LARGE);
        Booking largeBooking = system.book(vehicle, largeSlot, start2, end2);
        assertEquals(15.0, largeBooking.getAmount(), 0.001);

        // 3. Incompatible: COMPACT & HANDICAPPED
        assertThrows(IllegalArgumentException.class, () ->
                system.book(vehicle, new ParkingSlot("3", ParkingSlotType.COMPACT), start1, end1));
        assertThrows(IllegalArgumentException.class, () ->
                system.book(vehicle, new ParkingSlot("4", ParkingSlotType.HANDICAPPED), start1, end1));

        assertEquals(100.0 - 25.0, vehicle.getBalance(), 0.001); // 75.0
        assertEquals(25.0, system.getBalance(), 0.001);
    }

    @Test
    public void shouldThrowExceptionForInvalidBookingTimesTest() {
        ParkingSystem system = ParkingSystem.getInstance();
        system.resetForTesting();
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 100.0);
        ParkingSlot slot = new ParkingSlot("1", ParkingSlotType.REGULAR);

        LocalDateTime start = LocalDateTime.of(2026, 10, 1, 12, 0);

        // Case A: End time is BEFORE start time
        LocalDateTime endBefore = LocalDateTime.of(2026, 10, 1, 11, 0);
        assertThrows(IllegalBookingTimeException.class, () -> {
            system.book(vehicle, slot, start, endBefore);
        });

        // Case B: End time is EQUAL to start time (duration = 0)
        assertThrows(IllegalBookingTimeException.class, () -> {
            system.book(vehicle, slot, start, start);
        });
    }

    @Test
    public void shouldCompleteBookingAndTransferFundsTest() {
        ParkingSystem system = ParkingSystem.getInstance();
        system.resetForTesting();
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 100.0);
        ParkingSlot slot = new ParkingSlot("1", ParkingSlotType.REGULAR);

        LocalDateTime start = LocalDateTime.of(2026, 10, 1, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 10, 1, 12, 0); // 2 hours = 20.0

        Booking booking = system.book(vehicle, slot, start, end);
        assertEquals(20.0, system.getBalance(), 0.001);
        assertEquals(0.0, slot.getBalance(), 0.001);

        // Complete booking
        system.completeBooking(booking);

        assertEquals(BookingStatus.COMPLETED, booking.getBookingStatus());
        assertEquals(16.0, slot.getBalance(), 0.001);   // 80% of 20.0 = 16.0
        assertEquals(4.0, system.getBalance(), 0.001);  // 20% retained by system = 4.0
    }


    @Test
    public void shouldCancelBookingAndRefundFundsTest() {
        ParkingSystem system = ParkingSystem.getInstance();
        system.resetForTesting();
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 100.0);
        ParkingSlot slot = new ParkingSlot("1", ParkingSlotType.REGULAR);

        LocalDateTime start = LocalDateTime.of(2026, 10, 1, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 10, 1, 12, 0); // 2 hours = 20.0

        Booking booking = system.book(vehicle, slot, start, end);
        assertEquals(80.0, vehicle.getBalance(), 0.001);

        // Cancel booking
        system.cancelBooking(booking);

        assertEquals(BookingStatus.CANCELLED, booking.getBookingStatus());
        assertEquals(98.0, vehicle.getBalance(), 0.001); // 80.0 + 90% of 20 (18.0) = 98.0
        assertEquals(2.0, system.getBalance(), 0.001);   // 10% retained by system = 2.0
    }

    @Test
    public void shouldRejectBookingWhenSlotIsInactiveOrOccupiedTest() {
        ParkingSystem system = ParkingSystem.getInstance();
        system.resetForTesting();
        Vehicle v1 = new Vehicle(1, VehicleType.CAR, 100.0);
        Vehicle v2 = new Vehicle(2, VehicleType.CAR, 100.0);
        ParkingSlot slot = new ParkingSlot("1", ParkingSlotType.REGULAR);

        LocalDateTime start = LocalDateTime.of(2026, 10, 1, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 10, 1, 12, 0);

        // 1. Deactivated slot
        slot.deactivate();
        assertThrows(IllegalArgumentException.class, () -> system.book(v1, slot, start, end));

        // 2. Reactivate and book first vehicle
        slot.activate();
        system.book(v1, slot, start, end);

        // 3. Second vehicle attempts to book overlapping time window
        LocalDateTime overlapStart = LocalDateTime.of(2026, 10, 1, 11, 0);
        LocalDateTime overlapEnd = LocalDateTime.of(2026, 10, 1, 13, 0);
        assertThrows(IllegalArgumentException.class, () -> system.book(v2, slot, overlapStart, overlapEnd));
    }


}
