package com.bignerdranch.android.itrainer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by mperez5 on 9/22/2016.
 */

public class AboutUsFragment extends android.support.v4.app.DialogFragment {
    String messageBody = "Built by Marco Perez\nYear Built: 2016\nClass: Android Programming";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.about_Msg01)
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
