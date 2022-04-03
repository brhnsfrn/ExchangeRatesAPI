package com.brhnsfrn.exchangerate.api.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brhnsfrn.exchangerate.business.abstracts.ExchangeRateService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rates")
public class ExchangeRateController {
	private ExchangeRateService exchangeRateService;

	@Autowired
	public ExchangeRateController(ExchangeRateService exchangeRateService) {
		super();
		this.exchangeRateService = exchangeRateService;
	}
	
	@GetMapping
	@ApiOperation(value = "Returns all exchange rates.")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(this.exchangeRateService.getAll());
	}
	
	@GetMapping("/{code}")
	@ApiOperation(value = "Returns exchange rates by currency code.")
	public ResponseEntity<?> getByCode(@PathVariable("code") String code){
		return ResponseEntity.ok(this.exchangeRateService.getByCurrencyCode(code));
	}
	
	@GetMapping("/calculate/{code}/{unit}")
	@ApiOperation(value = "Calculates the exchange rates from {code} to TRY")
	public ResponseEntity<?> calculate(@PathVariable("code") String code, @PathVariable("unit") BigDecimal unit){
		return ResponseEntity.ok(this.exchangeRateService.calculate(code, unit));
	}
}
