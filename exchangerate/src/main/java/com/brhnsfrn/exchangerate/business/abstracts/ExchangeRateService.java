package com.brhnsfrn.exchangerate.business.abstracts;

import java.math.BigDecimal;
import java.util.List;

import com.brhnsfrn.exchangerate.core.utilities.result.DataResult;
import com.brhnsfrn.exchangerate.entities.dtos.CalculatorResultDto;
import com.brhnsfrn.exchangerate.entities.dtos.ExchangeRateDto;

public interface ExchangeRateService {
	DataResult<List<ExchangeRateDto>> getAll();
	DataResult<ExchangeRateDto> getByCurrencyCode(String code);
	CalculatorResultDto calculate(String code, BigDecimal unit);
}
