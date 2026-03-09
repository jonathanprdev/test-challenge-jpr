package com.inditex.ecommerce.technicaltest.jpr.service.application.services;

import com.inditex.ecommerce.technicaltest.jpr.service.infrastructure.in.web.mappers.PriceResponseMapper;
import com.inditex.ecommerce.technicaltest.jpr.service.application.port.FindPriceApplyRepositoryPort;
import com.inditex.ecommerce.technicaltest.jpr.service.application.usecases.FinderPriceService;
import com.inditex.ecommerce.technicaltest.jpr.service.infrastructure.in.web.dtos.request.PriceRequest;
import com.inditex.ecommerce.technicaltest.jpr.service.infrastructure.in.web.dtos.response.PriceResponse;
import com.inditex.ecommerce.technicaltest.jpr.service.domain.exception.PriceNotFoundException;
import com.inditex.ecommerce.technicaltest.jpr.service.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinderPriceServiceImpl implements FinderPriceService {

    private final FindPriceApplyRepositoryPort findPriceApplyRepositoryPort;

    private final PriceResponseMapper priceResponseMapper;

    @Override
    public Optional<PriceResponse> findPriceToApply(PriceRequest request) {
        var pricesFoundList = findPriceApplyRepositoryPort.getPricesToApply(request.getApplicationDate(), request.getProductId(), request.getBrandId());

        if(pricesFoundList == null || pricesFoundList.isEmpty())
            throw new PriceNotFoundException(request.getBrandId(), request.getProductId());

        return pricesFoundList.stream()
                .max(Comparator.comparing(Price::getPriority))
                .map(priceResponseMapper::domainToResponse);

    }
}
