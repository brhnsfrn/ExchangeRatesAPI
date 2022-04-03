package com.brhnsfrn.exchangerate.dataAccess.abstracts;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brhnsfrn.exchangerate.entities.concretes.ExchangeRate;
import com.brhnsfrn.exchangerate.entities.dtos.ExchangeRateDto;

@Repository
public interface ExchangeRateDao extends JpaRepository<ExchangeRate, UUID> {
	@Query("Select new com.brhnsfrn.exchangerate.entities.dtos.ExchangeRateDto(c.currencyCode, c.currencyName, er.buying, er.selling, er.banknoteBuying, er.banknoteSelling, er.crossRateUSD, er.crossRateOther, er.unit, er.createdDate) From Currency c Inner Join c.exchangeRates er")
	List<ExchangeRateDto> getAll();
	
	@Query("Select new com.brhnsfrn.exchangerate.entities.dtos.ExchangeRateDto(c.currencyCode, c.currencyName, er.buying, er.selling, er.banknoteBuying, er.banknoteSelling, er.crossRateUSD, er.crossRateOther, er.unit, er.createdDate) From Currency c Inner Join c.exchangeRates er where lower(c.currencyCode) like lower(:code)")
	ExchangeRateDto getByCode(String code);
}
