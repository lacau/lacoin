package com.lacoin.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lacoin.model.enumeration.ExchangeCode;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuotationVO {

    @JsonProperty("exchange")
    private ExchangeCode exchange;

    @JsonProperty("quotation")
    private BigDecimal quotation;

    @JsonProperty("volume")
    private BigDecimal volume;
}
