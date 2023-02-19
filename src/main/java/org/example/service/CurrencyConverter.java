package org.example.service;

import org.example.model.Currency;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {

    private Map<Currency, Double> currencyRateMapToUsd = new HashMap<>();

    public CurrencyConverter(String rates) {
        currencyRateMapToUsd.put(Currency.USD, 1d);
        for (String line : rates.split(",")) {
            try {
                Currency pairCurrency = Currency.valueOf(line.split(",")[0].split("-")[0].split(":")[1]);
                Double rate = Double.valueOf(line.split(",")[0].split("-")[1]);
                currencyRateMapToUsd.put(pairCurrency, rate);
            } catch (Exception e) {
                System.out.println("Can't parse pair: " + line);
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    private Double getRate(Currency input) {
        if (currencyRateMapToUsd.get(input) == null) {
            throw new IllegalArgumentException("Currency doesn't exist!");
        }
        return currencyRateMapToUsd.get(input);
    }

    public Double convertRate(Currency input, Currency output) {
        if (currencyRateMapToUsd.get(input) == null) {
            throw new IllegalArgumentException("Currency doesn't exist!");
        }
        return currencyRateMapToUsd.get(input)/currencyRateMapToUsd.get(output);
    }


}
