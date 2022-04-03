package com.brhnsfrn.exchangerate.entities.concretes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "rates")
@Data
public class ExchangeRate {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private UUID id;
	
	@ManyToOne()
	@JoinColumn(name = "currency_id")
	private Currency currency;
	
	@Column(name = "unit", nullable = false)
	private int unit;
	
	@Transient
	@JsonIgnore
	private String currencyCode;
	
	@Transient
	@JsonIgnore
	private String currencyName;
	
	@Column(name = "buying", precision = 18, scale = 5)
	private BigDecimal buying;
	
	@Column(name = "selling", precision = 18, scale = 5)
	private BigDecimal selling;
	
	@Column(name = "banknote_buying", precision = 18, scale = 5)
	private BigDecimal banknoteBuying;
	
	@Column(name = "banknote_selling", precision = 18, scale = 5)
	private BigDecimal banknoteSelling;
	
	@Column(name = "cross_rate_usd", precision = 18, scale = 5)
	private BigDecimal crossRateUSD;
	
	@Column(name = "cross_rate_other", precision = 18, scale = 5)
	private BigDecimal crossRateOther;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @PrePersist
    protected void onCreate() {
    	createdDate = new Date();
    }
}
