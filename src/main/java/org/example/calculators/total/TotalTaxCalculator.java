package org.example.calculators.total;

import org.example.model.Citizen;
import org.example.model.Country;
import org.example.model.CountryReport;
import org.example.model.Currency;
import org.example.service.CurrencyConverter;

import java.util.List;

public class TotalTaxCalculator {

    private CurrencyConverter currencyCalculator;

    public TotalTaxCalculator(CurrencyConverter currencyCalculator) {
        this.currencyCalculator = currencyCalculator;
    }


    public CountryReport calculateReport(List<Citizen> citizens, Country country, Currency reportCurrency) {
        Integer totalCitizenNumber = 0;
        Double totalTax = 0d;

        for (Citizen citizen : citizens) {
            if (citizen.getCountry() == country) {
                totalCitizenNumber++;
                totalTax = totalTax + citizen.getTotalTax();
            }
        }

        double totalTaxConvertedToCurrency = this.currencyCalculator.convertRate(country.getCurrency(), reportCurrency) * totalTax;
        Double averageTaxConvertedToCurrency = totalTaxConvertedToCurrency / (double) totalCitizenNumber;

        return CountryReport.builder()
                .country(country)
                .currency(reportCurrency)
                .citizensNumber(totalCitizenNumber)
                .totalTax(totalTaxConvertedToCurrency)
                .totalAverageTax(averageTaxConvertedToCurrency)
                .build();
    }

}
