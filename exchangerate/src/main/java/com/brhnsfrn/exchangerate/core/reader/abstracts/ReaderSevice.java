package com.brhnsfrn.exchangerate.core.reader.abstracts;

import java.util.List;

import com.brhnsfrn.exchangerate.entities.concretes.ExchangeRate;

public interface ReaderSevice {
	List<ExchangeRate> getData();
}
