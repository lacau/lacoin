package com.lacoin.model.repository;

import com.lacoin.model.entity.Quotation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends CrudRepository<Quotation, Long> {

}
