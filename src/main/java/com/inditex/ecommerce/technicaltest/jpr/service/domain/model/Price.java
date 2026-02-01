package com.inditex.ecommerce.technicaltest.jpr.service.domain.model;

import com.inditex.ecommerce.technicaltest.jpr.service.domain.enumerations.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Price {
    private Long id;
    private Brand brand;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private PriceList priceList;
    private Product product;
    private Integer priority;
    private BigDecimal price;
    private Currency currency;
}
