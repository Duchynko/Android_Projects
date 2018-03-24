package com.example.android.jmpstats;

import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String BLOCKCHAIN_REQUEST_URL = "https://blockchain.info/ticker";
    public static final String NICEHASH_REQUEST_URL = "https://api.nicehash.com/api?method=balance&id=843348&key=6e18fa04-c419-35c5-fc84-2dd71f4e7629";
    public static final String NICEHASH_TRANSACTIONS_URL = "https://api.nicehash.com/api?method=stats.provider.payments&addr=3DiDQXJepRfBeMjb6qqHvwhXiaVK3qu8FV";

    private TextView balanceTV;
    private TextView pendingTV;
    private TextView actionBar;
    private TextView toEuroTV;
    private TextView toUsdTV;
    private TextView toDkkTV;
    private TransactionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView transactionListView = (ListView) findViewById(R.id.transaction_lv);
         mAdapter = new TransactionAdapter(this, new ArrayList<Transaction>());
         transactionListView.setAdapter(mAdapter);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
        actionBar = findViewById(R.id.abs_textview);
        actionBar.setText("Balance");

        balanceTV = findViewById(R.id.balance_bitcoin_tv);
        toEuroTV = findViewById(R.id.balance_euro_tv);
        toUsdTV = findViewById(R.id.balance_usd_tv);
        toDkkTV = findViewById(R.id.balance_dkk_tv);

        BalanceAsyncTask taskB = new BalanceAsyncTask();
        taskB.execute(NICEHASH_REQUEST_URL, BLOCKCHAIN_REQUEST_URL, NICEHASH_TRANSACTIONS_URL);


    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private class BalanceAsyncTask extends AsyncTask<String, Void, Result>{

        /**
         * This method runs on a background thread and performs the network request.
         * We should not update the UI from a background thread, so we return a
         * {@link Balance} as the result.
         */
        @Override
        protected Result doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            mAdapter.clear();

            Balance balance = QueryUtils.fetchBalanceData(urls[0]);
            double[] rates = QueryUtils.fetchRatesData(urls[1]);
            List<Transaction> transactions = QueryUtils.fetchTransactionsData(urls[2]);

            return new Result(balance, rates, transactions);
        }

        @Override
        protected void onPostExecute(Result result) {
            if(result != null){
                balanceTV.setText(result.getBalance().getBalanceCrypto());

                double euro = round(result.getRates()[0]*Double.parseDouble(result.getBalance().getBalanceCrypto()), 2);
                double usd = round(((result.getRates()[1])*Double.parseDouble(result.getBalance().getBalanceCrypto())), 2);
                double dkk = round(((result.getRates()[2])*Double.parseDouble(result.getBalance().getBalanceCrypto())), 2);
                toEuroTV.setText(String.valueOf(euro) + " €");
                toUsdTV.setText(String.valueOf (usd) + " $");
                toDkkTV.setText(String.valueOf(dkk) + " Kr");
                mAdapter.addAll(result.getTransactions());
            }
        }
    }
}
