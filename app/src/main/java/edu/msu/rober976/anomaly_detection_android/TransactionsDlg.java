package edu.msu.rober976.anomaly_detection_android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;

/**
 * Created by rober on 9/28/2018.
 */

public class TransactionsDlg extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Loading transactions");

        final AlertDialog dlg = builder.create();

        return dlg;
    }
}
