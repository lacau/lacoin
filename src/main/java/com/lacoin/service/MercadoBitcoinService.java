package com.lacoin.service;

import com.lacoin.controller.vo.QuotationVO;
import com.lacoin.external.response.MERCTickerResponse;
import com.lacoin.model.enumeration.CurrencyCode;
import com.lacoin.model.enumeration.ExchangeCode;
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
    public QuotationVO getQuotation() {
        final MERCTickerResponse ticker = getTicker();

        return QuotationVO.builder()
            .exchange(ExchangeCode.MERCADO_BITCOIN)
            .quotation(ticker.getTicker().getLast())
            .volume(ticker.getTicker().getVol())
            .build();
    }
}
