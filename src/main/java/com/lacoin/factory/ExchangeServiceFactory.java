package com.lacoin.factory;

import com.lacoin.model.enumeration.ExchangeCode;
import com.lacoin.service.ExchangeServiceInterface;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
public class ExchangeServiceFactory {

    @Autowired
    private Map<ExchangeCode, ExchangeServiceInterface> exchangeServiceFactory;

    public ExchangeServiceInterface getService(final ExchangeCode exchangeCode) {
        final ExchangeServiceInterface service = exchangeServiceFactory.get(exchangeCode);
        if (service == null) {
            throw new NotImplementedException();
        }

        return service;
    }
}
