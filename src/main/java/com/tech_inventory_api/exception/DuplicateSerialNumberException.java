package com.tech_inventory_api.exception;

public class DuplicateSerialNumberException extends RuntimeException {

    public DuplicateSerialNumberException(String message) {
        super(message);
    }
}