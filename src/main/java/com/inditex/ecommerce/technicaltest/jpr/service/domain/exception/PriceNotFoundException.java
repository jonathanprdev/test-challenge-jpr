package com.inditex.ecommerce.technicaltest.jpr.service.domain.exception;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(Long brandId, Long productId) {
        super(String.format(
                "No price found for brandId=%d and productId=%d",
                brandId, productId
        ));
    }

    public PriceNotFoundException(String message) {
        super(message);
    }
}
