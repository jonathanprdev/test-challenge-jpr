package com.inditex.ecommerce.technicaltest.jpr.service.application.mappers;

import com.inditex.ecommerce.technicaltest.jpr.service.domain.dtos.response.PriceResponse;
import com.inditex.ecommerce.technicaltest.jpr.service.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceResponseMapper {

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "applyTariff", source = "priceList.id")
    @Mapping(target = "applyStartDate", source = "startDate")
    @Mapping(target = "applyEndDate", source = "endDate")
    @Mapping(target = "finalPrice", source = "price")
    PriceResponse domainToResponse(Price domain);

}
