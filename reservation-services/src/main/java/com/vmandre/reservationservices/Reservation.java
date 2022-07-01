package com.vmandre.reservationservices;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "RESERVATION")
public class Reservation {

   @Id
   @Column(name = "RESERVATION_ID", nullable = false)
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

   @Column(name = "ROOM_ID", nullable = false)
   private long roomId;

   @Column(name = "GUEST_ID", nullable = false)
   private long guestId;

   @Column(name = "RES_DATE", nullable = false)
   private Date date;
}
