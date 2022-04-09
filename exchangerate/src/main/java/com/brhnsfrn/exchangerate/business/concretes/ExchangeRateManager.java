package com.brhnsfrn.exchangerate.business.concretes;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.brhnsfrn.exchangerate.business.abstracts.ExchangeRateService;
import com.brhnsfrn.exchangerate.core.utilities.result.DataResult;
import com.brhnsfrn.exchangerate.dataAccess.abstracts.CurrencyDao;
import com.brhnsfrn.exchangerate.dataAccess.abstracts.ExchangeRateDao;
import com.brhnsfrn.exchangerate.entities.concretes.ExchangeRate;
import com.brhnsfrn.exchangerate.entities.dtos.CalculatorResultDto;
import com.brhnsfrn.exchangerate.entities.dtos.ExchangeRateDto;

@Service
public class ExchangeRateManager implements ExchangeRateService{
	
	@Autowired
	private ModelMapper modelMapper;
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
		List<ExchangeRateDto> response = this.exchangeRateDao.findAll().stream().map(m -> entityToDto(m)).collect(Collectors.toList());
		return new DataResult<List<ExchangeRateDto>>(response);
	}

	@Override
	public DataResult<ExchangeRateDto> getByCurrencyCode(String code) {
		if(!this.currencyDao.existsByCurrencyCodeIgnoreCase(code)) {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Para birimi mevcut değil!"
					);
		}
		return new DataResult<ExchangeRateDto>(entityToDto(this.exchangeRateDao.getByCurrency_CurrencyCodeIgnoreCase(code)));
	}

	@Override
	public CalculatorResultDto calculate(String code, BigDecimal unit) {
		if(!this.currencyDao.existsByCurrencyCodeIgnoreCase(code)) {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Para birimi mevcut değil!"
					);
		}
		
		CalculatorResultDto result = new CalculatorResultDto();
		ExchangeRate dto = this.exchangeRateDao.getByCurrency_CurrencyCodeIgnoreCase(code);
		BigDecimal price = dto.getSelling().divide(new BigDecimal(dto.getUnit()));
		result.setFrom(dto.getCurrency().getCurrencyCode());
		result.setUnit(unit);
		result.setResult(unit.multiply(price));
		return result;
	}
	
	private ExchangeRateDto entityToDto(ExchangeRate exchangeRate) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		ExchangeRateDto dto = new ExchangeRateDto();
		dto = modelMapper.map(exchangeRate, ExchangeRateDto.class);
		return dto;
	}

}
