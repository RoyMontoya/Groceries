package com.example.amado.groceries;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Amado on 20/07/2015.
 */
public class ConfirmationDialog extends DialogFragment {
    private static final String TAG = "ConfirmationDialog";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_confirmation, null);


        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendConfirmation(true);
                    }
                })
                .setNegativeButton(android.R.string.cancel,null)
                .create();
    }



    public void sendConfirmation(boolean confirmation){
        if(getTargetFragment() == null){
            return;
        }

        Intent i = new Intent();
        i.putExtra(ItemViewFragment.EXTRA_CONFIRMATION, confirmation);

        getTargetFragment().onActivityResult(getTargetRequestCode(), getActivity().RESULT_OK, i);
    }
}
