package com.lacoin.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class BKTRTickerResponse {

    @JsonProperty("high")
    private BigDecimal high;

    @JsonProperty("low")
    private BigDecimal low;

    @JsonProperty("vol")
    private BigDecimal vol;

    @JsonProperty("last")
    private BigDecimal last;

    @JsonProperty("buy")
    private BigDecimal buy;

    @JsonProperty("sell")
    private BigDecimal sell;
}
