package ru.juniortest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.juniortest.model.ExchangeRate;

public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.RateViewHolder> {
    private static final int RATE_VIEW_TYPE = 1;

    private List<ExchangeRate> exchangeRates = new ArrayList<>();

    @Override
    public int getItemViewType(int position) {
        return RATE_VIEW_TYPE;
    }

    @Override
    public RateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RATE_VIEW_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rate_item, parent, false);
            return new RateViewHolder(view);
        } else {
            throw new IllegalArgumentException("Unknown view type " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(RateViewHolder holder, int position) {
        ExchangeRate exchangeRate = exchangeRates.get(position);
        holder.currency.setText(exchangeRate.currency);
        String rateAsString = "" + exchangeRate.rate;
        holder.rate.setText(rateAsString);
    }

    @Override
    public int getItemCount() {
        return exchangeRates.size();
    }

    public void setExchangeRates(List<ExchangeRate> exchangeRates) {
        this.exchangeRates = exchangeRates;
        notifyDataSetChanged();
    }

    static class RateViewHolder extends RecyclerView.ViewHolder {
        final TextView currency;
        final TextView rate;

        RateViewHolder(View itemView) {
            super(itemView);
            currency = (TextView) itemView.findViewById(R.id.currency);
            rate = (TextView) itemView.findViewById(R.id.rate);
        }
    }
}
