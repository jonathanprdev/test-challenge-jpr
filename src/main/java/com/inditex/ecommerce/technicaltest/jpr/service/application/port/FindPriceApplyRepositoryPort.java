package com.inditex.ecommerce.technicaltest.jpr.service.application.port;

import com.inditex.ecommerce.technicaltest.jpr.service.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface FindPriceApplyRepositoryPort {

    List<Price> getPricesToApply(LocalDateTime applyDate, Long productId, Long brandId);

}
