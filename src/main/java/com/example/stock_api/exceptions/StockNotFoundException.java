package com.example.stock_api.exceptions;

public class StockNotFoundException extends RuntimeException{
    public StockNotFoundException(String message) {
        super(message);
    }
}
