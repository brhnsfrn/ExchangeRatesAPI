package com.brhnsfrn.exchangerate.business.abstracts;

import java.util.List;

import com.brhnsfrn.exchangerate.core.utilities.result.DataResult;
import com.brhnsfrn.exchangerate.entities.dtos.CurrencyDto;

public interface CurrencyService {
	DataResult<List<CurrencyDto>> getAll();
}
