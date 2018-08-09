package com.lacoin.service;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class FoxbitService implements ExchangeServiceInterface {

    @Override
    public BigDecimal getLastOrderPrice() {
        return null;
    }
}
