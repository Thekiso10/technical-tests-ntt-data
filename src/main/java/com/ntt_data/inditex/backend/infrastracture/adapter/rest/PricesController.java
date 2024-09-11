package com.ntt_data.inditex.backend.infrastracture.adapter.rest;

import com.ntt_data.inditex.backend.application.usercase.GetAvailablePriceUseCase;
import com.ntt_data.inditex.backend.domain.model.Prices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @Operation(summary = "Get valid price", description = "Get the valid price of a donated product on a given date", tags = {"Price"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the correct record", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Prices.class))),
            @ApiResponse(responseCode = "400", description = "Error in request parameters", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Prices> getAvailablePriceByProductAndBrandAndDate(
            @RequestParam @NotNull Long productId,
            @RequestParam @NotNull Long brandId,
            @RequestParam @NotNull Instant date) {
        log.info("Get available price by product: {} and brand: {} and date: {}", productId, brandId, date.toString());
        return new ResponseEntity<>(getAvailablePriceUseCase.execute(productId, brandId, date), HttpStatus.OK);
    }
}
