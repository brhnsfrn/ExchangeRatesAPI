package com.brhnsfrn.exchangerate.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brhnsfrn.exchangerate.business.abstracts.CurrencyService;
import com.brhnsfrn.exchangerate.core.utilities.result.DataResult;
import com.brhnsfrn.exchangerate.dataAccess.abstracts.CurrencyDao;
import com.brhnsfrn.exchangerate.entities.concretes.Currency;
import com.brhnsfrn.exchangerate.entities.dtos.CurrencyDto;

@Service
public class CurrencyManager implements CurrencyService{
	
	@Autowired
	private ModelMapper modelMapper;

	private CurrencyDao currencyDao;
	
	@Autowired
	public CurrencyManager(CurrencyDao currencyDao) {
		this.currencyDao = currencyDao;
	}

	@Override
	public DataResult<List<CurrencyDto>> getAll() {
		List<CurrencyDto> response = this.currencyDao.findAll().stream().map(m -> entityToDto(m)).collect(Collectors.toList());
		return new DataResult<List<CurrencyDto>>(response);
	}
	
	private CurrencyDto entityToDto(Currency currency) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		CurrencyDto dto = new CurrencyDto();
		dto = modelMapper.map(currency, CurrencyDto.class);
		return dto;
	}

}
