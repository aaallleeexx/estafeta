package com.example.alex.estafeta;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.alex.estafeta.adapters.CustomDriverAdapter;
import com.example.alex.estafeta.fragments.FragmentInfo;
import com.example.alex.estafeta.models.DriverInfo;
import com.example.alex.estafeta.web.Web;

import org.json.JSONArray;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private CustomDriverAdapter mAdapter;
    private ListView mListView;
    private List<DriverInfo> mDriverList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        mListView = (ListView) findViewById(R.id.act_main_list_view);
        mAdapter = new CustomDriverAdapter(this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    private void initList() {
        new AsyncTask<Void, Void, JSONArray>() {
            @Override
            protected JSONArray doInBackground(Void... voids) {
                try {
                    return Web.newInstance().getTaskList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(JSONArray object) {
                super.onPostExecute(object);
                if (object != null) {
                    Log.d("dbg", object.toString());
                    mDriverList = DriverInfo.getDriverInfoList(object);
                    mAdapter.setList(mDriverList);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        getSupportFragmentManager().beginTransaction().add(R.id.act_main_container, FragmentInfo.newInstance(mDriverList.get(i)))
                .addToBackStack(FragmentInfo.class.toString()).commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
            return;
        }
        super.onBackPressed();
    }
}
