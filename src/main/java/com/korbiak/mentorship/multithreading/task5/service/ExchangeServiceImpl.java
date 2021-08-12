package com.korbiak.mentorship.multithreading.task5.service;

import com.korbiak.mentorship.multithreading.task5.dao.UserDao;
import com.korbiak.mentorship.multithreading.task5.dao.UserDaoImpl;
import com.korbiak.mentorship.multithreading.task5.except.ExchangeException;
import com.korbiak.mentorship.multithreading.task5.model.UserAccount;
import com.korbiak.mentorship.multithreading.task5.utilities.Currency;
import com.korbiak.mentorship.multithreading.task5.utilities.ExchangeType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Objects;

@Data
@Slf4j
public class ExchangeServiceImpl implements ExchangeService {

    private final UserDao userDao;

    public ExchangeServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public synchronized void exchangeCurrency(String userName, String userSurname, ExchangeType exchangeType, BigDecimal value) {
        log.info("Start exchange currency for user: {} {}, with params: {}, {} | {}", userName, userSurname, exchangeType, value, Thread.currentThread());
        UserAccount targetUser = userDao.getUser(userName, userSurname);
        if (Objects.isNull(targetUser)) {
            log.error("User not found | {}", Thread.currentThread());
            throw new ExchangeException("Target user not found");
        }

        Map<Currency, BigDecimal> currencyValues = targetUser.getCurrencyValues();
        BigDecimal fromCurrency = currencyValues.get(exchangeType.getFrom());
        BigDecimal toCurrency = currencyValues.get(exchangeType.getTo());
        if (Objects.isNull(fromCurrency) || Objects.isNull(toCurrency)) {
            log.error("User dont have some type of currency | {}", Thread.currentThread());
            throw new ExchangeException("Currency value of is null");
        }

        if (fromCurrency.compareTo(value) < 0) {
            log.error("Not enough money | {}", Thread.currentThread());
            throw new ExchangeException("Input value less than current (Not enough money)");
        }
        currencyValues.put(exchangeType.getFrom(), fromCurrency.subtract(value));

        BigDecimal exchangeResult = value
                .multiply(BigDecimal.valueOf(exchangeType.getFrom().getRate()))
                .divide(BigDecimal.valueOf(exchangeType.getTo().getRate()), RoundingMode.CEILING)
                .add(toCurrency);
        currencyValues.put(exchangeType.getTo(), exchangeResult);

        userDao.saveUser(targetUser);
        log.info("End exchange currency for user: {} {}, with params: {}, {} | {}", userName, userSurname, exchangeType, value, Thread.currentThread());
    }
}
