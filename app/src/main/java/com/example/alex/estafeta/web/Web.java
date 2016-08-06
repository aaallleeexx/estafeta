package com.example.alex.estafeta.web;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by alex on 06.08.16.
 */
public class Web {
    private final static String URL_TASK_LIST = "http://amt2.estafeta.org/api/mobilesurveytasks/gettestsurveytasks";
    private final static String LOGIN = "admin";
    private final static String PASSWORD = "1";
    private final static String COMPANY_ID = "9F346DDB-8FF8-4F42-8221-6E03D6491756";

    private OkHttpClient.Builder mClientBuilder = new OkHttpClient.Builder();
    private OkHttpClient mClient;
    public static Web web;

    public static Web newInstance() {
        if (web == null) {
            return new Web();
        }
        return web;
    }

    private OkHttpClient makeClient() {
        mClientBuilder.authenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                if (responseCount(response) >= 3) {
                    return null;
                }
                String credential = Credentials.basic(LOGIN + "@" + COMPANY_ID, PASSWORD);
                return response.request().newBuilder().header("Authorization", credential).build();
            }
        });
        mClientBuilder.connectTimeout(10, TimeUnit.SECONDS);
        mClientBuilder.writeTimeout(10, TimeUnit.SECONDS);
        mClientBuilder.readTimeout(30, TimeUnit.SECONDS);

        return mClientBuilder.build();
    }

    private JSONArray run(String url) throws IOException, JSONException {
        mClient = makeClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = mClient.newCall(request).execute();
        return new JSONArray(response.body().string());
    }

    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }

    public JSONArray getTaskList() throws IOException, JSONException {
        return run(URL_TASK_LIST);
    }
}