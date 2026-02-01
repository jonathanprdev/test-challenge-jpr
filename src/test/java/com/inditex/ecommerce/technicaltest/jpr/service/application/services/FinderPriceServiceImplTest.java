package com.inditex.ecommerce.technicaltest.jpr.service.application.services;

import com.inditex.ecommerce.technicaltest.jpr.service.application.mappers.PriceResponseMapper;
import com.inditex.ecommerce.technicaltest.jpr.service.application.port.FindPriceApplyRepositoryPort;
import com.inditex.ecommerce.technicaltest.jpr.service.application.usecases.FinderPriceService;
import com.inditex.ecommerce.technicaltest.jpr.service.domain.dtos.request.PriceRequest;
import com.inditex.ecommerce.technicaltest.jpr.service.domain.dtos.response.PriceResponse;
import com.inditex.ecommerce.technicaltest.jpr.service.domain.exception.PriceNotFoundException;
import com.inditex.ecommerce.technicaltest.jpr.service.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FinderPriceServiceImplTest {

    @Mock
    private FindPriceApplyRepositoryPort findPriceApplyRepositoryPort;

    @Mock
    private PriceResponseMapper priceResponseMapper;

    @InjectMocks
    private FinderPriceServiceImpl finderPriceService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldReturnExceptionWhenFindPriceToApplyNotFound() {
        //Given
        PriceRequest priceRequest = buildPriceRequest();
        //When
        when(findPriceApplyRepositoryPort.getPricesToApply(priceRequest.getApplicationDate(),priceRequest.getProductId(), priceRequest.getBrandId())).thenReturn(new ArrayList<>());
        //Then
        assertThatThrownBy(() -> finderPriceService.findPriceToApply(priceRequest)).isExactlyInstanceOf(PriceNotFoundException.class);
    }

    @Test
    void shouldReturnExceptionWhenFindPriceToApplyIsNull() {
        //Given
        PriceRequest priceRequest = buildPriceRequest();
        //When
        when(findPriceApplyRepositoryPort.getPricesToApply(priceRequest.getApplicationDate(),priceRequest.getProductId(), priceRequest.getBrandId())).thenReturn(null);
        //Then
        assertThatThrownBy(() -> finderPriceService.findPriceToApply(priceRequest)).isExactlyInstanceOf(PriceNotFoundException.class);
    }

    @Test
    void shouldReturnPriceWhenFindPriceToApply() {
        //Given
        PriceRequest priceRequest = buildPriceRequest();
        PriceResponse priceResponse = buildPriceResponse();

        //When
        when(findPriceApplyRepositoryPort.getPricesToApply(priceRequest.getApplicationDate(),priceRequest.getProductId(), priceRequest.getBrandId())).thenReturn(buildPriceList());
        when(priceResponseMapper.domainToResponse(any(Price.class))).thenReturn(priceResponse);

        Optional<PriceResponse> priceFounded = finderPriceService.findPriceToApply(priceRequest);

        //Then
        assertThat(priceFounded).isPresent();
        verify(findPriceApplyRepositoryPort).getPricesToApply(priceRequest.getApplicationDate(),priceRequest.getProductId(),priceRequest.getBrandId());
        verify(priceResponseMapper).domainToResponse(any(Price.class));
    }

    private PriceRequest buildPriceRequest() {
        PriceRequest request = new PriceRequest();
        request.setApplicationDate(LocalDateTime.of(2020, 6, 14, 10, 0));
        request.setProductId(35455L);
        request.setBrandId(1L);

        return request;
    }

    private PriceResponse buildPriceResponse() {
        PriceResponse response = new PriceResponse();
        response.setProductId(35455L);
        response.setBrandId(1L);
        response.setApplyTariff(2L);
        response.setApplyStartDate(LocalDateTime.now());
        response.setApplyEndDate(LocalDateTime.now().plusDays(10));

        return response;
    }

    private List<Price> buildPriceList() {
        List<Price> priceList = new ArrayList<>();
        Price lowPriority = new Price();
        lowPriority.setPriority(0);
        priceList.add(lowPriority);
        Price highPriority = new Price();
        highPriority.setPriority(1);
        priceList.add(highPriority);
        return priceList;
    }
}