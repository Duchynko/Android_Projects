package com.example.android.jmpstats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jakub on 3/18/2018.
 */

public class TransactionAdapter extends ArrayAdapter<Transaction> {

    public TransactionAdapter(Context context, List<Transaction> transactions){
        super(context, 0, transactions);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Transaction currentTransaction = getItem(position);

        Date dateObject = new Date(Long.valueOf(currentTransaction.getDate())*1000);

        TextView transactionDate = (TextView) listItemView.findViewById(R.id.transaction_date);
        String formattedDate = formatDate(dateObject);
        transactionDate.setText(formattedDate);

        TextView transactionAmount = (TextView) listItemView.findViewById(R.id.transaction_amount);
        transactionAmount.setText(currentTransaction.getPayment());

        TextView transactionFee = (TextView) listItemView.findViewById(R.id.transaction_fee);
        transactionFee.setText(currentTransaction.getFee());

        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy' 'HH:mm");
        return dateFormat.format(dateObject);
    }

}
