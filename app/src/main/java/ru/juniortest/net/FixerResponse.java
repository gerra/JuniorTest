package ru.juniortest.net;

import com.google.gson.annotations.SerializedName;

import java.util.Currency;
import java.util.Date;
import java.util.Map;

@SuppressWarnings("unused")
public class FixerResponse {
    @SerializedName("base")
    private Currency base;
    @SerializedName("date")
    private Date date;
    @SerializedName("rates")
    private Map<String, Double> rates;

    public Currency getBase() {
        return base;
    }

    public Date getDate() {
        return date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}
