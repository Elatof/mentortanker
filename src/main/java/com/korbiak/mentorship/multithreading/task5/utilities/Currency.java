package com.korbiak.mentorship.multithreading.task5.utilities;

public enum Currency {

    USD(1.0),
    EUR(1.18),
    UAH(0.037);

    private final double rate;

    Currency(double value) {
        this.rate = value;
    }

    public double getRate() {
        return rate;
    }
}
