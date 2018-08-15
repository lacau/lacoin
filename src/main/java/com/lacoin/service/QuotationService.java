package com.lacoin.service;

import com.lacoin.factory.ExchangeServiceFactory;
import com.lacoin.model.entity.Quotation;
import com.lacoin.model.enumeration.ExchangeCode;
import com.lacoin.model.repository.QuotationRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuotationService {

    @Autowired
    private ExchangeServiceFactory exchangeServiceFactory;

    @Autowired
    private QuotationRepository quotationRepository;

    public List<Quotation> fetchQuotations() {
        final List<Quotation> quotations = new ArrayList<>();
        for (ExchangeCode exchange : ExchangeCode.values()) {
            final ExchangeServiceInterface service = exchangeServiceFactory.getService(exchange);
            final Quotation quotation = service.getQuotation();

            if (quotation != null) {
                quotations.add(quotation);
            }
        }

        return quotations;
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