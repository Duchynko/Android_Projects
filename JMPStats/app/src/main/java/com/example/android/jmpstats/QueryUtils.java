package com.example.android.jmpstats;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 3/7/2018.
 */

public class QueryUtils {

    /** Tag for the log messages */
    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /** Private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static methods, which can be accessed directly
     * from class. */
    private QueryUtils(){
    }

    private static List<Transaction> extractTransactionsFromJson(String transactionsJSON){
        if(TextUtils.isEmpty((transactionsJSON))) return null;

        List<Transaction> transactions = new ArrayList<>();

        try {
            JSONObject baseJasonResponse = new JSONObject(transactionsJSON);
            JSONObject paymentsObject = baseJasonResponse.getJSONObject("result");
            JSONArray transactionArray = paymentsObject.getJSONArray("payments");

            // For each earthquake in the transactionArray, create an {@link Earthquake} object
            for(int i=0; i<transactionArray.length(); i++){
                JSONObject currentTransaction = transactionArray.getJSONObject(i);
                String amount = currentTransaction.getString("amount");
                String fee = currentTransaction.getString("fee");
                String time = currentTransaction.getString("time");

                Transaction transaction = new Transaction(time, amount, fee);

                transactions.add(transaction);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return transactions;
    }

    private static Balance extractBalanceFromJson(String balanceJSON){
        //If the JSON String is empty, return early
        if(TextUtils.isEmpty(balanceJSON)) return null;

        Balance balance = null;

        //Try to parse NiceHash URL. If there's problem with the way JSON is formatted,
        // a JSONException object will be thrown.
        try{
            //Create a JSONObject from the balanceJSON string
            JSONObject baseJsonResponse = new JSONObject(balanceJSON);

            JSONObject result = baseJsonResponse.getJSONObject("result");
            String balanceCrypto = result.getString("balance_confirmed");
            String pendingCrypto = result.getString("balance_pending");

            balance = new Balance(balanceCrypto, pendingCrypto);

        }catch (JSONException e){
            Log.e("QueryUtils", "Problem parsing the balance JSON results", e);
        }
        return balance;
    }

    private static double[] extractRatesData(String ratesJSON){
        //If the JSON String is empty, return early
        if(TextUtils.isEmpty(ratesJSON)) return null;

        double[] extractedRates = new double[3];

        //Try to parse Blockchain URL (same as extractFromJson method)
        try{
            //Create a JSONObject from the balanceJSON string
            JSONObject baseJsonResponse = new JSONObject(ratesJSON);

            //Get EUR rates and add it into {@link rates} array.
            JSONObject eurJSON = baseJsonResponse.getJSONObject("EUR");
            double lastRateEur = eurJSON.getDouble("15m");
            extractedRates[0] = lastRateEur;

            //Get USD rates and add it into {@link rates} array.
            JSONObject usdJSON = baseJsonResponse.getJSONObject("USD");
            double lastRateUsd = usdJSON.getDouble("15m");
            extractedRates[1] = lastRateUsd;

            //Get EUR rates and add it into {@link rates} array.
            JSONObject dkkJSON = baseJsonResponse.getJSONObject("DKK");
            double lastRateDkk = dkkJSON.getDouble("15m");
            extractedRates[2] = lastRateDkk;

        }catch(JSONException e){
            Log.e("QueryUtils", "Problem getting last Bitcoin rates", e);
        }

        return extractedRates;
    }

    private static URL createUrl(String stringUrl){
        URL url = null;
        try{
            url = new URL(stringUrl);

        }catch(MalformedURLException e){
            Log.e(LOG_TAG, "Error with creating an URL");
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";

        if(url==null) return jsonResponse;

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try{
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(1000 /*milliseconds*/);
            httpURLConnection.setConnectTimeout(15000 /*milliseconds*/);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if(httpURLConnection.getResponseCode() == 200){
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }else Log.e(LOG_TAG, "Error response code: " + httpURLConnection.getResponseCode());
        }catch(IOException e){
            Log.e(LOG_TAG, "Problem retrieving Balance JSON results");
        }finally {
            if(httpURLConnection != null) httpURLConnection.disconnect();
            if(inputStream != null) inputStream.close();
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line != null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static List<Transaction> fetchTransactionsData(String requestUrl){
        URL url = createUrl(requestUrl);

        String jsonResponse = null;

        //Perform HTTP request to the URL and receive JSON response back
        try{
            jsonResponse = makeHttpRequest(url);
        }catch(IOException e){
            Log.e(LOG_TAG, "Problem making the HTTP request", e);
        }

        List<Transaction> transactions = extractTransactionsFromJson(jsonResponse);

        return transactions;
    }


    public static Balance fetchBalanceData(String requestUrl){
        URL url = createUrl(requestUrl);

        String jsonResponse = null;

        //Perform HTTP request to the URL and receive JSON response back
        try{
            jsonResponse = makeHttpRequest(url);
        }catch(IOException e){
            Log.e(LOG_TAG, "Problem making the HTTP request", e);
        }

        Balance balance = extractBalanceFromJson(jsonResponse);

        return balance;
    }

    public static double[] fetchRatesData(String requestUrl){
        URL url = createUrl(requestUrl);

        String jsonResponse = null;

        //Perform HTTP request to the URL and receive JSON response back
        try{
            jsonResponse = makeHttpRequest(url);
        }catch(IOException e){
            Log.e(LOG_TAG, "Problem making the HTTP request", e);
        }

        double[] rates = extractRatesData(jsonResponse);

        return rates;
    }

}
