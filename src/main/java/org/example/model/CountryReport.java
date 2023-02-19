package org.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryReport {
    Country country;
    Double totalTax;
    Double totalAverageTax;
    Currency currency;
    int citizensNumber;
}
