package com.blibli.ojekonline.service;

import com.blibli.ojekonline.model.Driver;

import java.util.List;

public interface DriverService {

    Driver getDriverById(int id);

    Driver saveDriver(Driver driver);

    String deleteDriverById(int id);

    List<Driver> getDriverList();

    String addBalance(Driver driver, int balance);
}
