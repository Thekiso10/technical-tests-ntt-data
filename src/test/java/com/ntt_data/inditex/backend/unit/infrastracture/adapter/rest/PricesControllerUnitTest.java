package com.ntt_data.inditex.backend.unit.infrastracture.adapter.rest;

import com.ntt_data.inditex.backend.application.usercase.GetAvailablePriceUseCase;
import com.ntt_data.inditex.backend.domain.model.Prices;
import com.ntt_data.inditex.backend.infrastracture.adapter.rest.PricesController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricesControllerUnitTest {

    @Mock
    private GetAvailablePriceUseCase getAvailablePriceUseCase;

    @InjectMocks
    private PricesController pricesController;

    @Test
    void testGetAvailablePriceByProductAndBrandAndDate() throws Exception {
        Long productId = 1L;
        Long brandId = 1L;
        Instant date = Instant.now();
        Prices prices = generatePrice();

        when(getAvailablePriceUseCase.execute(productId, brandId, date)).thenReturn(prices);

        ResponseEntity<Prices> response = pricesController.getAvailablePriceByProductAndBrandAndDate(productId, brandId, date);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(prices, response.getBody());
    }

    private Prices generatePrice() {
        Prices prices = new Prices();
        prices.setBrandId(1L);
        prices.setProductId(1L);
        prices.setPrice(01.00);

        return prices;
    }
}
