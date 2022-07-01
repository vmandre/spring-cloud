package com.vmandre.roomservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomWebServices {

   private RoomRepository roomRepository;

   public RoomWebServices(RoomRepository roomRepository) {
      this.roomRepository = roomRepository;
   }

   @GetMapping
   Iterable<Room> getAllRooms() {
      return roomRepository.findAll();
   }

   @GetMapping("/{id}")
   Room getRoom(@PathVariable("id") Long id) {
      return roomRepository.findById(id).get();
   }
}
