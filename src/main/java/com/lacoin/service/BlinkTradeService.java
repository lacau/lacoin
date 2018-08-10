package com.lacoin.service;

import com.lacoin.external.response.BKTRTickerResponse;
import com.lacoin.model.enumeration.CurrencyCode;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class BlinkTradeService implements ExchangeServiceInterface {

    private static final String OPERATION_TICKER = "ticker";

    @Value("${url.blink.trade}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BigDecimal getLastOrderPrice() {
        final String reqUrl = String.format(url, CurrencyCode.BRL, OPERATION_TICKER);
        final UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(reqUrl).queryParam("crypto_currency", CurrencyCode.BTC);

        try {
            final ResponseEntity<BKTRTickerResponse> response = restTemplate.getForEntity(urlBuilder.toUriString(), BKTRTickerResponse.class);
            return response.getBody().getLast();
        } catch (Exception e) {
            return null;
        }
    }
}
