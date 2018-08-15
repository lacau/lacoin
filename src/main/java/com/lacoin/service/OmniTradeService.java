package com.lacoin.service;

import com.lacoin.LacoinContants;
import com.lacoin.external.response.ONTRTickerResponse;
import com.lacoin.model.entity.Exchange;
import com.lacoin.model.entity.Quotation;
import com.lacoin.model.enumeration.CurrencyCode;
import com.lacoin.model.enumeration.ExchangeCode;
import com.lacoin.model.repository.ExchangeRepository;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(OmniTradeService.class);

    @Value("${url.omni.trade.ticker}")
    private String urlTicker;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ExchangeRepository exchangeRepository;

    public ONTRTickerResponse getTicker() {
        final String reqUrl = String.format(urlTicker, CurrencyCode.BTC.toString().toLowerCase() + CurrencyCode.BRL.toString().toLowerCase());

        final HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", LacoinContants.DEFAULT_USER_AGENT);
        final HttpEntity entity = new HttpEntity<>(headers);

        try {
            final ResponseEntity<ONTRTickerResponse> response = restTemplate.exchange(reqUrl, HttpMethod.GET, entity, ONTRTickerResponse.class);
            return response.getBody();
        } catch (Exception e) {
            LOGGER.error("method=getTicker, exchange={}, msg=Error on get ticker from exchange", ExchangeCode.OMNI_TRADE);
            return null;
        }
    }

    @Override
    public Quotation getQuotation() {
        final ONTRTickerResponse ticker = getTicker();
        final Exchange exchange = exchangeRepository.findOneByCode(ExchangeCode.OMNI_TRADE);

        return Quotation.builder()
            .exchange(exchange)
            .amount(ticker == null ? BigDecimal.ZERO : ticker.getTicker().getLast())
            .volume(ticker == null ? BigDecimal.ZERO : ticker.getTicker().getVol())
            .build();
    }
}
