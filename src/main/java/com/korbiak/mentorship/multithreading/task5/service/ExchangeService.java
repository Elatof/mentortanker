package com.korbiak.mentorship.multithreading.task5.service;

import com.korbiak.mentorship.multithreading.task5.utilities.ExchangeType;

import java.math.BigDecimal;

public interface ExchangeService {

    void exchangeCurrency(String userName, String userSurname, ExchangeType exchangeType, BigDecimal value);
}
