package com.brhnsfrn.exchangerate.entities.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CalculatorResultDto {
	private String from;
	private BigDecimal unit;
	private String to = "TRY";
	private BigDecimal result;
	
}
