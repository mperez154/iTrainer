package com.bignerdranch.android.itrainer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Created by Marco on 9/24/2016.
 */

public class AddSessionsFragment extends android.support.v4.app.DialogFragment {
    String messageBody = "Sessions to Add:";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.addSessionsMsg01)
                .setMessage(messageBody)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                dismiss();
                            }
                        })
                .create();

    }
}
