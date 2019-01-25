package com.blibli.ojekonline.service;

import com.blibli.ojekonline.model.Payment;
import com.blibli.ojekonline.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> getPaymentList() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(int id) {
        return paymentRepository.findOne(id);
    }
}
