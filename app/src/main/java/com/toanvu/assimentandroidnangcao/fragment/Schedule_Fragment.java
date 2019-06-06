package com.toanvu.assimentandroidnangcao.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toanvu.assimentandroidnangcao.R;
import com.toanvu.assimentandroidnangcao.adapter.ScheduleAdapter;
import com.toanvu.assimentandroidnangcao.database.KhoaHocJavaWebDao;
import com.toanvu.assimentandroidnangcao.model.KhoaHoc;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Schedule_Fragment extends Fragment {
    private RecyclerView recylerScheule;
    KhoaHocJavaWebDao khoaHocJavaWebDao;
    List<KhoaHoc>  kh = new ArrayList<>();
    ScheduleAdapter adapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.schedule_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recylerScheule = (RecyclerView) view.findViewById(R.id.recyler_Scheule);

        kh = new ArrayList<>();
        khoaHocJavaWebDao = new KhoaHocJavaWebDao(getContext());

        try {
            kh = khoaHocJavaWebDao.getALLKhoaHoc();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        adapter = new ScheduleAdapter(getActivity(), kh);
        recylerScheule.setAdapter(adapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recylerScheule.setLayoutManager(manager);
    }

    public void onResume() {
        super.onResume();
        kh.clear();
        try {
            kh = khoaHocJavaWebDao.getALLKhoaHoc();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapter.changeDataset(kh);
    }
}
