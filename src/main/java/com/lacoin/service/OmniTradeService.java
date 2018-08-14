package com.lacoin.service;

import com.lacoin.LacoinContants;
import com.lacoin.controller.vo.QuotationVO;
import com.lacoin.external.response.ONTRTickerResponse;
import com.lacoin.model.enumeration.CurrencyCode;
import com.lacoin.model.enumeration.ExchangeCode;
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

    public ONTRTickerResponse getTicker() {
        final String reqUrl = String.format(urlTicker, CurrencyCode.BTC.toString().toLowerCase() + CurrencyCode.BRL.toString().toLowerCase());

        final HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", LacoinContants.DEFAULT_USER_AGENT);
        final HttpEntity entity = new HttpEntity<>(headers);

        try {
            final ResponseEntity<ONTRTickerResponse> response = restTemplate.exchange(reqUrl, HttpMethod.GET, entity, ONTRTickerResponse.class);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public QuotationVO getQuotation() {
        final ONTRTickerResponse ticker = getTicker();

        return QuotationVO.builder()
            .exchange(ExchangeCode.OMNI_TRADE)
            .quotation(ticker.getTicker().getLast())
            .volume(ticker.getTicker().getVol())
            .build();
    }
}
