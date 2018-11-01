package edu.msu.rober976.anomaly_detection_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

public class Individual_Trans extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual);


        Intent intent = getIntent();
        Transaction transaction = intent.getParcelableExtra("Transaction");

        TextView Location = findViewById(R.id.amount2);
        Location.setText("$"+String.format("%.2f",transaction.getPost_amount()));


    }

}
