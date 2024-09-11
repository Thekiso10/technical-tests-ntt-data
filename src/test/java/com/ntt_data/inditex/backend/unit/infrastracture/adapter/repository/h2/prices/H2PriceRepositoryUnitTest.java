package com.ntt_data.inditex.backend.unit.infrastracture.adapter.repository.h2.prices;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.Optional;

import com.ntt_data.inditex.backend.infrastracture.adapter.repository.h2.prices.H2PriceRepository;
import com.ntt_data.inditex.backend.infrastracture.adapter.repository.h2.prices.SpringDataH2PricesRepository;
import com.ntt_data.inditex.backend.infrastracture.entity.PricesEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class H2PriceRepositoryUnitTest {

    @Mock
    private SpringDataH2PricesRepository repository;

    @InjectMocks
    private H2PriceRepository h2PriceRepository;

    @Test
    void testFindTopByProductAndBrandAndDate() {
        Long productId = 1L;
        Long brandId = 1L;
        Instant date = Instant.now();
        PricesEntity pricesEntity = new PricesEntity();

        when(repository.findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(productId, brandId, date, date))
                .thenReturn(Optional.of(pricesEntity));

        Optional<PricesEntity> result = h2PriceRepository.findTopByProductAndBrandAndDate(productId, brandId, date);

        assertTrue(result.isPresent());
        assertEquals(pricesEntity, result.get());
    }
}
