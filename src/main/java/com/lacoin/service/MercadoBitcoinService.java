package com.lacoin.service;

import com.lacoin.external.response.MERCLastOrderPriceResponse;
import com.lacoin.model.enumeration.CoinCode;
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
        final String reqUrl = String.format(url, CoinCode.BTC, OPERATION_TICKER);

        try {
            final ResponseEntity<MERCLastOrderPriceResponse> response = restTemplate.getForEntity(reqUrl, MERCLastOrderPriceResponse.class);
            return response.getBody().getTicker().getLast();
        } catch (Exception e) {
            return null;
        }
    }
}
