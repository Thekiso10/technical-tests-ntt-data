package com.ntt_data.inditex.backend.infrastracture.adapter.repository.h2.prices;

import com.ntt_data.inditex.backend.infrastracture.entity.PricesEntity;
import com.ntt_data.inditex.backend.domain.repository.PricesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class H2PriceRepository implements PricesRepository {

    private final SpringDataH2PricesRepository repository;

    @Override
    public Optional<PricesEntity> findTopByProductAndBrandAndDate(Long productId, Long brandId, Instant date) {
        return repository.findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(productId, brandId, date, date);
    }
}
