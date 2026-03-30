package com.tech_inventory_api.exception;

public class InvalidDeviceTypeException extends RuntimeException {

    public InvalidDeviceTypeException(String message) {
        super(message);
    }
}