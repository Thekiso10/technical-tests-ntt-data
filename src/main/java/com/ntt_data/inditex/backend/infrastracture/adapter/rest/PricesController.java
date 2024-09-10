package com.ntt_data.inditex.backend.infrastracture.adapter.rest;

import com.ntt_data.inditex.backend.domain.model.Prices;
import com.ntt_data.inditex.backend.application.usercase.GetAvailablePriceUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/prices")
public class PricesController {

    private final GetAvailablePriceUseCase getAvailablePriceUseCase;

    @GetMapping("/available")
    public ResponseEntity<Prices> getAvailablePriceByProductAndBrandAndDate(
            @RequestParam @NotNull Long productId,
            @RequestParam @NotNull Long brandId,
            @RequestParam @NotNull Instant date) {
        log.info("Get available price by product: {} and brand: {} and date: {}", productId, brandId, date.toString());
        return new ResponseEntity<>(getAvailablePriceUseCase.execute(productId, brandId, date), HttpStatus.OK);
    }
}
