package com.blibli.ojekonline.service;

import com.blibli.ojekonline.model.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> getPaymentList();

    Payment savePayment(Payment payment);

    Payment getPaymentById(int id);
}
