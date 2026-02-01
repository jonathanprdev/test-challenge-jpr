package com.inditex.ecommerce.technicaltest.jpr.service.infrastructure.out.persistence.h2.repositories;

import com.inditex.ecommerce.technicaltest.jpr.service.infrastructure.out.persistence.h2.entities.PriceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PricesH2Repository extends CrudRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p WHERE :applyDate BETWEEN p.startDate AND p.endDate AND p.product.id = :productId AND p.brand.id = :brandId")
    List<PriceEntity> findPrices(LocalDateTime applyDate, Long productId, Long brandId);
}
