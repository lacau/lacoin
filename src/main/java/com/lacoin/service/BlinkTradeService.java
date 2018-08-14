package com.lacoin.service;

import com.lacoin.controller.vo.QuotationVO;
import com.lacoin.external.response.BKTRTickerResponse;
import com.lacoin.model.enumeration.CurrencyCode;
import com.lacoin.model.enumeration.ExchangeCode;
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
    public QuotationVO getQuotation() {
        final BKTRTickerResponse ticker = getTicker();

        return QuotationVO.builder()
            .exchange(ExchangeCode.BLINK_TRADE)
            .quotation(ticker.getLast())
            .volume(ticker.getVol())
            .build();
    }
}
