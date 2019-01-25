package com.blibli.ojekonline.controller;

import com.blibli.ojekonline.model.Driver;
import com.blibli.ojekonline.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.blibli.ojekonline.controller.API_PATH.API_DRIVER;
import static com.blibli.ojekonline.controller.API_PATH.API_DRIVER_ALL;
import static com.blibli.ojekonline.controller.API_PATH.API_DRIVER_ID;

@RestController
@CrossOrigin
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping(API_DRIVER_ALL)
    public BaseResponse<List<Driver>> getAllDriver() {
        BaseResponse response = new BaseResponse();
        response.setData(driverService.getDriverList());
        response.setCode(HttpStatus.OK.toString());
        response.setStatus("OK");
        return response;
    }

    @GetMapping(API_DRIVER_ID)
    public BaseResponse<Driver> getDriver(@PathVariable int id) {
        BaseResponse response = new BaseResponse();
        try {
            response.setData(driverService.getDriverById(id));
            response.setCode(HttpStatus.OK.toString());
            response.setStatus("OK");
        } catch (RuntimeException e) {
            response.setData(null);
            response.setCode(HttpStatus.NOT_FOUND.toString());
            response.setStatus("Fail");
        }
        return response;
    }

    @PostMapping(API_DRIVER)
    public BaseResponse<String> insertDriver(@RequestBody Driver driver) {
        Driver returned = driverService.saveDriver(driver);
        BaseResponse response = new BaseResponse();
        if (returned != null) {
            response.setData("Save success");
            response.setCode(HttpStatus.OK.toString());
            response.setStatus("OK");
        } else {
            response.setData(null);
            response.setCode(HttpStatus.NOT_FOUND.toString());
            response.setStatus("Fail");
        }
        return response;
    }

    @PutMapping(value = API_DRIVER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateDriver(@RequestBody Driver driver) {
        driverService.saveDriver(driver);
        BaseResponse response = new BaseResponse();
        if (response != null) {
            response.setData("Update success!");
            response.setCode(HttpStatus.OK.toString());
            response.setStatus("OK");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.setData(null);
            response.setCode(HttpStatus.NOT_FOUND.toString());
            response.setStatus("Fail");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping(API_DRIVER_ID)
    public BaseResponse<String> deleteDriver(@PathVariable int id) {
        String returnMessage = driverService.deleteDriverById(id);
        BaseResponse response = new BaseResponse();
        response.setData(returnMessage);
        response.setCode(HttpStatus.OK.toString());
        response.setStatus("OK");
        return response;
    }
}
