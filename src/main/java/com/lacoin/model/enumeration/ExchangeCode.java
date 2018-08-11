package com.lacoin.model.enumeration;

import java.util.Arrays;

public enum ExchangeCode {

    MERCADO_BITCOIN("MERC"),
    //FOXBIT("FOXB"),
    BLINK_TRADE("BKTR"),
    OMNI_TRADE("ONTR"),
    BITCOIN_TRADE("BTTR");

    final String code;

    ExchangeCode(final String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ExchangeCode fromCode(final String code) {
        return Arrays.stream(values())
            .filter(ec -> ec.code.equalsIgnoreCase(code))
            .findFirst()
            .orElse(null);
    }
}
