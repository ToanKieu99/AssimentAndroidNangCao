package com.toanvu.assimentandroidnangcao.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.toanvu.assimentandroidnangcao.detail.LearningKeyInformation;
import com.toanvu.assimentandroidnangcao.R;
import com.toanvu.assimentandroidnangcao.database.KhoaHocJavaWebDao;
import com.toanvu.assimentandroidnangcao.model.KhoaHoc;

import java.text.SimpleDateFormat;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    List<KhoaHoc> arrKhoaHoc;
    Activity context;
    KhoaHocJavaWebDao khoaHocJavaWebDao;
    SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");

    public ScheduleAdapter(Activity context, List<KhoaHoc> arrKhoaHoc) {
        this.context = context;
        this.arrKhoaHoc = arrKhoaHoc;
        khoaHocJavaWebDao = new KhoaHocJavaWebDao(context);

    }

    @NonNull
    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_lich_hoc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.ViewHolder holder, final int position) {
        KhoaHoc kh = arrKhoaHoc.get(position);
        holder.itemLichHoc.setText(arrKhoaHoc.get(position).getNameKH());
        holder.itemdateKH.setText(sp.format(arrKhoaHoc.get(position).getThoigianHK()));
        holder.itemCtLh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Optional");
                builder.setPositiveButton("Huỷ khoá học", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        khoaHocJavaWebDao.deleteKhoaHoc(arrKhoaHoc.get(position).getId());
                        arrKhoaHoc.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Xem thông tin khoá học", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context,LearningKeyInformation.class);
                        Bundle sBQL = new Bundle();
                        sBQL.putString("MAKH", arrKhoaHoc.get(position).getId());
                        sBQL.putString("NAMEKH", arrKhoaHoc.get(position).getNameKH());
                        sBQL.putString("NGAYKH",sp.format(arrKhoaHoc.get(position).getThoigianHK())) ;
                        sBQL.putString("GIOITHIEU", arrKhoaHoc.get(position).getChitiet());
                        intent.putExtras(sBQL);
                        context.startActivity(intent);
                    }
                });
                builder.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrKhoaHoc.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemLichHoc,itemdateKH;
        private ImageView itemCtLh;


        public ViewHolder(View itemView) {
            super(itemView);
            itemLichHoc = (TextView) itemView.findViewById(R.id.item_lich_hoc);
            itemCtLh = (ImageView) itemView.findViewById(R.id.item_ct_lh);
            itemdateKH = itemView.findViewById(R.id.item_date);
        }
    }
    public void changeDataset(List<KhoaHoc> quanLyModels) {
        this.arrKhoaHoc = quanLyModels;
        notifyDataSetChanged();
    }
}
