package com.blibli.ojekonline.controller;

import com.blibli.ojekonline.model.Payment;
import com.blibli.ojekonline.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.blibli.ojekonline.controller.API_PATH.API_PAYMENT;
import static com.blibli.ojekonline.controller.API_PATH.API_PAYMENT_ALL;
import static com.blibli.ojekonline.controller.API_PATH.API_PAYMENT_ID;

@RestController
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(API_PAYMENT_ALL)
    public BaseResponse<List<Payment>> getAllPayment() {
        BaseResponse response = new BaseResponse();
        response.setData(paymentService.getPaymentList());
        response.setCode(HttpStatus.OK.toString());
        response.setStatus("OK");
        return response;
    }

    @GetMapping(API_PAYMENT_ID)
    public BaseResponse<Payment> getPayment(@PathVariable int id) {
        BaseResponse response = new BaseResponse();
        try {
            Payment payment = paymentService.getPaymentById(id);
            response.setData(payment);
            response.setCode(HttpStatus.OK.toString());
            response.setStatus("OK");
        } catch (RuntimeException e) {
            response.setData(null);
            response.setCode(HttpStatus.NOT_FOUND.toString());
            response.setStatus("Fail");
        }
        return response;
    }

    @PostMapping(API_PAYMENT)
    public BaseResponse<String> insertPayment(@Valid @RequestBody Payment payment) {
        Payment returned = paymentService.savePayment(payment);
        BaseResponse response = new BaseResponse();
        if (returned != null) {
            response.setData("Save success!");
            response.setCode(HttpStatus.OK.toString());
            response.setStatus("OK");
        } else {
            response.setData(null);
            response.setCode(HttpStatus.NOT_FOUND.toString());
            response.setStatus("Fail");
        }
        return response;
    }

    @PutMapping(API_PAYMENT)
    public BaseResponse<String> updatePayment(@Valid @RequestBody Payment payment) {
        Payment returned = paymentService.savePayment(payment);
        BaseResponse response = new BaseResponse();
        if (returned != null) {
            response.setData("Update success!");
            response.setCode(HttpStatus.OK.toString());
            response.setStatus("OK");
        } else {
            response.setData(null);
            response.setCode(HttpStatus.NOT_FOUND.toString());
            response.setStatus("Fail");
        }
        return response;
    }

//    @DeleteMapping(API_PAYMENT_ID)
//    public BaseResponse<String> deletePayment(@PathVariable int id){
//        String returned = paymentService;
//    }
}
