package com.brhnsfrn.exchangerate.dataAccess.abstracts;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.brhnsfrn.exchangerate.entities.concretes.Currency;
import com.brhnsfrn.exchangerate.entities.dtos.CurrencyDto;

@Repository
public interface CurrencyDao extends JpaRepository<Currency, UUID> {
	@Query("Select new com.brhnsfrn.exchangerate.entities.dtos.CurrencyDto(c.currencyCode, c.currencyName) From Currency c")
	List<CurrencyDto> getAll();
	
	@Query("select case when count(c)> 0 then true else false end from Currency c where lower(c.currencyCode) like lower(:code)")
	boolean isExists(String code);
	
	Currency getByCurrencyCode(String currencyCode);
}
