package com.inditex.ecommerce.technicaltest.jpr.service.application.usecases;

import com.inditex.ecommerce.technicaltest.jpr.service.domain.dtos.request.PriceRequest;
import com.inditex.ecommerce.technicaltest.jpr.service.domain.dtos.response.PriceResponse;

import java.util.Optional;

public interface FinderPriceService {

    Optional<PriceResponse> findPriceToApply(PriceRequest request);
}
