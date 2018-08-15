package com.lacoin.service;

import com.lacoin.factory.ExchangeServiceFactory;
import com.lacoin.model.entity.Quotation;
import com.lacoin.model.enumeration.ExchangeCode;
import com.lacoin.model.repository.QuotationRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuotationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuotationService.class);

    @Autowired
    private ExchangeServiceFactory exchangeServiceFactory;

    @Autowired
    private QuotationRepository quotationRepository;

    public List<Quotation> fetchQuotations() {
        final List<Quotation> quotations = new ArrayList<>();
        for (ExchangeCode exchange : ExchangeCode.values()) {
            LOGGER.info("method=fetchQuotations, exchange={}, msg=Fetching quotation", exchange);
            final ExchangeServiceInterface service = exchangeServiceFactory.getService(exchange);
            quotations.add(service.getQuotation());
        }

        return quotations;
    }

    public List<Quotation> getLastQuotations() {
        return quotationRepository.findAllByOrderByDateDesc(PageRequest.of(0, ExchangeCode.values().length));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveQuotations(final List<Quotation> quotations) {
        quotationRepository.saveAll(quotations);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void purgeQuotations() {
        quotationRepository.deleteByDateBefore(Instant.now().minus(1, ChronoUnit.HOURS));
    }
}
