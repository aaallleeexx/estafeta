package com.example.alex.estafeta.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alex.estafeta.R;
import com.example.alex.estafeta.models.DriverInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 06.08.16.
 */
public class CustomDriverAdapter extends BaseAdapter {
    private List<DriverInfo> list = new ArrayList<>();
    private Context mContext;

    public CustomDriverAdapter(Context context) {
        this.mContext = context;
    }

    public void setList(List<DriverInfo> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public DriverInfo getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();

            view = LayoutInflater.from(mContext).inflate(R.layout.list_view_item, viewGroup, false);
            holder.tvVin = (TextView) view.findViewById(R.id.item_vin);
            holder.tvDate = (TextView) view.findViewById(R.id.item_date);
            holder.tvModel = (TextView) view.findViewById(R.id.item_model);
            holder.tvName = (TextView) view.findViewById(R.id.item_driver_name);
            holder.tvSv = (TextView) view.findViewById(R.id.item_sv);
            holder.tvBrand = (TextView) view.findViewById(R.id.item_brand);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tvName.setText(list.get(i).getmDriver());
        holder.tvDate.setText(list.get(i).getmActualEndDate());
        holder.tvSv.setText(list.get(i).getmModelCode());
        holder.tvBrand.setText(list.get(i).getmBrand());
        holder.tvModel.setText(list.get(i).getmModel());
        holder.tvVin.setText(list.get(i).getmVin());

        return view;
    }

    private class ViewHolder {
        public TextView tvVin;
        public TextView tvModel;
        public TextView tvName;
        public TextView tvDate;
        public TextView tvSv;
        public TextView tvBrand;
    }
}
