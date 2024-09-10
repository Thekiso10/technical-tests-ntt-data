package com.ntt_data.inditex.backend.application.usercase;

import com.ntt_data.inditex.backend.domain.model.Prices;

import java.time.Instant;

public interface GetAvailablePriceUseCase {
    Prices execute(Long productId, Long brandId, Instant date);
}
