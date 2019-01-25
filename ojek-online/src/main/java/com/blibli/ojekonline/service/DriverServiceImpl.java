package com.blibli.ojekonline.service;

import com.blibli.ojekonline.model.Driver;
import com.blibli.ojekonline.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Driver getDriverById(int id) {
        return driverRepository.findOne(id);
    }

    @Override
    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public String deleteDriverById(int id) {
        driverRepository.delete(id);
        return "Delete success!";
    }

    @Override
    public List<Driver> getDriverList() {
        return driverRepository.findAll();
    }

    @Override
    public String addBalance(Driver driver, int balance) {
        driver.setBalance(driver.getBalance() + balance);
        driverRepository.save(driver);
        return "Add balance success!";
    }
}
