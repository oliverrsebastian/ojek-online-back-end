package com.blibli.ojekonline.controller;

import com.blibli.ojekonline.model.Booking;
import com.blibli.ojekonline.model.Driver;
import com.blibli.ojekonline.model.Member;
import com.blibli.ojekonline.service.BookingService;
import com.blibli.ojekonline.service.DriverService;
import com.blibli.ojekonline.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.blibli.ojekonline.controller.API_PATH.*;

@RestController
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private MemberService memberService;

    @GetMapping(API_BOOKING_ALL)
    public BaseResponse<List<Booking>> getAllBooking() {
        BaseResponse response = new BaseResponse();
        response.setData(bookingService.getBookingList());
        response.setCode(HttpStatus.OK.toString());
        response.setStatus("OK");
        return response;
    }

    @GetMapping(API_BOOKING_MEMBER_ID)
    public BaseResponse<List<Booking>> getMemberBooking(@PathVariable int id){
        BaseResponse response = new BaseResponse();
        response.setData(bookingService.findBookingByMemberId(id));
        response.setCode(HttpStatus.OK.toString());
        response.setStatus("OK");
        return response;
    }

    @GetMapping(API_BOOKING_DRIVER_ID)
    public BaseResponse<List<Booking>> getDriverBooking(@PathVariable int id){
        BaseResponse response = new BaseResponse();
        response.setData(bookingService.findBookingByDriverId(id));
        response.setCode(HttpStatus.OK.toString());
        response.setStatus("OK");
        return response;
    }

    @GetMapping(API_BOOKING_ID)
    public BaseResponse<Booking> getBooking(@PathVariable int id) {
        BaseResponse response = new BaseResponse();
        try {
            response.setData(bookingService.getBookingById(id));
            response.setCode(HttpStatus.OK.toString());
            response.setStatus("OK");
        } catch (RuntimeException e) {
            response.setData(null);
            response.setCode(HttpStatus.NOT_FOUND.toString());
            response.setStatus("Fail");
        }
        return response;
    }

    @PostMapping(API_BOOKING)
    public BaseResponse<String> saveBooking(@RequestBody BookingRequest bookingRequest){
        BaseResponse response = new BaseResponse();
        Booking booking = new Booking();
        booking.setBalanceTaken(bookingRequest.getBalanceTaken());
        booking.setCanceled(false);
        booking.setFinished(false);
        booking.setBookedDate(bookingRequest.getBookedDate());
        booking.setScheduledDate(bookingRequest.getScheduledDate());
        Driver driver = driverService.getDriverById(bookingRequest.getDriverId());
        booking.setDriver(driver);
        Member member = memberService.getMemberById(bookingRequest.getMemberId());
        booking.setMember(member);
        try{
            bookingService.saveBooking(booking);
            response.setData("Save success!");
            response.setCode(HttpStatus.OK.toString());
            response.setStatus("OK");
        }catch(RuntimeException e){
            response.setData(null);
            response.setCode(HttpStatus.NOT_FOUND.toString());
            response.setStatus("Fail");
        }
        return response;
    }

    @GetMapping(API_BOOKING_ID_STATUS_FINISHED)
    public BaseResponse<String> changeBookingToFinished(@PathVariable int id){
        BaseResponse response = new BaseResponse();
        try{
            Booking booking = bookingService.getBookingById(id);
            bookingService.finishedBooking(booking);
            response.setData("Booking : " + id + " is finished!");
            response.setCode(HttpStatus.OK.toString());
            response.setStatus("OK");
        }catch(RuntimeException e){
            response.setData(null);
            response.setCode(HttpStatus.NOT_FOUND.toString());
            response.setStatus("Fail");
        }
        return response;
    }

    @DeleteMapping(API_BOOKING_ID_STATUS_CANCELED)
    public BaseResponse<String> changeBookingToCanceled(@PathVariable int id){
        BaseResponse response = new BaseResponse();
        try{
            Booking booking = bookingService.getBookingById(id);
            bookingService.cancelBooking(booking);
            response.setData("Booking : " + id + " is canceled!");
            response.setCode(HttpStatus.OK.toString());
            response.setStatus("OK");
        }catch(RuntimeException e){
            response.setData(null);
            response.setCode(HttpStatus.NOT_FOUND.toString());
            response.setStatus("Fail");
        }
        return response;
    }

}
