package com.lacoin.model.entity;

import com.lacoin.LacoinContants;
import com.lacoin.model.enumeration.CurrencyCode;
import com.lacoin.model.enumeration.FeeType;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class ExchangeFee {

    @Id
    @GeneratedValue(generator = "EXCHANGE_FEE_SEQ_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "EXCHANGE_FEE_SEQ_GEN", sequenceName = "seq_exchange_fee")
    private Long id;

    @Column(name = "fixed_amount", nullable = false, precision = LacoinContants.SATOSHI_LENGTH)
    private BigDecimal fixedAmount;

    @Column(name = "percentage_amount", nullable = false, precision = LacoinContants.SATOSHI_LENGTH)
    private BigDecimal percentageAmount;

    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FeeType type;

    @ManyToOne
    @JoinColumn(name = "fk_exchange", nullable = false)
    private Exchange exchange;
}