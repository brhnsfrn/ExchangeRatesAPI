package com.brhnsfrn.exchangerate.entities.concretes;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "currencies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "currency_code", length = 3)
	private String currencyCode;
	
	@Column(name = "currency_name")
	private String currencyName;
}
