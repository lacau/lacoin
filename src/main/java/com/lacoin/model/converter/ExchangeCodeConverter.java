package com.lacoin.model.converter;

import com.lacoin.model.enumeration.ExchangeCode;
import javax.persistence.AttributeConverter;

public class ExchangeCodeConverter implements AttributeConverter<ExchangeCode, String> {

    @Override
    public String convertToDatabaseColumn(ExchangeCode exchangeCode) {
        return exchangeCode.getCode();
    }

    @Override
    public ExchangeCode convertToEntityAttribute(String value) {
        return ExchangeCode.fromCode(value);
    }
}
