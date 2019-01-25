package com.blibli.ojekonline.service;

import com.blibli.ojekonline.model.Booking;

import java.util.List;

public interface BookingService {

    Booking getBookingById(int id);

    List<Booking> getBookingList();

    Booking saveBooking(Booking booking);

    Booking cancelBooking(Booking booking);

    Booking finishedBooking(Booking booking);

    List<Booking> findBookingByMemberId(int memberId);

    List<Booking> findBookingByDriverId(int driverId);
}
