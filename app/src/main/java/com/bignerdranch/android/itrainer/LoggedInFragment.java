package com.bignerdranch.android.itrainer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LoggedInFragment extends Fragment {

    View rootView;

    public static LoggedInFragment newInstance() {
        Bundle args = new Bundle();

        LoggedInFragment fragment = new LoggedInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_logged_in, container, false);
        return rootView;
    }


}
