package com.lacoin;

import com.lacoin.model.entity.Quotation;
import com.lacoin.service.QuotationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class Schedulers {

    @Autowired
    private QuotationService quotationService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void saveLastQuotation() {
        final List<Quotation> quotations = quotationService.fetchQuotations();
        if (!CollectionUtils.isEmpty(quotations)) {
            quotationService.saveQuotations(quotations);
        }
    }
}
