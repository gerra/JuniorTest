package ru.juniortest.exchangemvp;

import ru.juniortest.net.FixerResponse;
import ru.juniortest.net.FixerRestClient;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

class ExchangeModelImpl implements ExchangeModel {

    private FixerRestClient fixerRestClient;

    ExchangeModelImpl() {
        fixerRestClient = FixerRestClient.getInstance();
    }

    @Override
    public Observable<FixerResponse> getExchangeObservable(String currencyCode) {
        return fixerRestClient.getLatestExchange(currencyCode)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
