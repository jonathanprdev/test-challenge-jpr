package com.inditex.ecommerce.technicaltest.jpr.service.infrastructure.out.persistence.h2.mappers;

import com.inditex.ecommerce.technicaltest.jpr.service.domain.model.Price;
import com.inditex.ecommerce.technicaltest.jpr.service.infrastructure.out.persistence.h2.entities.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PricesMapper {

    Price entityToDomain(PriceEntity entity);

}
