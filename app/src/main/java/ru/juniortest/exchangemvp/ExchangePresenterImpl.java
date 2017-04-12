package ru.juniortest.exchangemvp;

import android.util.Log;

import ru.juniortest.net.FixerResponse;
import rx.Subscriber;
import rx.Subscription;

class ExchangePresenterImpl implements ExchangePresenter {
    private static final String TAG = ExchangePresenterImpl.class.getSimpleName();

    private ExchangeView exchangeView;
    private ExchangeModel exchangeModel;

    private Subscription subscription;

    ExchangePresenterImpl(ExchangeView exchangeView) {
        this.exchangeView = exchangeView;
        exchangeModel = new ExchangeModelImpl();
    }

    @Override
    public void requestExchange(String currencyCode) {
        exchangeView.hideExchangeList();
        exchangeView.showProgress();
        subscription = exchangeModel.getExchangeObservable(currencyCode)
                .subscribe(new Subscriber<FixerResponse>() {
                    @Override
                    public void onCompleted() {
                        exchangeView.hideProgress();
                        exchangeView.showExchangeList();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage(), e);
                        exchangeView.hideProgress();
                        exchangeView.onError();
                    }

                    @Override
                    public void onNext(FixerResponse fixerResponse) {
                        exchangeView.onExchangeReceived(fixerResponse);
                    }
                });
    }

    @Override
    public void onViewDestroy() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
        exchangeView = null;
    }
}
