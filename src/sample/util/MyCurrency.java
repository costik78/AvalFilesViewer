package sample.util;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by conti on 23.11.2016.
 */
public class MyCurrency {
    private static Set<Currency> currencies;

    private static void init() {
        currencies = Currency.getAvailableCurrencies();
    }

    public static String getSymbol(int code) {
        if(currencies == null) {
            init();
        }

        Currency currency = null;
        try {
            currency = currencies.stream().filter(e -> e.getNumericCode() == code).findFirst().get();
        } catch (NoSuchElementException | NullPointerException e) {
           e.getStackTrace();
        }

        return (currency == null ? "" : currency.getSymbol());
    }

    public static int getCode(String r030) {
        if(currencies == null) {
            init();
        }

        Currency currency = null;
        try {
            if( Pattern.matches("[+-]?\\d+\\.?\\d+", r030) ) {
                int code = Integer.parseInt(r030);
                currency = currencies.stream().filter(e -> e.getNumericCode() == code).findFirst().get();
            } else {
                currency = currencies.stream().filter(e -> e.getSymbol().startsWith(r030)).findFirst().get();
            }
        } catch (NoSuchElementException | NullPointerException e) {
            e.getStackTrace();
        }


        return currency == null ? 0 : currency.getNumericCode();
    }
}
