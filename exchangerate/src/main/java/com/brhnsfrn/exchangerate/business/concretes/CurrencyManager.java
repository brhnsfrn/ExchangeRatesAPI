package com.brhnsfrn.exchangerate.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brhnsfrn.exchangerate.business.abstracts.CurrencyService;
import com.brhnsfrn.exchangerate.core.utilities.result.DataResult;
import com.brhnsfrn.exchangerate.dataAccess.abstracts.CurrencyDao;
import com.brhnsfrn.exchangerate.entities.dtos.CurrencyDto;

@Service
public class CurrencyManager implements CurrencyService{

	private CurrencyDao currencyDao;
	
	@Autowired
	public CurrencyManager(CurrencyDao currencyDao) {
		this.currencyDao = currencyDao;
	}

	@Override
	public DataResult<List<CurrencyDto>> getAll() {
		return new DataResult<List<CurrencyDto>>(this.currencyDao.getAll());
	}

}
