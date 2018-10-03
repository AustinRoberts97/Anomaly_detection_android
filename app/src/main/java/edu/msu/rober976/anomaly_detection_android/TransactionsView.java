package edu.msu.rober976.anomaly_detection_android;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rober on 9/28/2018.
 */

public class TransactionsView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transactions_layout);

        /*ListView list = (ListView) findViewById(R.id.listTransactions);

        final Cloud.CatalogAdapter adapter = new Cloud.CatalogAdapter(list);

        list.setAdapter(adapter);
        */


    }
}
