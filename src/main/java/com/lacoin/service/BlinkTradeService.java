package com.lacoin.service;

import com.lacoin.external.response.BKTRTickerResponse;
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
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class BlinkTradeService implements ExchangeServiceInterface {

    private static final String OPERATION_TICKER = "ticker";

    @Value("${url.blink.trade}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ExchangeRepository exchangeRepository;

    public BKTRTickerResponse getTicker() {
        final String reqUrl = String.format(url, CurrencyCode.BRL, OPERATION_TICKER);
        final UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(reqUrl).queryParam("crypto_currency", CurrencyCode.BTC);

        try {
            final ResponseEntity<BKTRTickerResponse> response = restTemplate.getForEntity(urlBuilder.toUriString(), BKTRTickerResponse.class);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Quotation getQuotation() {
        final BKTRTickerResponse ticker = getTicker();
        if (ticker == null) {
            return null;
        }

        final Exchange exchange = exchangeRepository.findOneByCode(ExchangeCode.BLINK_TRADE);

        return Quotation.builder()
            .exchange(exchange)
            .amount(ticker.getLast())
            .volume(ticker.getVol())
            .build();
    }
}
