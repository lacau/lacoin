package com.lacoin.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exchange {

    @Id
    @GeneratedValue(generator = "EXCHANGE_SEQ_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "EXCHANGE_SEQ_GEN", sequenceName = "seq_exchange")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Convert(converter = ExchangeCodeConverter.class)
    @Column(name = "code", nullable = false)
    private ExchangeCode code;
}