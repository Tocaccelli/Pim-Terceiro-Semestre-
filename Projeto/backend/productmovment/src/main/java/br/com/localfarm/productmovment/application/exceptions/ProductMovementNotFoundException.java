package br.com.localfarm.productmovment.application.exceptions;

public class ProductMovementNotFoundException extends RuntimeException {

    public ProductMovementNotFoundException(String message) {
        super(message);
    }

    public ProductMovementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}