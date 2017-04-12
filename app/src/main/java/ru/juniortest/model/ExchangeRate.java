package ru.juniortest.model;

import java.io.Serializable;

public class ExchangeRate implements Serializable {
    public final String currency;
    public final double rate;

    public ExchangeRate(String currency, double rate) {
        this.rate = rate;
        this.currency = currency;
    }
}
