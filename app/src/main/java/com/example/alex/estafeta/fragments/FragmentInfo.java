package com.example.alex.estafeta.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alex.estafeta.R;
import com.example.alex.estafeta.models.DriverInfo;

/**
 * Created by alex on 06.08.16.
 */
public class FragmentInfo extends Fragment {
    private final static String INFO_KEY = "driver_info_key";

    public static FragmentInfo newInstance(DriverInfo info) {

        Bundle args = new Bundle();
        args.putSerializable(INFO_KEY, info);
        FragmentInfo fragment = new FragmentInfo();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DriverInfo driverInfo;

        if (getArguments() != null) {
            driverInfo = (DriverInfo) getArguments().getSerializable(INFO_KEY);

            if (driverInfo != null) {
                ((TextView) view.findViewById(R.id.fragment_model)).setText(driverInfo.getmModel());
                ((TextView) view.findViewById(R.id.fragment_brand)).setText(driverInfo.getmBrand());
                ((TextView) view.findViewById(R.id.fragment_model_code)).setText(driverInfo.getmModelCode());
                ((TextView) view.findViewById(R.id.fragment_panned_start_time)).setText(driverInfo.getmActualStartDate());
                ((TextView) view.findViewById(R.id.fragment_vin)).setText(driverInfo.getmVin());
                ((TextView) view.findViewById(R.id.fragment_survey_date)).setText(driverInfo.getmActualEndDate());
                ((TextView) view.findViewById(R.id.fragment_place_inspection)).setText(driverInfo.getmSurveyPoint());
            }
        }
    }
}
