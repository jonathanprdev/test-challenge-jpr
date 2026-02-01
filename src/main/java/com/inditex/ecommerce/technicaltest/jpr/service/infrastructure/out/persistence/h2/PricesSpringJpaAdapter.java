package com.inditex.ecommerce.technicaltest.jpr.service.infrastructure.out.persistence.h2;

import com.inditex.ecommerce.technicaltest.jpr.service.application.port.FindPriceApplyRepositoryPort;
import com.inditex.ecommerce.technicaltest.jpr.service.domain.model.Price;
import com.inditex.ecommerce.technicaltest.jpr.service.infrastructure.out.persistence.h2.mappers.PricesMapper;
import com.inditex.ecommerce.technicaltest.jpr.service.infrastructure.out.persistence.h2.repositories.PricesH2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PricesSpringJpaAdapter implements FindPriceApplyRepositoryPort {

    private final PricesH2Repository pricesH2Repository;
    private final PricesMapper pricesMapper;

    @Override
    public List<Price> getPricesToApply(LocalDateTime applyDate, Long productId, Long brandId) {
        var pricesEntityFoundList = pricesH2Repository.findPrices(applyDate, productId, brandId);
        if(pricesEntityFoundList == null || pricesEntityFoundList.isEmpty())
            return new ArrayList<>();

        return pricesEntityFoundList.stream().map(pricesMapper::entityToDomain).collect(Collectors.toList());
    }
}
