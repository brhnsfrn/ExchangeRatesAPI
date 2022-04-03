package com.brhnsfrn.exchangerate.entities.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateDto {
	private String currencyCode;
	private String currencyName;
	private BigDecimal buying;
	private BigDecimal selling;
	private BigDecimal banknoteBuying;
	private BigDecimal banknoteSelling;
	private BigDecimal crossRateUSD;
	private BigDecimal crossRateOther;
	private int unit;
	private Date updateDate;
}
