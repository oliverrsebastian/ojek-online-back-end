package com.blibli.ojekonline.controller;

public class API_PATH {

    public static final String API_MEMBER_ALL = "/api/members";
    public static final String API_MEMBER_ID = "/api/members/{id}";
    public static final String API_MEMBER = "api/members/_save";

    public static final String API_DRIVER_ALL = "/api/drivers";
    public static final String API_DRIVER_ID = "/api/drivers/{id}";
    public static final String API_DRIVER = "/api/drivers/_save";

    public static final String API_PAYMENT_ALL = "/api/payments";
    public static final String API_PAYMENT_ID = "/api/payments/{id}";
    public static final String API_PAYMENT = "/api/payments/_save";

    public static final String API_BOOKING_ALL = "/api/bookings";
    public static final String API_BOOKING_ID = "/api/bookings/{id}";
    public static final String API_BOOKING_MEMBER_ID = "/api/bookings/all/member/{id}";
    public static final String API_BOOKING_DRIVER_ID = "/api/bookings/all/driver/{id}";
    public static final String API_BOOKING = "api/bookings/_schedule";
    public static final String API_BOOKING_ID_STATUS_FINISHED = "/api/bookings/{id}/_finished";
    public static final String API_BOOKING_ID_STATUS_CANCELED = "/api/bookings/{id}/_canceled";
}
