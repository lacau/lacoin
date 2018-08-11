package com.lacoin.service;

import com.lacoin.LacoinContants;
import com.lacoin.external.response.ONTRTickerResponse;
import com.lacoin.model.enumeration.CurrencyCode;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OmniTradeService implements ExchangeServiceInterface {

    @Value("${url.omni.trade.ticker}")
    private String urlTicker;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BigDecimal getLastOrderPrice() {
        final String reqUrl = String.format(urlTicker, CurrencyCode.BTC.toString().toLowerCase() + CurrencyCode.BRL.toString().toLowerCase());

        final HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", LacoinContants.DEFAULT_USER_AGENT);
        final HttpEntity entity = new HttpEntity<>(headers);

        try {
            final ResponseEntity<ONTRTickerResponse> response = restTemplate.exchange(reqUrl, HttpMethod.GET, entity, ONTRTickerResponse.class);
            return response.getBody().getTicker().getLast();
        } catch (Exception e) {
            return null;
        }
    }
}
