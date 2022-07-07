package com.vmandre.roomreservationservice;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/room-reservations")
public class RoomReservationWebService {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    // we're going to use Feign
//    private final RestTemplate restTemplate;
//    public RoomReservationWebService(RestTemplate restTemplate) {
//        super();
//        this.restTemplate = restTemplate;
//    }

    private final RoomClient roomClient;
    private final GuestClient guestClient;
    private final ReservationClient reservationClient;

    public RoomReservationWebService(RoomClient roomClient, GuestClient guestClient, ReservationClient reservationClient) {
        this.roomClient = roomClient;
        this.guestClient = guestClient;
        this.reservationClient = reservationClient;
    }

    @GetMapping
    public List<RoomReservation> getRoomReservations(@RequestParam(name = "date", required = false) String dateString) {
//        List<Room> rooms = this.getAllRooms();

        Date date = createDateFromDateString(dateString);

        List<Room> rooms = this.roomClient.getAllRooms();
        Map<Long, RoomReservation> roomReservations = new HashMap<>();

        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomId(room.getId());

            roomReservations.put(room.getId(), roomReservation);
        });

        List<Reservation> reservations = this.reservationClient.getAllReservations(new java.sql.Date(date.getTime()));

        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservations.get(reservation.getRoomId());
            roomReservation.setDate(date);

            Guest guest = this.guestClient.getGuest(reservation.getGuestId());
            roomReservation.setGuestId(guest.getId());
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
        });

        return new ArrayList<>(roomReservations.values());
    }

//    private List<Room> getAllRooms() {
//        ResponseEntity<List<Room>> roomResponse = this.restTemplate.exchange("http://ROOMSERVICES/rooms", HttpMethod.GET, null, new ParameterizedTypeReference<List<Room>>() {});
//        return roomResponse.getBody();
//    }

    private Date createDateFromDateString(String dateString) {
        Date date = null;

        if (dateString != null) {
            try {
                date =  DATE_FORMAT.parse(dateString);
            } catch (ParseException e) {
                System.err.println("ERROR ON PARSING DATE: " + e.getMessage());
                date = new Date();
            }
        } else {
            date = new Date();
        }

        return date;
    }
}
