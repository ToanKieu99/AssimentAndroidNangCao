package com.toanvu.assimentandroidnangcao.bottomsheet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.toanvu.assimentandroidnangcao.R;
import com.toanvu.assimentandroidnangcao.adapter.StudentAdapter;
import com.toanvu.assimentandroidnangcao.database.HocVienDao;
import com.toanvu.assimentandroidnangcao.model.HocVien;

import java.util.ArrayList;
import java.util.List;

public class Information_BottomSheet extends BottomSheetDialogFragment {

    ListView listView;
    HocVienDao hocVienDao;
    public static List<HocVien> hv = new ArrayList<>();
    StudentAdapter adapter = null;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.information_bottm_sheet, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        listView = view.findViewById(R.id.lvHocVien);
        hocVienDao = new HocVienDao(getContext());
        registerForContextMenu(listView);

        try {
            hv = hocVienDao.getALLHocVien();
        } catch (Exception e) {
            e.printStackTrace();
        }

        adapter = new StudentAdapter(getActivity(), hv);
        listView.setAdapter(adapter);




    }


    @Override
    public void onResume() {
        super.onResume();
        hv.clear();
        try {
            hv = hocVienDao.getALLHocVien();
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter.changeDataset(hv);
    }
}
