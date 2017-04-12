package ru.juniortest.net;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

public class FixerRestClient {
    private static final class FixerRestClientHolder {
        static FixerRestClient instance = new FixerRestClient();
    }

    public static FixerRestClient getInstance() {
        return FixerRestClientHolder.instance;
    }

    private FixerService fixerService;

    private FixerRestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        fixerService = retrofit.create(FixerService.class);
    }

     public Observable<FixerResponse> getLatestExchange(String base) {
         return fixerService.getLatestExchange(base)
                 .subscribeOn(Schedulers.io());
     }
}
