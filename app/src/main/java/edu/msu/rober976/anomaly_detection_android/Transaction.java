package edu.msu.rober976.anomaly_detection_android;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rober on 9/27/2018.
 */

// Class to reflect Transaction data model
public class Transaction {
    private String processor_account;
    private String our_account;
    private String post_amount;
    private boolean post_success;
    private boolean hold_action;
    private boolean duplicate_flag;
    private String transaction_code;
    private String auth_id_response;
    private String trace_number;
    private String reference_number;
    private String local_tran_date;
    private String local_tran_time;
    private String device_type;
    private String reversal_flag;
    private String adjustment_flag;
    private String original_amount;
    private String tran_sub_type;
    private String our_preauth_code;
    private String our_tran_code;
    private String our_transmission_date;
    private String our_transmission_time;
    private String cash_back_amount;
    private String card_acceptor_city;
    private String card_acceptor_country;
    private String card_acceptor_id_code;
    private String card_acceptor_name;
    private String card_acceptor_state;
    private String card_acceptor_street;
    private boolean fraud_flag;

    public Transaction(JSONObject json) {
        try {
            processor_account = (String)json.get("processor_account");
            our_account = (String)json.get("our_account");
            post_amount = (String)json.get("post_amount");
            post_success = (Boolean)json.get("post_success");
            hold_action = (Boolean)json.get("hold_action");
            duplicate_flag = (Boolean)json.get("duplicate_flag");
            transaction_code = (String)json.get("transaction_code");
            auth_id_response = (String)json.get("auth_id_response");
            trace_number = (String)json.get("trace_number");
            reference_number = (String)json.get("reference_number");
            local_tran_date = (String)json.get("local_tran_date");
            local_tran_time = (String)json.get("local_tran_time");
            device_type = (String)json.get("device_type");
            card_acceptor_name = (String)json.get("card_acceptor_name");
            card_acceptor_state = (String)json.get("card_acceptor_state");
            card_acceptor_street = (String)json.get("card_acceptor_street");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getOur_transmission_date() {
        return our_transmission_date;
    }

    public String getLocal_tran_time() {
        return local_tran_time;
    }

    public String getPost_amount() {
        return post_amount;
    }

    public boolean isPost_success() {
        return post_success;
    }
}
