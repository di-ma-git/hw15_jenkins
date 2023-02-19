package org.example.calculators;

import org.example.model.Citizen;

public class RussianTaxCalculator implements TaxCalculator {
    @Override
    public Double calculateTax(Citizen citizen) {
        return citizen.getMonthOfWork() * citizen.getSalary() * 0.13;
    }
}
