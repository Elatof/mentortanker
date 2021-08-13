package com.korbiak.mentorship.multithreading.task5.model;

import com.korbiak.mentorship.multithreading.task5.utilities.Currency;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

@Data
public class UserAccount implements Serializable {

    private String name;
    private String secondaryName;
    private Map<Currency, BigDecimal> currencyValues = new EnumMap<>(Currency.class);

    public UserAccount(String name, String secondaryName) {
        this.name = name;
        this.secondaryName = secondaryName;
    }

    public UserAccount(String name, String secondaryName, Map<Currency, BigDecimal> currencyValues) {
        this.name = name;
        this.secondaryName = secondaryName;
        this.currencyValues = currencyValues;
    }
}
