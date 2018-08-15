package com.lacoin.service;

import com.lacoin.external.response.MERCTickerResponse;
import com.lacoin.model.entity.Exchange;
import com.lacoin.model.entity.Quotation;
import com.lacoin.model.enumeration.CurrencyCode;
import com.lacoin.model.enumeration.ExchangeCode;
import com.lacoin.model.repository.ExchangeRepository;
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

    @Autowired
    private ExchangeRepository exchangeRepository;

    public MERCTickerResponse getTicker() {
        final String reqUrl = String.format(url, CurrencyCode.BTC, OPERATION_TICKER);

        try {
            final ResponseEntity<MERCTickerResponse> response = restTemplate.getForEntity(reqUrl, MERCTickerResponse.class);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Quotation getQuotation() {
        final MERCTickerResponse ticker = getTicker();
        final Exchange exchange = exchangeRepository.findOneByCode(ExchangeCode.MERCADO_BITCOIN);

        return Quotation.builder()
            .exchange(exchange)
            .amount(ticker == null ? BigDecimal.ZERO : ticker.getTicker().getLast())
            .volume(ticker == null ? BigDecimal.ZERO : ticker.getTicker().getVol())
            .build();
    }
}
