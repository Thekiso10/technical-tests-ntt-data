package com.ntt_data.inditex.backend.domain.model;

import lombok.Data;

import java.time.Instant;

@Data
public class Prices {

    private Long productId;
    private Long brandId;
    private Integer priceList;
    private Instant startDate;
    private Instant endDate;
    private Double price;
    private String currency;
}
