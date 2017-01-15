package com.example.mydrawerlayout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mydrawerlayout.MainActivity;
import com.example.mydrawerlayout.R;

import java.util.ArrayList;

/**
 * Created by zhiyuan on 16/12/30.
 */

public class LefetFragment extends Fragment {

    private ListView lv_left;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.left_fragment, null);
        lv_left = (ListView) view.findViewById(R.id.lv_left);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        lv_left.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,dataList));
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //去更新main中内容
                MainActivity activity = (MainActivity) getActivity();
                activity.updateUI(dataList.get(i));
            }
        });
    }
    ArrayList<String> dataList=new ArrayList<>();
    private void initData() {
        for (int i = 0; i <20 ; i++) {
            dataList.add("我是第"+i+"条目");
        }
    }
}
