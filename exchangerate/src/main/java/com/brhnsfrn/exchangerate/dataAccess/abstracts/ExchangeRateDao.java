package com.brhnsfrn.exchangerate.dataAccess.abstracts;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brhnsfrn.exchangerate.entities.concretes.ExchangeRate;

@Repository
public interface ExchangeRateDao extends JpaRepository<ExchangeRate, UUID> {	
	ExchangeRate getByCurrency_CurrencyCodeIgnoreCase(String currencyCode);
}
