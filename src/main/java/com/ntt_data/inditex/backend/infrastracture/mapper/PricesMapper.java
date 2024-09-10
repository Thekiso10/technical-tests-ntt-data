package com.ntt_data.inditex.backend.infrastracture.mapper;

import com.ntt_data.inditex.backend.domain.model.Prices;
import com.ntt_data.inditex.backend.infrastracture.entity.PricesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PricesMapper {

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "product.id", target = "productId")
    Prices mapToDto(PricesEntity entity);
}
