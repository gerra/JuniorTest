package ru.juniortest.exchangemvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ru.juniortest.ExchangeAdapter;
import ru.juniortest.R;
import ru.juniortest.model.ExchangeRate;
import ru.juniortest.net.FixerResponse;

public class ExchangeFragment extends Fragment implements ExchangeView {

    private static final String TAG = ExchangeFragment.class.getSimpleName();

    public static ExchangeFragment newInstance() {
        return new ExchangeFragment();
    }

    private ExchangePresenter presenter;

    private ExchangeAdapter exchangeAdapter;
    private RecyclerView ratesRecyclerView;
    private View progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() " + savedInstanceState);
        setRetainInstance(true);
        exchangeAdapter = new ExchangeAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        final EditText getRatesEditText = (EditText) view.findViewById(R.id.ratesEditText);
        view.findViewById(R.id.getRates).setOnClickListener(v -> {
            String currency = getRatesEditText.getText().toString();
            presenter.requestExchange(currency);
        });
        progressBar = view.findViewById(R.id.progressBar);
        ratesRecyclerView = (RecyclerView) view.findViewById(R.id.ratesList);
        ratesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));
        ratesRecyclerView.setAdapter(exchangeAdapter);
        presenter = new ExchangePresenterImpl(this);
        return view;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onViewDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showExchangeList() {
        ratesRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideExchangeList() {
        ratesRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onExchangeReceived(FixerResponse fixerResponse) {
        Map<String, Double> rates = fixerResponse.getRates();
        List<ExchangeRate> exchangeRates = new ArrayList<>();
        for (Map.Entry<String, Double> entry : rates.entrySet()) {
            exchangeRates.add(new ExchangeRate(entry.getKey(), entry.getValue()));
        }
        exchangeAdapter.setExchangeRates(exchangeRates);
    }

    @Override
    public void onError() {
        exchangeAdapter.setExchangeRates(Collections.emptyList());
    }
}
