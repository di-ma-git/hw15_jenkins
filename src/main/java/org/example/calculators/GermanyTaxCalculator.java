package org.example.calculators;

import org.example.model.Citizen;

public class GermanyTaxCalculator implements TaxCalculator {
    @Override
    public Double calculateTax(Citizen citizen) {
        Integer salary = citizen.getSalary();
        double isFemaleMultiplier = !citizen.isMale() ? 0.97 : 1;
        if(salary < 1000 && citizen.isDisabled()) {
            return 0d;
        }
        if (salary < 1200) {
            return citizen.getMonthOfWork() * citizen.getMonthOfWork() * isFemaleMultiplier * 0.10d;
        }
        if (salary < 2000) {
            return citizen.getMonthOfWork() * citizen.getMonthOfWork() * isFemaleMultiplier * 0.12d;
        }
        return citizen.getMonthOfWork() * citizen.getMonthOfWork() * isFemaleMultiplier * 0.35d;
    }
}
