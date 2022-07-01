package com.vmandre.reservationservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationWebServices {

   private ReservationRepository reservationRepository;

   public ReservationWebServices(ReservationRepository reservationRepository) {
      this.reservationRepository = reservationRepository;
   }

   @GetMapping
   Iterable<Reservation> getAllReservations() {
      return reservationRepository.findAll();
   }

   @GetMapping("/{id}")
   Reservation getReservation(@PathVariable("id") long id) {
      return reservationRepository.findById(id).get();
   }
}
