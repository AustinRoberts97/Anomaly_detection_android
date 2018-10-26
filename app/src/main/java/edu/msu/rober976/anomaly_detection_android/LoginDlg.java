package edu.msu.rober976.anomaly_detection_android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class LoginDlg extends DialogFragment {
    private static final String BASE_URL = "http://django-env.zqqwi3vey2.us-east-1.elasticbeanstalk.com";
    private static final String API_URL = "/api/token";
    private volatile boolean cancel = false;
    private String username;
    private String password;
    private boolean success = false;
    private Context context;
    private JSONObject tokens;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Logging in");

        builder.setNegativeButton(android.R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        cancel = true;
                    }
                });

        //creates dlg box
        final AlertDialog dlg = builder.create();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Cloud cloud = new Cloud();
                tokens = cloud.createAuthToken(username,password);
            }
        }).start();
        return dlg;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
