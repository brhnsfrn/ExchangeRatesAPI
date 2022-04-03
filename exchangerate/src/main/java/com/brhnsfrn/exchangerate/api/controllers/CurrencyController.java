package com.brhnsfrn.exchangerate.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.brhnsfrn.exchangerate.business.abstracts.CurrencyService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
	private CurrencyService currencyService;

	@Autowired
	public CurrencyController(CurrencyService currencyService) {
		super();
		this.currencyService = currencyService;
	}
	
	@GetMapping
	@ApiOperation(value = "Returns all currencies.")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(this.currencyService.getAll());
	}
}
