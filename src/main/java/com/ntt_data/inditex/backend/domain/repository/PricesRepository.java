package com.ntt_data.inditex.backend.domain.repository;

import com.ntt_data.inditex.backend.infrastracture.entity.PricesEntity;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface PricesRepository {

    Optional<PricesEntity> findTopByProductAndBrandAndDate(Long productId, Long brandId, Instant date);
}
