package com.vmandre.roomreservationservice;

import lombok.Data;

import java.sql.Date;

@Data
public class Reservation {
    private long id;
    private long roomId;
    private long guestId;
    private Date date;
}
