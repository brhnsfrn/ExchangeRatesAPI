package com.brhnsfrn.exchangerate.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDto {
	@JsonProperty("code")
	private String currencyCode;
	
	@JsonProperty("name")
	private String currencyName;
}