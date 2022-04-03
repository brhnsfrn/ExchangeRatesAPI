package com.brhnsfrn.exchangerate.business.concretes;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.brhnsfrn.exchangerate.business.abstracts.ExchangeRateService;
import com.brhnsfrn.exchangerate.core.utilities.result.DataResult;
import com.brhnsfrn.exchangerate.dataAccess.abstracts.CurrencyDao;
import com.brhnsfrn.exchangerate.dataAccess.abstracts.ExchangeRateDao;
import com.brhnsfrn.exchangerate.entities.dtos.CalculatorResultDto;
import com.brhnsfrn.exchangerate.entities.dtos.ExchangeRateDto;

@Service
public class ExchangeRateManager implements ExchangeRateService{

	private ExchangeRateDao exchangeRateDao;
	private CurrencyDao currencyDao;
	
	@Autowired
	public ExchangeRateManager(ExchangeRateDao exchangeRateDao, CurrencyDao currencyDao) {
		super();
		this.exchangeRateDao = exchangeRateDao;
		this.currencyDao = currencyDao;
	}
	
	@Override
	public DataResult<List<ExchangeRateDto>> getAll() {
		return new DataResult<List<ExchangeRateDto>>(this.exchangeRateDao.getAll());
	}

	@Override
	public ExchangeRateDto getByCurrencyCode(String code) {
		if(!this.currencyDao.isExists(code)) {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Para birimi mevcut değil!"
					);
		}
		return this.exchangeRateDao.getByCode(code);
	}

	@Override
	public CalculatorResultDto calculate(String code, BigDecimal unit) {
		CalculatorResultDto result = new CalculatorResultDto();
		ExchangeRateDto dto = this.exchangeRateDao.getByCode(code);
		BigDecimal price = dto.getSelling().divide(new BigDecimal(dto.getUnit()));
		result.setFrom(dto.getCurrencyCode());
		result.setUnit(unit);
		result.setResult(unit.multiply(price));
		return result;
	}

}
