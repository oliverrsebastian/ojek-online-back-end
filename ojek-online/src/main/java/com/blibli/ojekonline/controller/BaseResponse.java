package com.blibli.ojekonline.controller;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private String code;
    private T data;
    private String status;
}
