package edu.msu.rober976.anomaly_detection_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import android.support.v4.widget.DrawerLayout;
import android.support.design.widget.NavigationView;

/**
 * Created by rober on 9/28/2018.
 */

public class TransactionsView extends AppCompatActivity {
    private NavigationView mNavView;
    private String account_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transactions_layout);
        mNavView = findViewById(R.id.nav_view);


        ListView list = (ListView) findViewById(R.id.listTransactions);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        account_number = (String) b.get("account");
        Integer card_number = (Integer) b.get("card");
        //Log.d("account transview",account_number);

        //creates a new cloud cataglog adapter
        final Cloud.CatalogAdapter adapter = new Cloud.CatalogAdapter(list, account_number, card_number);

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


        Menu menu = mNavView.getMenu();
        MenuItem mItem0 = menu.findItem(R.id.card0);
        MenuItem mItem1 = menu.findItem(R.id.card1);

        //sets on click listener
        mItem0.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),TransactionsView.class);
                intent.putExtra("account",account_number);
                intent.putExtra("card",0);
                startActivity(intent);
                return true;
            }
        });
        //sets menu clicking
        mItem1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),TransactionsView.class);
                intent.putExtra("account",account_number);
                intent.putExtra("card",1);
                startActivity(intent);
                return true;
            }
        });
    }



}
