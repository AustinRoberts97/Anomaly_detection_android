package edu.msu.rober976.anomaly_detection_android;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Message;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private static final String ACCOUNT_NUMBER="3243617280";

    private static class Item {
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
        public CatalogAdapter(final View view) {
            // Create a thread to load the catalog
            new Thread(new Runnable() {

                @Override
                public void run() {
                    ArrayList<Item> newItems = get_transactions();
                    if(newItems != null) {

                        items = newItems;

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
        private ArrayList<Item> items = new ArrayList<Item>();


    public ArrayList<Item> get_transactions() {
        // create a get query
        String query = BASE_URL + API_URL + TRANSACTION_URL+ ACCOUNT_NUMBER;
        String data = "";
        String dataParsed = "";
        String singleParsed = "";
        ArrayList<Item> transactions = new ArrayList<Item>();

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
                }
                JSONArray ar = new JSONArray(data);

                for (int i =0; i < ar.length(); i++) {
                    JSONObject transJson = (JSONObject) ar.get(i);

                    Item item = new Item();
                    item.acceptor = transJson.getString("card_acceptor_name");
                    item.amount = transJson.getString("post_amount");
                    item.state = transJson.getString("card_acceptor_state");
                    item.success = transJson.getBoolean("post_success");
                    item.fraud_flag = transJson.getInt("fraud_flag");
                    if(item != null && item.success && !transactions.contains(item)) {
                        transactions.add(item);
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

        return transactions;
    }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
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
            tv.setText(items.get(i).acceptor);
            tv.setHeight(250);
            Log.e(TAG, items.get(i).acceptor );
            TextView tv2 = (TextView)view.findViewById(R.id.amount);
            tv2.setText(items.get(i).amount);
            tv2.setHeight(250);
            Log.e(TAG, items.get(i).amount);
            TextView tv3 = (TextView)view.findViewById(R.id.fraud_flag);
            tv3.setText("");
            tv3.setHeight(250);
            RelativeLayout RL = (RelativeLayout) tv.getParent();
            RL.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    String acc = items.get(i).acceptor;
                    Intent intent = new Intent(v.getContext(), Individual_Trans.class);
                    intent.putExtra("Acceptor", acc);
                    v.getContext().startActivity(intent);
                }
            });
            if (items.get(i).fraud_flag == 1){
                tv3.setText("!");
                RL.setBackgroundColor(Color.parseColor("#BB4F4E"));
            }
            else{
                RL.setBackgroundColor(000000);
            }

            Log.e(TAG, items.get(i).fraud_flag.toString());
            return view;
        }


    }


}

