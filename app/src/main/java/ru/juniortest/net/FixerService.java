package ru.juniortest.net;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

interface FixerService {
    @GET("/latest")
    Observable<FixerResponse> getLatestExchange(@Query("base") String currency);
}
