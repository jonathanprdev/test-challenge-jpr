package com.inditex.ecommerce.technicaltest.jpr.service.infrastructure.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.inditex.ecommerce.technicaltest.jpr.service.infrastructure.in.web.dtos.request.PriceRequest;
import com.inditex.ecommerce.technicaltest.jpr.service.infrastructure.in.web.dtos.response.PriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    public static final String URL_PRICE_APPLY = "/price/apply";
    private final String URL_API_CONTEXT = "/prices";

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectmapper;

    @BeforeEach
    void setUp() {
        objectmapper = JsonMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .addModule(new JavaTimeModule())
                .build();
    }

    @Test
    void shouldReturn400WhenApplyDateIsNull() throws Exception {
        //Given
        LocalDateTime applyDate = null;
        Long idProduct = 1L;
        Long idBrand = 1L;
        PriceRequest request = new PriceRequest();
        request.setApplicationDate(applyDate);
        request.setProductId(idProduct);
        request.setBrandId(idBrand);
        String requestJson = objectmapper.writeValueAsString(request);
        log.info(requestJson);
        //When
        var response = mockMvc
                .perform(post(URL_API_CONTEXT + URL_PRICE_APPLY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    void shouldReturn400WhenProductIsNull() throws Exception {
        //Given
        LocalDateTime applyDate = LocalDateTime.of(2000, 1, 1, 0, 0);;
        Long idProduct = null;
        Long idBrand = 1L;
        PriceRequest request = new PriceRequest();
        request.setApplicationDate(applyDate);
        request.setProductId(idProduct);
        request.setBrandId(idBrand);
        String requestJson = objectmapper.writeValueAsString(request);
        log.info(requestJson);
        //When
        var response = mockMvc
                .perform(post(URL_API_CONTEXT + URL_PRICE_APPLY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    void shouldReturn400WhenBrandIsNull() throws Exception {
        //Given
        LocalDateTime applyDate = LocalDateTime.of(2000, 1, 1, 0, 0);;
        Long idProduct = 1L;

        PriceRequest request = new PriceRequest();
        request.setApplicationDate(applyDate);
        request.setProductId(idProduct);

        String requestJson = objectmapper.writeValueAsString(request);
        log.info(requestJson);
        //When
        var response = mockMvc
                .perform(post(URL_API_CONTEXT + URL_PRICE_APPLY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    void shouldReturn204whenNotFound() throws Exception {
        //Given
        LocalDateTime applyDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long idProduct = 1L;
        Long idBrand = 1L;
        PriceRequest request = new PriceRequest();
        request.setApplicationDate(applyDate);
        request.setProductId(idProduct);
        request.setBrandId(idBrand);
        String requestJson = objectmapper.writeValueAsString(request);
        log.info(requestJson);
        //When
        var response = mockMvc
                .perform(post(URL_API_CONTEXT + URL_PRICE_APPLY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()));

    }

    @Test
    void findApplyPrice_Test1() throws Exception {
        //Given
        LocalDateTime applyDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long idProduct = 35455L;
        Long idBrand = 1L;
        PriceRequest request = new PriceRequest();
        request.setApplicationDate(applyDate);
        request.setProductId(idProduct);
        request.setBrandId(idBrand);
        String requestJson = objectmapper.writeValueAsString(request);
        log.info(requestJson);
        //When
        var response = mockMvc
                .perform(post(URL_API_CONTEXT + URL_PRICE_APPLY)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        log.info(response);
        PriceResponse responseObj = objectmapper.readValue(response, PriceResponse.class);
        assertEquals(responseObj.getApplyTariff(), 1);
    }

    @Test
    void findApplyPrice_Test2() throws Exception {
        //Given
        LocalDateTime applyDate = LocalDateTime.of(2020, 6, 14, 16, 0);
        Long idProduct = 35455L;
        Long idBrand = 1L;
        PriceRequest request = new PriceRequest();
        request.setApplicationDate(applyDate);
        request.setProductId(idProduct);
        request.setBrandId(idBrand);
        String requestJson = objectmapper.writeValueAsString(request);
        log.info(requestJson);

        //When
        var response = mockMvc
                .perform(post(URL_API_CONTEXT + URL_PRICE_APPLY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        log.info(response);
        PriceResponse responseObj = objectmapper.readValue(response, PriceResponse.class);
        assertEquals(responseObj.getApplyTariff(), 2);
    }

    @Test
    void findApplyPrice_Test3() throws Exception {
        //Given
        LocalDateTime applyDate = LocalDateTime.of(2020, 6, 14, 21, 0);
        Long idProduct = 35455L;
        Long idBrand = 1L;
        PriceRequest request = new PriceRequest();
        request.setApplicationDate(applyDate);
        request.setProductId(idProduct);
        request.setBrandId(idBrand);
        String requestJson = objectmapper.writeValueAsString(request);
        log.info(requestJson);

        //When
        var response = mockMvc
                .perform(post(URL_API_CONTEXT + URL_PRICE_APPLY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        log.info(response);
        PriceResponse responseObj = objectmapper.readValue(response, PriceResponse.class);
        assertEquals(responseObj.getApplyTariff(), 1);
    }

    @Test
    void findApplyPrice_Test4() throws Exception {
        //Given
        LocalDateTime applyDate = LocalDateTime.of(2020, 6, 15, 10, 0);
        Long idProduct = 35455L;
        Long idBrand = 1L;
        PriceRequest request = new PriceRequest();
        request.setApplicationDate(applyDate);
        request.setProductId(idProduct);
        request.setBrandId(idBrand);
        String requestJson = objectmapper.writeValueAsString(request);
        log.info(requestJson);

        //When
        var response = mockMvc
                .perform(post(URL_API_CONTEXT + URL_PRICE_APPLY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        log.info(response);
        PriceResponse responseObj = objectmapper.readValue(response, PriceResponse.class);
        assertEquals(responseObj.getApplyTariff(), 3);
    }

    @Test
    void findApplyPrice_Test5() throws Exception {
        //Given
        LocalDateTime applyDate = LocalDateTime.of(2020, 6, 16, 21, 0);
        Long idProduct = 35455L;
        Long idBrand = 1L;
        PriceRequest request = new PriceRequest();
        request.setApplicationDate(applyDate);
        request.setProductId(idProduct);
        request.setBrandId(idBrand);
        String requestJson = objectmapper.writeValueAsString(request);
        log.info(requestJson);

        //When
        var response = mockMvc
                .perform(post(URL_API_CONTEXT + URL_PRICE_APPLY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        log.info(response);
        PriceResponse responseObj = objectmapper.readValue(response, PriceResponse.class);
        assertEquals(responseObj.getApplyTariff(), 4);
    }

}