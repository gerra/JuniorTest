package ru.juniortest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.juniortest.R;
import ru.juniortest.exchangemvp.ExchangeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content, ExchangeFragment.newInstance())
                    .commit();
        }
    }
}
