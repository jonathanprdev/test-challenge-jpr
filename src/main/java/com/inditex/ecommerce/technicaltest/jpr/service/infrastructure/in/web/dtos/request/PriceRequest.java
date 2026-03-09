package com.inditex.ecommerce.technicaltest.jpr.service.infrastructure.in.web.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PriceRequest {
    @NotNull(message = "applicationDate is required")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime applicationDate;

    @NotNull(message = "productId is required")
    @Positive(message = "productId must be positive")
    private Long productId;

    @NotNull(message = "brandId is required")
    @Positive(message = "brandId must be positive")
    private Long brandId;
}
