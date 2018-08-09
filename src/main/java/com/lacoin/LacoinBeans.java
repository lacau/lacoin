package com.lacoin;

import com.lacoin.model.enumeration.ExchangeCode;
import com.lacoin.service.ExchangeServiceInterface;
import com.lacoin.service.FoxbitService;
import com.lacoin.service.MercadoBitcoinService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LacoinBeans {

    @Autowired
    private MercadoBitcoinService mercadoBitcoinService;

    @Autowired
    private FoxbitService foxbitService;

    @Bean("exchangeServiceFactory")
    public Map<ExchangeCode, ExchangeServiceInterface> exchangeServiceMap() {
        final Map<ExchangeCode, ExchangeServiceInterface> map = new HashMap<>();
        map.put(ExchangeCode.MERCADO_BITCOIN, mercadoBitcoinService);
        map.put(ExchangeCode.FOXBIT, foxbitService);

        return map;
    }
}
