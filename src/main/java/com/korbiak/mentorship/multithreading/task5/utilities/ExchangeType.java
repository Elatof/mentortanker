package com.korbiak.mentorship.multithreading.task5.utilities;

public enum ExchangeType {
    UAH_TO_USD(Currency.UAH, Currency.USD),
    UAH_TO_EUR(Currency.UAH, Currency.EUR),
    USD_TO_UAH(Currency.USD, Currency.UAH),
    EUR_TO_UAH(Currency.EUR, Currency.UAH);

    private final Currency from;
    private final Currency to;

    ExchangeType(Currency from, Currency to) {
        this.from = from;
        this.to = to;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }
}
