package com.vmandre.roomreservationservice;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/room-reservations")
public class RoomReservationWebService {

    // we're going to use Feign
//    private final RestTemplate restTemplate;
//    public RoomReservationWebService(RestTemplate restTemplate) {
//        super();
//        this.restTemplate = restTemplate;
//    }

    private final RoomClient roomClient;

    public RoomReservationWebService(RoomClient roomClient) {
        this.roomClient = roomClient;
    }

    @GetMapping
    public List<RoomReservation> getRoomReservations() {
//        List<Room> rooms = this.getAllRooms();
        List<Room> rooms = this.roomClient.getAllRooms();
        List<RoomReservation> roomReservations = new ArrayList<>();

        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomId(room.getId());
            roomReservations.add(roomReservation);
        });

        return roomReservations;
    }

//    private List<Room> getAllRooms() {
//        ResponseEntity<List<Room>> roomResponse = this.restTemplate.exchange("http://ROOMSERVICES/rooms", HttpMethod.GET, null, new ParameterizedTypeReference<List<Room>>() {});
//        return roomResponse.getBody();
//    }
}
