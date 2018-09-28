package edu.msu.rober976.anomaly_detection_android;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button click;
    TextView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click = (Button)findViewById(R.id.button);
        data = (TextView)findViewById(R.id.data);
        final Handler handler = new Handler();
        List<Transaction> transactionList = null;

        Intent intent = new Intent(this, TransactionsView.class);
        this.startActivity(intent);
        finish();
    }

}
