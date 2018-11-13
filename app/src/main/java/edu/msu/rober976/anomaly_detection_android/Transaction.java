package edu.msu.rober976.anomaly_detection_android;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rober on 9/27/2018.
 */

// Class to reflect Transaction data model
public class Transaction implements Parcelable{
    private Integer id;
    private String processor_account;
    private String our_account;
    private Double post_amount;
    private boolean post_success;
    private Integer hold_action;
    private boolean duplicate_flag;
    private Integer transaction_code;
    private Integer auth_id_response;
    private Integer trace_number;
    private String reference_number;
    private String local_tran_date;
    private String local_tran_time;
    private Boolean device_type;
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
    private Integer fraud_flag;

    public Transaction(JSONObject json) {
        try {
            id = (Integer)json.get("id");
            processor_account = (String)json.get("processor_account");
            our_account = json.get("our_account").toString();
            post_amount = (Double) json.get("post_amount");
            post_success = (Boolean)json.get("post_success");
            hold_action = (Integer)json.get("hold_action");
            duplicate_flag = (Boolean)json.get("duplicate_flag");
            transaction_code = (Integer)json.get("transaction_code");
            auth_id_response = (Integer)json.get("auth_id_response");
            trace_number = (Integer)json.get("trace_number");
            reference_number = (String)json.get("reference_number");
            local_tran_date = (String)json.get("local_tran_date");
            local_tran_time = (String)json.get("local_tran_time");
            device_type = (Boolean)json.get("device_type");
            card_acceptor_name = (String)json.get("card_acceptor_name");
            card_acceptor_state = (String)json.get("card_acceptor_state");
            card_acceptor_street = (String)json.get("card_acceptor_street");
            fraud_flag = (Integer)json.get("fraud_flag");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected Transaction(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        processor_account = in.readString();
        if (in.readByte() == 0) {
            our_account = null;
        } else {
            our_account = in.readString();
        }
        if (in.readByte() == 0) {
            post_amount = null;
        } else {
            post_amount = in.readDouble();
        }
        post_success = in.readByte() != 0;
        if (in.readByte() == 0) {
            hold_action = null;
        } else {
            hold_action = in.readInt();
        }
        duplicate_flag = in.readByte() != 0;
        if (in.readByte() == 0) {
            transaction_code = null;
        } else {
            transaction_code = in.readInt();
        }
        if (in.readByte() == 0) {
            auth_id_response = null;
        } else {
            auth_id_response = in.readInt();
        }
        if (in.readByte() == 0) {
            trace_number = null;
        } else {
            trace_number = in.readInt();
        }
        reference_number = in.readString();
        local_tran_date = in.readString();
        local_tran_time = in.readString();
        byte tmpDevice_type = in.readByte();
        device_type = tmpDevice_type == 0 ? null : tmpDevice_type == 1;
        reversal_flag = in.readString();
        adjustment_flag = in.readString();
        original_amount = in.readString();
        tran_sub_type = in.readString();
        our_preauth_code = in.readString();
        our_tran_code = in.readString();
        our_transmission_date = in.readString();
        our_transmission_time = in.readString();
        cash_back_amount = in.readString();
        card_acceptor_city = in.readString();
        card_acceptor_country = in.readString();
        card_acceptor_id_code = in.readString();
        card_acceptor_name = in.readString();
        card_acceptor_state = in.readString();
        card_acceptor_street = in.readString();
        if (in.readByte() == 0) {
            fraud_flag = null;
        } else {
            fraud_flag = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(processor_account);
        if (our_account == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(our_account);
        }
        if (post_amount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(post_amount);
        }
        dest.writeByte((byte) (post_success ? 1 : 0));
        if (hold_action == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(hold_action);
        }
        dest.writeByte((byte) (duplicate_flag ? 1 : 0));
        if (transaction_code == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(transaction_code);
        }
        if (auth_id_response == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(auth_id_response);
        }
        if (trace_number == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(trace_number);
        }
        dest.writeString(reference_number);
        dest.writeString(local_tran_date);
        dest.writeString(local_tran_time);
        dest.writeByte((byte) (device_type == null ? 0 : device_type ? 1 : 2));
        dest.writeString(reversal_flag);
        dest.writeString(adjustment_flag);
        dest.writeString(original_amount);
        dest.writeString(tran_sub_type);
        dest.writeString(our_preauth_code);
        dest.writeString(our_tran_code);
        dest.writeString(our_transmission_date);
        dest.writeString(our_transmission_time);
        dest.writeString(cash_back_amount);
        dest.writeString(card_acceptor_city);
        dest.writeString(card_acceptor_country);
        dest.writeString(card_acceptor_id_code);
        dest.writeString(card_acceptor_name);
        dest.writeString(card_acceptor_state);
        dest.writeString(card_acceptor_street);
        if (fraud_flag == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(fraud_flag);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    public String getOur_transmission_date() {
        return our_transmission_date;
    }

    public String getLocal_tran_time() {
        return local_tran_time;
    }

    public Double getPost_amount() {
        return post_amount;
    }

    public boolean isPost_success() {
        return post_success;
    }
    public String getCard_acceptor_name(){return card_acceptor_name;}
    public Integer getId() {return id;}
    public Integer getFraud_flag() {return fraud_flag;}
    public String getLocal_tran_date() {return local_tran_date;}
    public String getProcessor_account() {return processor_account;}

}
