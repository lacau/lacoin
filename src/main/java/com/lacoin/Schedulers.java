package com.lacoin;

import com.lacoin.model.entity.Quotation;
import com.lacoin.service.QuotationService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class Schedulers {

    private static final Logger LOGGER = LoggerFactory.getLogger(Schedulers.class);

    @Autowired
    private QuotationService quotationService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void saveLastQuotation() {
        LOGGER.info("method=saveLastQuotation, msg=Start fetch quotations job");
        final List<Quotation> quotations = quotationService.fetchQuotations();
        if (!CollectionUtils.isEmpty(quotations)) {
            quotationService.saveQuotations(quotations);
            LOGGER.info("method=saveLastQuotation, msg=Quotations updated");
        }
        LOGGER.info("method=saveLastQuotation, msg=Finish fetch quotations job");
    }

    @Scheduled(cron = "0 0 0/2 1/1 * ?")
    public void purgeQuotations() {
        LOGGER.info("method=purgeQuotations, msg=Start quotations purge job");
        quotationService.purgeQuotations();
        LOGGER.info("method=purgeQuotations, msg=Finish quotations purge job");
    }
}
