package com.bignerdranch.android.itrainer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by mperez5 on 9/21/2016.
 */

public class SettingsFragment extends android.support.v4.app.DialogFragment {
    String messageBody = "Setting 1\nSetting 2\nSetting 3";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.settings_Msg01)
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
