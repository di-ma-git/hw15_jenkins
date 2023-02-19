package org.example;

import org.example.calculators.*;
import org.example.calculators.total.TotalTaxCalculator;
import org.example.model.Citizen;
import org.example.model.Country;
import org.example.model.CountryReport;
import org.example.report.PdfReportWriter;
import org.example.report.ReportWriter;
import org.example.report.TextReportWriter;
import org.example.service.CurrencyConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.model.Currency.RUB;
import static org.example.model.Currency.USD;
import static org.example.service.ReadService.readCitizensFromFile;

public class Main {
    public static final String CITIZEN_PATH = "C:\\Users\\icju\\java_core_2023_jan\\src\\main\\resources\\citizens.txt";
    public static final String REPORT_TXT_FILE = "C:\\Users\\icju\\java_core_2023_jan\\src\\main\\resources\\textReport.txt";
    public static final String PDF_TXT_FILE = "C:\\Users\\icju\\java_core_2023_jan\\src\\main\\resources\\pdfReport.pdf";
    public static final String FINAL_PDF_REPORT = "C:\\Users\\icju\\java_core_2023_jan\\src\\main\\resources\\finalPdfReport.pdf";
    public static final String FINAL_PDF_REPORT_USD = "C:\\Users\\icju\\java_core_2023_jan\\src\\main\\resources\\finalPdfReportUSD.pdf";
    public static final Map<Country, TaxCalculator> taxCalculators = new HashMap<>();


    public static void main(String[] args) {
        CurrencyConverter currencyCalculator = new CurrencyConverter("USD:GBP-0.75,USD:EUR-0.9,USD:RUB-0.02");

        taxCalculators.put(Country.UK, new UkTaxCalculator());
        taxCalculators.put(Country.CANADA, new CanadianTaxCalculator());
        taxCalculators.put(Country.RUSSIA, new RussianTaxCalculator());
        taxCalculators.put(Country.GERMANY, new GermanyTaxCalculator());

        List<Citizen> citizens = readCitizensFromFile(CITIZEN_PATH);

        for (Citizen citizen : citizens) {
            TaxCalculator calculator = taxCalculators.get(citizen.getCountry());
            citizen.setTotalTax(calculator.calculateTax(citizen));
        }

        List<String> citizensStrings = citizens.stream().map(Citizen::toString).collect(Collectors.toList());

//        ReportWriter textReportWriter = new TextReportWriter(REPORT_TXT_FILE);
//        textReportWriter.writeReport(citizensStrings);
//
//        PdfReportWriter pdfReportWriter = new PdfReportWriter(PDF_TXT_FILE);
//        pdfReportWriter.writeReport(citizensStrings);




        TotalTaxCalculator totalTaxCalculator = new TotalTaxCalculator(currencyCalculator);

        CountryReport countryReport = totalTaxCalculator.calculateReport(citizens, Country.RUSSIA, RUB);

        PdfReportWriter pdfReportWriter = new PdfReportWriter(FINAL_PDF_REPORT);
        pdfReportWriter.writeReport(List.of(countryReport.toString()));

         countryReport = totalTaxCalculator.calculateReport(citizens, Country.RUSSIA, USD);

        pdfReportWriter = new PdfReportWriter(FINAL_PDF_REPORT_USD);
        pdfReportWriter.writeReport(List.of(countryReport.toString()));
    }


}
