package com.lacoin.service;

import com.lacoin.external.response.MERCTickerResponse;
import com.lacoin.model.enumeration.CurrencyCode;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MercadoBitcoinService implements ExchangeServiceInterface {

    private static final String OPERATION_TICKER = "ticker";

    @Value("${url.mercado.bitcoin}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BigDecimal getLastOrderPrice() {
        final String reqUrl = String.format(url, CurrencyCode.BTC, OPERATION_TICKER);

        try {
            final ResponseEntity<MERCTickerResponse> response = restTemplate.getForEntity(reqUrl, MERCTickerResponse.class);
            return response.getBody().getTicker().getLast();
        } catch (Exception e) {
            return null;
        }
    }
}
