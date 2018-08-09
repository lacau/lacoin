package com.lacoin.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class MERCLastOrderPriceResponse {

    @JsonProperty("last")
    private BigDecimal last;
}
