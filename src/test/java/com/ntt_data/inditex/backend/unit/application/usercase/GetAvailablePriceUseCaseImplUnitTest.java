package com.ntt_data.inditex.backend.unit.application.usercase;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

import com.ntt_data.inditex.backend.application.usercase.adapter.GetAvailablePriceUseCaseImpl;
import com.ntt_data.inditex.backend.domain.model.Prices;
import com.ntt_data.inditex.backend.domain.repository.PricesRepository;
import com.ntt_data.inditex.backend.infrastracture.entity.PricesEntity;
import com.ntt_data.inditex.backend.infrastracture.exceptions.BackendException;
import com.ntt_data.inditex.backend.infrastracture.mapper.PricesMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GetAvailablePriceUseCaseImplUnitTest {

    @Mock
    private PricesRepository pricesRepository;

    @Mock
    private PricesMapper pricesMapper;

    @InjectMocks
    private GetAvailablePriceUseCaseImpl getAvailablePriceUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnPriceWhenFound() {
        // Arrange
        Long productId = 35455L;
        Long brandId = 1L;
        Instant date = Instant.parse("2020-06-14T10:00:00Z");

        PricesEntity price = new PricesEntity();
        Prices priceDto = new Prices();

        when(pricesRepository.findTopByProductAndBrandAndDate(productId, brandId, date))
                .thenReturn(Optional.of(price));
        when(pricesMapper.mapToDto(price)).thenReturn(priceDto);

        // Act
        Prices result = getAvailablePriceUseCase.execute(productId, brandId, date);

        // Assert
        assertThat(result).isEqualTo(priceDto);
    }

    @Test
    void shouldThrowExceptionWhenPriceNotFound() {
        // Arrange
        Long productId = 35455L;
        Long brandId = 1L;
        Instant date = Instant.parse("2020-06-14T10:00:00Z");

        when(pricesRepository.findTopByProductAndBrandAndDate(productId, brandId, date))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> getAvailablePriceUseCase.execute(productId, brandId, date))
                .isInstanceOf(BackendException.class)
                .hasFieldOrPropertyWithValue("status", HttpStatus.BAD_REQUEST);
    }
}
