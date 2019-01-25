package com.blibli.ojekonline.service;

import com.blibli.ojekonline.exceptions.BookingAlreadyCanceledException;
import com.blibli.ojekonline.exceptions.BookingAlreadyFinisedException;
import com.blibli.ojekonline.model.Booking;
import com.blibli.ojekonline.model.Driver;
import com.blibli.ojekonline.model.Member;
import com.blibli.ojekonline.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private PaymentService paymentService;

    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Override
    public Booking getBookingById(int id) {
        logger.warn("get booking by id : " + id);
        try {
            return bookingRepository.findOne(id);
        } catch (RuntimeException e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<Booking> getBookingList() {
        return bookingRepository.findAll();
    }

    public List<Booking> getUserBookingList(int member_id){
        return bookingRepository.findAllByMember_Id(member_id);
    }

    @Override
    public Booking saveBooking(Booking booking) {
        try {
            paymentService.getPaymentById(booking.getPaymentId());
            Member member = memberService.getMemberById(booking.getMember().getId());
            Driver driver = driverService.getDriverById(booking.getDriver().getId());
            booking.setMember(member);
            booking.setDriver(driver);
        } catch (RuntimeException e) {
            throw e;
        }
        return bookingRepository.save(booking);
    }

    @Override
    public Booking cancelBooking(Booking booking) {
        booking.setCanceled(true);
        memberService.recoverBalance(booking.getMember(), booking.getBalanceTaken());
        return bookingRepository.save(booking);
    }

    @Override
    public Booking finishedBooking(Booking booking) {
        if (booking.isCanceled())
            throw new BookingAlreadyCanceledException();
        else if (booking.isFinished())
            throw new BookingAlreadyFinisedException();
        else {
            booking.setFinished(true);
            memberService.reduceBalance(booking.getMember(), booking.getBalanceTaken());
            driverService.addBalance(booking.getDriver(), booking.getBalanceTaken());
            return bookingRepository.save(booking);
        }
    }

    @Override
    public List<Booking> findBookingByMemberId(int memberId) {
        return bookingRepository.findAllByMember_Id(memberId);
    }

    @Override
    public List<Booking> findBookingByDriverId(int driverId) {
        return bookingRepository.findAllByDriver_Id(driverId);
    }
}
