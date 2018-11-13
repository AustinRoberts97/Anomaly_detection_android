package edu.msu.rober976.anomaly_detection_android;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Message;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static edu.msu.rober976.anomaly_detection_android.R.color.colorPrimaryDark;
import static edu.msu.rober976.anomaly_detection_android.R.color.urgent;

/**
 * Created by rober on 9/27/2018.
 */

public class Cloud {

    private static final String BASE_URL = "http://django-env.zqqwi3vey2.us-east-1.elasticbeanstalk.com";
    private static final String API_URL = "/api";
    private static final String TRANSACTION_URL = "/transactions/?account=";
    private static String ACCOUNT_NUMBER="";
    //http://django-env.zqqwi3vey2.us-east-1.elasticbeanstalk.com/api/transactions/?account=3243617280

    private static class Item {
        public String id ="";
        public String amount = "";
        public String acceptor = "";
        public String state = "";
        public Boolean success = Boolean.TRUE;
        public Integer fraud_flag = 0;
    }
    /**
     * An adapter so that list boxes can display a list of filenames from
     * the cloud server.
     */
    public static class CatalogAdapter extends BaseAdapter {


        /**
         * Constructor
         */
        public CatalogAdapter(final View view, String accnum, Integer card) {
            ACCOUNT_NUMBER = accnum;
            final Integer CARD_NUMBER = card;
            Log.d("account cloud",ACCOUNT_NUMBER);
            // Create a thread to load the catalog
            new Thread(new Runnable() {

                @Override
                public void run() {
                    ArrayList<Transaction> newItems = get_transactions(CARD_NUMBER);
                    if(newItems != null) {

                        transactions = newItems;

                        view.post(new Runnable() {

                            @Override
                            public void run() {
                                // Tell the adapter the data set has been changed
                                notifyDataSetChanged();
                            }

                        });
                    }else {
                        // Error condition!
                        view.post(new Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(view.getContext(), R.string.catalog_fail, Toast.LENGTH_SHORT).show();
                            }

                        });
                    }
                }
            }).start();
        }
        /**
         * The items we display in the list box. Initially this is
         * null until we get items from the server.
         */
        private ArrayList<Transaction> transactions = new ArrayList<Transaction>();


    //Returns transactions
    public ArrayList<Transaction> get_transactions(Integer CARD_NUMBER) {
        // create a get query
        String query = BASE_URL + API_URL + TRANSACTION_URL+ ACCOUNT_NUMBER;
        String data = "";
        Log.d("query",query);
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        ArrayList<String> Cards = new ArrayList<String>();
        try {
            URL url = new URL(query);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            int responseCode = conn.getResponseCode();
            if(responseCode != HttpURLConnection.HTTP_OK) {
                Log.e("login", "Connection Failed");
            }

            InputStream stream = conn.getInputStream();

            // Create JSON parser for result
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String line = "";
                while (line != null) {
                    line = reader.readLine();
                    data = data + line;
                    Log.d("data",data);
                }
                JSONArray ar = new JSONArray(data);

                for (int i =0; i < ar.length(); i++) {
                    JSONObject transJson = (JSONObject) ar.get(i);


                    Transaction transaction = new Transaction(transJson);

                    //if(transaction != null && transaction.isPost_success() && !transactions.contains(transaction)) {
                    if(transaction != null && !transactions.contains(transaction)) {
                        transactions.add(transaction);

                    }

                    if(!Cards.contains(transaction.getProcessor_account())) {
                        Cards.add(transaction.getProcessor_account());
                    }


                }

            } catch (JSONException e) {
                e.printStackTrace();
            } finally {

            }
        } catch (MalformedURLException e) {
            Log.e(e.getMessage(), "error");
        } catch (IOException e) {
            Log.e(e.getMessage(), "error");
        }


        //filters cards
        ArrayList<Transaction> transactions1;
        transactions1 = new ArrayList<Transaction>();
        for (int i =0; i < transactions.size(); i++) {
            if (transactions.get(i).getProcessor_account().equals(Cards.get(CARD_NUMBER))) {
                transactions1.add(transactions.get(i));
            }
        }


        return transactions1;

    }

        @Override
        public int getCount() {
            return transactions.size();
        }

        @Override
        public Object getItem(int i) {
            return transactions.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if(view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.catalog_item, viewGroup, false);
            }

            TextView tv = (TextView)view.findViewById(R.id.acceptor);
            tv.setText(transactions.get(i).getCard_acceptor_name());
            Log.e(TAG, transactions.get(i).getCard_acceptor_name() );
            TextView tv2 = (TextView)view.findViewById(R.id.amount);
            tv2.setText("$"+String.format("%.2f",transactions.get(i).getPost_amount()));
            Log.e(TAG, transactions.get(i).getPost_amount().toString());
            TextView tv3 = (TextView)view.findViewById(R.id.fraud_flag);
            tv3.setText("");
            RelativeLayout RL = (RelativeLayout) tv.getParent();
            RL.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Transaction tran = transactions.get(i);
                    Intent intent = new Intent(v.getContext(), Individual_Trans.class);
                    intent.putExtra("Transaction", tran);
                    v.getContext().startActivity(intent);
                }
            });
            if (transactions.get(i).getFraud_flag() == 3){
                tv3.setText("!");
                RL.setBackgroundColor(Color.parseColor("#DD8B8A"));
            }
            else if (transactions.get(i).getFraud_flag() == 1){
                tv3.setText("");
                RL.setBackgroundColor(Color.parseColor("#FFE4C4"));
            }
            else{
                tv3.setText("");
                RL.setBackgroundColor(000000);
            }

            Log.e(TAG, transactions.get(i).getFraud_flag().toString());
            return view;
        }




    }


}

