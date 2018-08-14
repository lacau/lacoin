package com.lacoin.model.repository;

import com.lacoin.model.entity.Exchange;
import com.lacoin.model.enumeration.ExchangeCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends CrudRepository<Exchange, Long> {

    Exchange findOneByCode(ExchangeCode code);
}
