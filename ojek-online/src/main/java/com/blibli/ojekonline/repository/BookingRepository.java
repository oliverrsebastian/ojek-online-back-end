package com.blibli.ojekonline.repository;

import com.blibli.ojekonline.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findAllByMember_Id(int memberId);

    List<Booking> findAllByDriver_Id(int driverId);
}
