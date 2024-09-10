package com.ntt_data.inditex.backend.integration.application.usercase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.ntt_data.inditex.backend.application.usercase.GetAvailablePriceUseCase;
import com.ntt_data.inditex.backend.domain.model.Prices;
import com.ntt_data.inditex.backend.domain.repository.PricesRepository;
import com.ntt_data.inditex.backend.infrastracture.entity.PricesEntity;
import com.ntt_data.inditex.backend.infrastracture.entity.ProductEntity;
import com.ntt_data.inditex.backend.infrastracture.exceptions.BackendException;
import com.ntt_data.inditex.backend.infrastracture.mapper.PricesMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;

@SpringBootTest
public class GetAvailablePriceUseCaseIntegrationTest {

    @Autowired
    private GetAvailablePriceUseCase getAvailablePriceUseCase;

    @Autowired
    private PricesRepository pricesRepository;

    @Autowired
    private PricesMapper pricesMapper;

    @Test
    void shouldReturnPriceWhenFound() {
        // Arrange
        Long productId = 35455L;
        Long brandId = 1L;
        Instant date = Instant.parse("2020-06-14T10:00:00Z");

        // Act
        Prices result = getAvailablePriceUseCase.execute(productId, brandId, date);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getPrice()).isEqualTo(35.5);
    }

    @Test
    void shouldThrowExceptionWhenPriceNotFound() {
        // Arrange
        Long productId = 35455L;
        Long brandId = 1L;
        Instant date = Instant.parse("2020-01-14T10:00:00Z");

        // Act & Assert
        assertThatThrownBy(() -> getAvailablePriceUseCase.execute(productId, brandId, date))
                .isInstanceOf(BackendException.class)
                .hasFieldOrPropertyWithValue("status", HttpStatus.BAD_REQUEST);
    }
}

