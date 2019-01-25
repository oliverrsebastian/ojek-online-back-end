package com.blibli.ojekonline.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class BookingRequest {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int id;

    private int paymentId;
    private int memberId;
    private int driverId;
    private int balanceTaken;
    private String bookedDate;
    private String scheduledDate;
}
