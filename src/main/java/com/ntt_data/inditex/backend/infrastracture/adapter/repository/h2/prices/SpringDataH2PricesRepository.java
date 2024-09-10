package com.ntt_data.inditex.backend.infrastracture.adapter.repository.h2.prices;

import com.ntt_data.inditex.backend.infrastracture.entity.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface SpringDataH2PricesRepository extends JpaRepository<PricesEntity, Long> {

    Optional<PricesEntity> findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(Long productId, Long brandId, Instant date1, Instant date2);
}
