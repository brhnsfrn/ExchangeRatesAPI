package com.brhnsfrn.exchangerate.entities.concretes;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "currencies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "exchangeRates"})
public class Currency {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "currency_code", length = 3)
	private String currencyCode;
	
	@Column(name = "currency_name")
	private String currencyName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "currency")
	private List<ExchangeRate> exchangeRates;
}
