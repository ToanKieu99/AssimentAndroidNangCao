package com.toanvu.assimentandroidnangcao.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.toanvu.assimentandroidnangcao.R;
import com.toanvu.assimentandroidnangcao.database.HocVienDao;
import com.toanvu.assimentandroidnangcao.model.HocVien;

import java.text.SimpleDateFormat;
import java.util.List;

public class StudentAdapter extends BaseAdapter {
    List<HocVien> arrHocVien;
    public Activity context;
    public LayoutInflater inflater;
    HocVienDao hocVienDao;
    SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");


    public StudentAdapter(Activity context, List<HocVien> arrHocVien) {
        super();
        this.context = context;
        this.arrHocVien = arrHocVien;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hocVienDao = new HocVienDao(context);
    }

    @Override
    public int getCount() {
        return arrHocVien.size();
    }

    @Override
    public Object getItem(int position) {
        return arrHocVien.get(position);
    }

    @Override
    public long getItemId(int position)     {
        return 0;
    }

    public static class ViewHolder {
        private TextView tvUsername;
        private TextView tvName;
        private TextView tvID;
        private TextView Gender;
        private TextView tvBirthday;
        private TextView tvEmail;
        private TextView tvPhone;
        private TextView tvPlaceBirth;

    }




    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_student, null);
            holder.tvUsername = convertView.findViewById(R.id.tvUsername);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvID = (TextView) convertView.findViewById(R.id.tvID);
            holder.Gender =  convertView.findViewById(R.id.Gender);
            holder.tvBirthday = convertView.findViewById(R.id.tvBirthday);
            holder.tvEmail = (TextView) convertView.findViewById(R.id.tvEmail);
            holder.tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);
            holder.tvPlaceBirth =  convertView.findViewById(R.id.tvPlaceBirth);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        HocVien _entry = arrHocVien.get(position);
        holder.tvUsername.setText(_entry.getUsername());
        holder.tvName.setText(_entry.getName());
        holder.tvID.setText(_entry.getId());
        holder.Gender.setText(_entry.getGender());
        holder.tvBirthday.setText(sp.format(_entry.getBirthday()));
        holder.tvEmail.setText(_entry.getEmail());
        holder.tvPhone.setText(_entry.getPhone());
        holder.tvPlaceBirth.setText(_entry.getPlaceofbirth());

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<HocVien> items) {
        this.arrHocVien = items;
        notifyDataSetChanged();
    }

}
