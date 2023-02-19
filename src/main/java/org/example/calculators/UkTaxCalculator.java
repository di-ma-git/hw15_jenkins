package org.example.calculators;

import org.example.model.Citizen;

public class UkTaxCalculator implements TaxCalculator {
    @Override
    public Double calculateTax(Citizen citizen) {
        Integer salary = citizen.getSalary();
        boolean hasDisability = citizen.isDisabled();
        int consideredMonthOfWork = hasDisability ?
                Math.max(citizen.getMonthOfWork() - 3, 0) : citizen.getMonthOfWork();
        if (salary < 1200) {
            return citizen.getMonthOfWork() * consideredMonthOfWork * 0.0d;
        }
        if (salary < 2000) {
            return citizen.getMonthOfWork() * consideredMonthOfWork * 0.20d;
        }
        return citizen.getMonthOfWork() * consideredMonthOfWork * 0.30d;
    }
}
