package com.lacoin.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class BTTRTickerResponse {

    @JsonProperty("data")
    private Ticker data;

    @Data
    public class Ticker {

        @JsonProperty("high")
        private BigDecimal high;

        @JsonProperty("low")
        private BigDecimal low;

        @JsonProperty("volume")
        private BigDecimal volume;

        @JsonProperty("last")
        private BigDecimal last;

        @JsonProperty("buy")
        private BigDecimal buy;

        @JsonProperty("sell")
        private BigDecimal sell;
    }
}
