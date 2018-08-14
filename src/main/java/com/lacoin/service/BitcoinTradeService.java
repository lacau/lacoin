package com.lacoin.service;

import com.lacoin.external.response.BTTRTickerResponse;
import com.lacoin.model.entity.Exchange;
import com.lacoin.model.entity.Quotation;
import com.lacoin.model.enumeration.CurrencyCode;
import com.lacoin.model.enumeration.ExchangeCode;
import com.lacoin.model.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BitcoinTradeService implements ExchangeServiceInterface {

    private static final String OPERATION_TICKER = "ticker";

    @Value("${url.bitcoin.trade}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ExchangeRepository exchangeRepository;

    public BTTRTickerResponse getTicker() {
        final String reqUrl = String.format(url, CurrencyCode.BTC, OPERATION_TICKER);

        try {
            final ResponseEntity<BTTRTickerResponse> response = restTemplate.getForEntity(reqUrl, BTTRTickerResponse.class);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Quotation getQuotation() {
        final BTTRTickerResponse ticker = getTicker();
        if (ticker == null) {
            return null;
        }

        final Exchange exchange = exchangeRepository.findOneByCode(ExchangeCode.BITCOIN_TRADE);

        return Quotation.builder()
            .exchange(exchange)
            .amount(ticker.getData().getLast())
            .volume(ticker.getData().getVolume())
            .build();
    }
}
