package ru.juniortest.exchangemvp;

import ru.juniortest.net.FixerResponse;

interface ExchangeView {
    void showProgress();
    void hideProgress();
    void showExchangeList();
    void hideExchangeList();
    void onExchangeReceived(FixerResponse fixerResponse);
    void onError();
}
