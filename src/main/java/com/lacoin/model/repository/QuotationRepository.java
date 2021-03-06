package com.lacoin.model.repository;

import com.lacoin.model.entity.Quotation;
import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface QuotationRepository extends CrudRepository<Quotation, Long> {

    List<Quotation> findAllByOrderByDateDesc(Pageable pageable);

    @Modifying
    @Transactional
    void deleteByDateBefore(Instant instant);
}
