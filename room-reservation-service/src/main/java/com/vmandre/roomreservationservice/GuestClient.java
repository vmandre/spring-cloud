package com.vmandre.roomreservationservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("guestservices")
public interface GuestClient {

    @GetMapping("/guests/")
    List<Guest> getAllGuests();

    @GetMapping("/guests/{id}")
    Guest getGuest(@PathVariable long id);
}
