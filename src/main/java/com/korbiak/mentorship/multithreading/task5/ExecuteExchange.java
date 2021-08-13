package com.korbiak.mentorship.multithreading.task5;

import com.korbiak.mentorship.multithreading.task5.dao.UserDao;
import com.korbiak.mentorship.multithreading.task5.dao.UserDaoImpl;
import com.korbiak.mentorship.multithreading.task5.model.UserAccount;
import com.korbiak.mentorship.multithreading.task5.service.ExchangeService;
import com.korbiak.mentorship.multithreading.task5.service.ExchangeServiceImpl;
import com.korbiak.mentorship.multithreading.task5.utilities.Currency;
import com.korbiak.mentorship.multithreading.task5.utilities.ExchangeType;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ExecuteExchange {

    private final UserDao userDao;
    private final ExchangeService exchangeService;
    private static final String USER_NAME = "Maksym";
    private static final String USER_SURNAME = "Korbiak";

    public ExecuteExchange() {
        this.userDao = new UserDaoImpl();
        this.exchangeService = new ExchangeServiceImpl();
    }

    public void execute() {
        //generate test data
        Map<Currency, BigDecimal> currencyBigDecimalMap = new EnumMap<>(Currency.class);
        currencyBigDecimalMap.put(Currency.USD, BigDecimal.valueOf(1234.3));
        currencyBigDecimalMap.put(Currency.EUR, BigDecimal.valueOf(44.3));
        currencyBigDecimalMap.put(Currency.UAH, BigDecimal.valueOf(666.72));
        userDao.saveUser(new UserAccount(USER_NAME, USER_SURNAME, currencyBigDecimalMap));

        currencyBigDecimalMap.put(Currency.USD, BigDecimal.valueOf(124.3));
        currencyBigDecimalMap.put(Currency.EUR, BigDecimal.valueOf(4532.3));
        currencyBigDecimalMap.put(Currency.UAH, BigDecimal.valueOf(43222.72));
        userDao.saveUser(new UserAccount("Roman", "Korb", currencyBigDecimalMap));

        currencyBigDecimalMap.put(Currency.USD, BigDecimal.valueOf(1277.3));
        currencyBigDecimalMap.put(Currency.EUR, BigDecimal.valueOf(2232.3));
        currencyBigDecimalMap.put(Currency.UAH, BigDecimal.valueOf(45.72));
        userDao.saveUser(new UserAccount("Victor", "Seniv", currencyBigDecimalMap));


        Runnable runnable1 = () -> exchangeService.exchangeCurrency(USER_NAME, USER_SURNAME, ExchangeType.EUR_TO_UAH, BigDecimal.valueOf(12));
        Runnable runnable2 = () -> exchangeService.exchangeCurrency(USER_NAME, USER_SURNAME, ExchangeType.EUR_TO_UAH, BigDecimal.valueOf(12));
        Runnable runnable3 = () -> exchangeService.exchangeCurrency(USER_NAME, USER_SURNAME, ExchangeType.UAH_TO_USD, BigDecimal.valueOf(1300));

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(runnable1);
        executorService.submit(runnable2);
        executorService.submit(runnable3);


        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            log.error("InterruptedException:{}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        UserAccount result = userDao.getUser(USER_NAME, USER_SURNAME);
        log.info("-------------------------------");
        log.info("Saved updated user: {}", result);
    }
}
