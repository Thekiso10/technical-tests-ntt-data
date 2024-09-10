package com.ntt_data.inditex.backend.application.usercase.adapter;

import com.ntt_data.inditex.backend.domain.model.Prices;
import com.ntt_data.inditex.backend.application.usercase.GetAvailablePriceUseCase;
import com.ntt_data.inditex.backend.domain.repository.PricesRepository;
import com.ntt_data.inditex.backend.infrastracture.mapper.PricesMapper;
import com.ntt_data.inditex.backend.infrastracture.exceptions.BackendException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetAvailablePriceUseCaseImpl implements GetAvailablePriceUseCase {

    private final PricesRepository pricesRepository;
    private final PricesMapper pricesMapper;

    @Override
    public Prices execute(Long productId, Long brandId, Instant date) {
        log.info("Get Price from productId: {}, brandId: {}, date: {}", productId, brandId, date.toString());
        return pricesRepository.findTopByProductAndBrandAndDate(productId, brandId, date)
                .map(pricesMapper::mapToDto)
                .orElseThrow(() -> new BackendException("ProductId, brandId or date specified do not return any valid prices", HttpStatus.BAD_REQUEST));
    }
}
