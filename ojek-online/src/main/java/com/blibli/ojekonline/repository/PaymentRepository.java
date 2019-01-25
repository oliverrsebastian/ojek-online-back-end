package com.blibli.ojekonline.repository;

import com.blibli.ojekonline.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
