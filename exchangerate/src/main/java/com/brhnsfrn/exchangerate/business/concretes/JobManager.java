package com.brhnsfrn.exchangerate.business.concretes;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.brhnsfrn.exchangerate.business.abstracts.JobService;
import com.brhnsfrn.exchangerate.core.reader.abstracts.ReaderSevice;
import com.brhnsfrn.exchangerate.core.utilities.result.Result;
import com.brhnsfrn.exchangerate.dataAccess.abstracts.CurrencyDao;
import com.brhnsfrn.exchangerate.dataAccess.abstracts.ExchangeRateDao;
import com.brhnsfrn.exchangerate.entities.concretes.Currency;
import com.brhnsfrn.exchangerate.entities.concretes.ExchangeRate;

@Service
public class JobManager implements JobService{
	private static final Logger LOG = LoggerFactory.getLogger(JobManager.class);
	private CurrencyDao currencyDao;
	private ExchangeRateDao exchangeRateDao;
	private ReaderSevice tcmbReader;
	
	@Autowired
	public JobManager(CurrencyDao currencyDao, ExchangeRateDao exchangeRateDao, ReaderSevice tcmbReader) {
		super();
		this.currencyDao = currencyDao;
		this.exchangeRateDao = exchangeRateDao;
		this.tcmbReader = tcmbReader;
	}


	@Override
	@Scheduled(cron = "${job.cron.expression}", zone = "Europe/Istanbul")
	public Result getData() {
		LOG.info("--- job started ---");
		List<ExchangeRate> listData = tcmbReader.getData();
		for(ExchangeRate item : listData) {
			if(this.currencyDao.existsByCurrencyCodeIgnoreCase(item.getCode())) {
				item.setCurrency(this.currencyDao.getByCurrencyCode(item.getCode()));
			}
			else {
				Currency currency = new Currency();
				currency.setCurrencyCode(item.getCode());
				currency.setCurrencyName(item.getName());
				item.setCurrency(this.currencyDao.save(currency));
			}
		}
		
		this.exchangeRateDao.deleteAll();
		this.exchangeRateDao.saveAll(listData);
		
		LOG.info("--- job executed ---");
		return new Result(true);
	}
	
	@PostConstruct
	public void onStartup() {
		getData();
	}
}
