package com.lacoin.model.entity;

import com.lacoin.LacoinContants;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import lombok.ToString;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quotation {

    @Id
    @GeneratedValue(generator = "QUOTATION_SEQ_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "QUOTATION_SEQ_GEN", sequenceName = "seq_quotation")
    private Long id;

    @Column(name = "amount", nullable = false, precision = LacoinContants.SATOSHI_LENGTH)
    private BigDecimal amount;

    @Column(name = "volume", nullable = false, precision = LacoinContants.SATOSHI_LENGTH)
    private BigDecimal volume;

    @Column(name = "date", nullable = false)
    @Builder.Default
    private Instant date = Instant.now();

    @ManyToOne
    @JoinColumn(name = "fk_exchange", nullable = false)
    private Exchange exchange;
}