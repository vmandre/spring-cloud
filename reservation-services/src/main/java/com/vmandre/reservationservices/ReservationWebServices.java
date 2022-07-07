package com.vmandre.reservationservices;

import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/reservations")
public class ReservationWebServices {

   private ReservationRepository reservationRepository;

   public ReservationWebServices(ReservationRepository reservationRepository) {
      this.reservationRepository = reservationRepository;
   }

   @GetMapping
   Iterable<Reservation> getAllReservations(@RequestParam(name = "date", required = false) Date date) {
      if (date != null) {
         return reservationRepository.findAllByDate(date);
      }
      return reservationRepository.findAll();
   }

   @GetMapping("/{id}")
   Reservation getReservation(@PathVariable("id") long id) {
      return reservationRepository.findById(id).get();
   }

}
