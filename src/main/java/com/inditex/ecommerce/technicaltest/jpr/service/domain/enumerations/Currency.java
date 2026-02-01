package com.inditex.ecommerce.technicaltest.jpr.service.domain.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum Currency {
    EUR("EUR"), USD("USD");

    private final String curr;
    Currency(String curr) {
        this.curr = curr;
    }
}
