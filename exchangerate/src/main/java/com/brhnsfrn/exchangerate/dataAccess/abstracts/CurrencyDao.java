package com.brhnsfrn.exchangerate.dataAccess.abstracts;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.brhnsfrn.exchangerate.entities.concretes.Currency;

@Repository
public interface CurrencyDao extends JpaRepository<Currency, UUID> {	
	boolean existsByCurrencyCodeIgnoreCase(String currencyCode);
	
	Currency getByCurrencyCode(String currencyCode);
}
