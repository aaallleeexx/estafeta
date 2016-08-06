package com.example.alex.estafeta.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 06.08.16.
 */
public class DriverInfo implements Serializable{
    private int mId;
    private String mNumber;
    private String mPlannedStartDate;
    private String mPlannedEndDate;
    private String mActualStartDate;
    private String mActualEndDate;
    private String mVin;
    private String mModel;
    private String mModelCode;
    private String mBrand;
    private String mSurveyPoint;
    private String mCarrier;
    private String mDriver;

    public DriverInfo(JSONObject object) {
        try {
            this.mId = object.getInt("Id");
            this.mNumber = object.getString("Number");
            this.mPlannedStartDate = object.getString("PlannedStartDate");
            this.mPlannedEndDate = object.getString("PlannedEndDate");
            this.mActualStartDate = object.getString("ActualStartDate");
            this.mActualEndDate = object.getString("ActualEndDate");
            this.mVin = object.getString("Vin");
            this.mModel = object.getString("Model");
            this.mModelCode = object.getString("ModelCode");
            this.mBrand = object.getString("Brand");
            this.mSurveyPoint = object.getString("SurveyPoint");
            this.mCarrier = object.getString("Carrier");
            this.mDriver = object.getString("Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<DriverInfo> getDriverInfoList(JSONArray array) {
        ArrayList<DriverInfo> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                list.add(new DriverInfo(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public int getmId() {
        return mId;
    }

    public String getmNumber() {
        return mNumber;
    }

    public String getmPlannedStartDate() {
        return mPlannedStartDate;
    }

    public String getmPlannedEndDate() {
        return mPlannedEndDate;
    }

    public String getmActualStartDate() {
        return mActualStartDate;
    }

    public String getmActualEndDate() {
        return mActualEndDate;
    }

    public String getmVin() {
        return mVin;
    }

    public String getmModel() {
        return mModel;
    }

    public String getmModelCode() {
        return mModelCode;
    }

    public String getmBrand() {
        return mBrand;
    }

    public String getmSurveyPoint() {
        return mSurveyPoint;
    }

    public String getmCarrier() {
        return mCarrier;
    }

    public String getmDriver() {
        return mDriver;
    }
}
