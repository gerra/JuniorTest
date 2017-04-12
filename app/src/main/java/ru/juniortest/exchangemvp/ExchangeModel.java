package ru.juniortest.exchangemvp;

import ru.juniortest.net.FixerResponse;
import rx.Observable;

interface ExchangeModel {
    Observable<FixerResponse> getExchangeObservable(String currencyCode);
}
