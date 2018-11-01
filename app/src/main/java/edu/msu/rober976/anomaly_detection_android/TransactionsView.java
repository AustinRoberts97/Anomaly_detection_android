package edu.msu.rober976.anomaly_detection_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by rober on 9/28/2018.
 */

public class TransactionsView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transactions_layout);

        ListView list = (ListView) findViewById(R.id.listTransactions);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String account_number = (String) b.get("account");
        Log.d("account transview",account_number);
        final Cloud.CatalogAdapter adapter = new Cloud.CatalogAdapter(list, account_number);

        list.setAdapter(adapter);
        LocalDate date = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = LocalDate.now();
        }
        LocalTime time = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            time = LocalTime.now();
        }
        TextView TimeBox = (TextView) findViewById(R.id.TimeBox);
        TimeBox.setText("TRANSACTION HISTORY as of " +time.toString()+ " on "+date.toString());

    }
}
