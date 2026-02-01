package com.inditex.ecommerce.technicaltest.jpr.service.domain.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PriceResponse {
    private Long productId;
    private Long brandId;
    private Long applyTariff;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime applyStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime applyEndDate;
    @JsonFormat(pattern = "#####.##")
    private BigDecimal finalPrice;
}
