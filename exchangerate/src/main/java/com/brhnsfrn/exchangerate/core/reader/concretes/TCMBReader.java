package com.brhnsfrn.exchangerate.core.reader.concretes;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.brhnsfrn.exchangerate.core.reader.abstracts.ReaderSevice;
import com.brhnsfrn.exchangerate.entities.concretes.ExchangeRate;

public class TCMBReader implements ReaderSevice{
	
	@Override
	public List<ExchangeRate> getData() {
		List<ExchangeRate> rates = new ArrayList<ExchangeRate>();
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			URL url = new URL("https://www.tcmb.gov.tr/kurlar/today.xml");
			Document document = builder.parse(url.openStream());
			
			NodeList currencyList = document.getElementsByTagName("Currency");
			
			for(int i = 0; i < currencyList.getLength(); i++) {
				Node node = currencyList.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					if(element.getAttribute("CurrencyCode").equals("XDR")) {
						//XDR - ÖZEL ÇEKME HAKKI (SDR) - SPECIAL DRAWING RIGHT (SDR)
						continue;
					}
					ExchangeRate rateItem = new ExchangeRate();
					rateItem.setCurrencyCode(element.getAttribute("CurrencyCode"));
					rateItem.setCurrencyName(getElementValue(element, "Isim"));
					rateItem.setBuying(getDecimal(getElementValue(element, "ForexBuying")));
					rateItem.setSelling(getDecimal(getElementValue(element, "ForexSelling")));
					rateItem.setBanknoteBuying(getDecimal(getElementValue(element, "BanknoteBuying")));
					rateItem.setBanknoteSelling(getDecimal(getElementValue(element, "BanknoteSelling")));
					rateItem.setCrossRateUSD(getDecimal(getElementValue(element, "CrossRateUSD")));
					rateItem.setCrossRateOther(getDecimal(getElementValue(element, "CrossRateOther")));
					rateItem.setUnit(Integer.parseInt(getElementValue(element, "Unit")));
					rates.add(rateItem);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return rates;
	}
	
	protected BigDecimal getDecimal(String value) {
		if(value != null && !value.equals("")) {
			return new BigDecimal(value).setScale(5);
		}
		return BigDecimal.ZERO;
	}
	
	protected static String getElementValue(Element parent, String label) {
		return parent.getElementsByTagName(label).item(0).getTextContent();
	}

}
