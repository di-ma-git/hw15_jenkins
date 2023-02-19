package org.example.model;

public enum Country {
    GERMANY(Currency.EUR), UK(Currency.GBP), CANADA(Currency.USD), RUSSIA(Currency.RUB);
    Currency currency;

    public Currency getCurrency() {
        return currency;
    }

    Country(Currency currency) {
        this.currency = currency;
    }
}
