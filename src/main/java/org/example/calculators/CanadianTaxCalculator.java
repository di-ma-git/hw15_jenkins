package org.example.calculators;

import org.example.model.Citizen;

public class CanadianTaxCalculator implements TaxCalculator {
    @Override
    public Double calculateTax(Citizen citizen) {
        Integer salary = citizen.getSalary();
        double disabilityMultiplier = citizen.isDisabled() ? 1d : 0.95d;
        if (salary < 1200) {
            return citizen.getMonthOfWork() * citizen.getMonthOfWork() * disabilityMultiplier * 0.10d;
        }
        if (salary < 2000) {
            return citizen.getMonthOfWork() * citizen.getMonthOfWork() * disabilityMultiplier * 0.12d;
        }
        return citizen.getMonthOfWork() * citizen.getMonthOfWork() * disabilityMultiplier * 0.20d;
    }
}
