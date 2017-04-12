package ru.juniortest.exchangemvp;

interface ExchangePresenter {
    void requestExchange(String currencyCode);
    void onViewDestroy();
}
