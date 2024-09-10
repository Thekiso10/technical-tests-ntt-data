package com.ntt_data.inditex.backend.integration.infrastracture.adapter.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class PricesControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final String URL_GET_AVAILABLE_PRICE = "/api/prices/available";

    @Test
    void shouldReturnPriceForGivenDateWithinFirstPriceRange() throws Exception {
        mockMvc.perform(get(URL_GET_AVAILABLE_PRICE)
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("date", "2020-06-14T10:00:00Z"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void shouldReturnPriceForGivenDateWithinSecondPriceRange() throws Exception {
        mockMvc.perform(get(URL_GET_AVAILABLE_PRICE)
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("date", "2020-06-14T16:00:00Z"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    void shouldReturnPriceForGivenDateWithinFirstPriceRangeAfterEndOfSecondRange() throws Exception {
        mockMvc.perform(get(URL_GET_AVAILABLE_PRICE)
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("date", "2020-06-14T21:00:00Z"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void shouldReturnPriceForGivenDateWithinThirdPriceRange() throws Exception {
        mockMvc.perform(get(URL_GET_AVAILABLE_PRICE)
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("date", "2020-06-15T10:00:00Z"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    void shouldReturnPriceForGivenDateWithinFourthPriceRange() throws Exception {
        mockMvc.perform(get(URL_GET_AVAILABLE_PRICE)
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("date", "2020-06-16T21:00:00Z"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(38.95));
    }
}
