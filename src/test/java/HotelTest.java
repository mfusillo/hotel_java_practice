import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class HotelTest {
    private Hotel hotel1;
    private Bedroom bedroom1;
    private Bedroom bedroom2;
    private Bedroom bedroom3;
    private ConferenceRoom conferenceRoom1;
    private Guest guest1;

    @Before
    public void before(){
        hotel1 = new Hotel();
        bedroom1 = new Bedroom(2, 2, "double", 20);
        bedroom2 = new Bedroom(2, 2, "double", 20);
        bedroom3 = new Bedroom(2, 2, "double", 20);
        conferenceRoom1 = new ConferenceRoom(10, "Castle View", 100);
        guest1 = new Guest("Blurb");
    }

    @Test
    public void check_bedrooms_list_starts_empty(){
        assertEquals(0, hotel1.getTotalBedrooms());
    }

    @Test
    public void check_conference_rooms_list_starts_empty(){
        assertEquals(0, hotel1.getTotalConferenceRooms());
    }

    @Test
    public void can_add_bedroom_to_bedrooms_list(){
        hotel1.addBedroom(bedroom1);
        assertEquals(1, hotel1.getTotalBedrooms());
    }

    @Test
    public void can_add_conference_room_to_conference_rooms(){
        hotel1.addConferenceRoom(conferenceRoom1);
        assertEquals(1, hotel1.getTotalConferenceRooms());
    }

    @Test
    public void can_check_in_guest_to_bedroom(){
        hotel1.checkInGuest(guest1, bedroom1);
        assertEquals(1, bedroom1.getNumberOfGuests());
        assertEquals(false, bedroom1.isAvailable());
    }

    @Test
    public void can_check_in_guest_to_conference_room(){
        hotel1.checkInGuest(guest1, conferenceRoom1);
        assertEquals(1, conferenceRoom1.getNumberOfGuests());
        assertEquals(false, conferenceRoom1.isAvailable());
    }

    @Test
    public void can_check_out_guest_from_bedroom(){
        hotel1.checkInGuest(guest1, bedroom1);
        hotel1.checkOutGuest(guest1, bedroom1);
        assertEquals(0, bedroom1.getNumberOfGuests());
        assertEquals(true, bedroom1.isAvailable());
    }

    @Test
    public void can_add_to_bookings_list(){
        // returns a booking
        Booking booking1 = hotel1.createBooking( bedroom1, 8, guest1);
        assertEquals(1, hotel1.getTotalBookings());
//        booking1 = new Booking(bedroom1, 8, guest1);


    }

    @Test
    public void can_create_booking(){
        Booking booking1 = hotel1.createBooking( bedroom1, 8, guest1);
        assertEquals(true, hotel1.getBooking(booking1));
    }

    @Test
    public void can_get_total_bill_for_booking(){
        Booking booking1 = hotel1.createBooking( bedroom1, 8, guest1);
        assertEquals(160, hotel1.getBookingTotalCost(booking1));
    }

    @Test
    public void check_if_available_rooms_are_returned_true(){
        hotel1.addBedroom(bedroom1);
        hotel1.addBedroom(bedroom2);
        hotel1.addBedroom(bedroom3);
        bedroom2.setAvailability(false);
//        assertEquals(2, hotel1.getAvailableBedrooms());
        ArrayList<Bedroom> availableBedrooms = new ArrayList<Bedroom>();
        availableBedrooms.add(bedroom1);
        availableBedrooms.add(bedroom3);
        assertEquals(true, hotel1.getAvailableBedrooms().equals(availableBedrooms));
    }

    @Test
    public void check_number_of_available_rooms(){
        hotel1.addBedroom(bedroom1);
        hotel1.addBedroom(bedroom2);
        hotel1.addBedroom(bedroom3);
        bedroom2.setAvailability(false);
        assertEquals(2, hotel1.getAvailableBedroomsQuantity());
    }

}

