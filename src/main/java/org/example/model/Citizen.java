package org.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Citizen {
    private String name;
    private String lastName;
    private Country country;
    private Integer salary;
    private Currency currency;
    private Integer monthOfWork;
    private boolean isMale;
    private boolean isDisabled;
    private Double totalTax;

    @Override
    public String toString() {
        return String.join(",", name, lastName, String.valueOf(country), String.valueOf(salary),
                String.valueOf(currency), String.valueOf(monthOfWork), isMale ? "Male" : "Female", isDisabled ? "Yes" : "No") + ":" + totalTax;
    }

}
