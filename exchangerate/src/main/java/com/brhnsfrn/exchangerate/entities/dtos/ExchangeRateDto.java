package com.brhnsfrn.exchangerate.entities.dtos;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateDto {
	@JsonProperty("code")
	private String currencyCode;
	
	@JsonProperty("name")
	private String currencyName;
	
	@JsonProperty("buying")
	private BigDecimal buying;
	
	@JsonProperty("selling")
	private BigDecimal selling;
	
	@JsonProperty("effectiveBuying")
	private BigDecimal banknoteBuying;
	
	@JsonProperty("effectiveSelling")
	private BigDecimal banknoteSelling;
	
	@JsonProperty("crossRateUsd")
	private BigDecimal crossRateUSD;
	
	@JsonProperty("crossRateOther")
	private BigDecimal crossRateOther;
	
	@JsonProperty("unit")
	private int unit;
	
	@JsonProperty("date")
	private Date createdDate;
}
