package com.example.announcements;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.xml.transform.Templates;


public class subject extends Fragment {
    private ListView lv;
    private ArrayAdapter<String> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        lv= view.findViewById(R.id.listsubs);
        adapter= new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, MainActivity.arraySubject);
        lv.setAdapter(adapter);
        lv.setDividerHeight(20);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.choice=i;
                FragmentTransaction t = getFragmentManager().beginTransaction();
                t.replace(R.id.fragContent,new Content(),"Content");
                t.commit();
            }
        });

        adapter.notifyDataSetChanged();
        // Inflate the layout for this fragment
        return view;
    }


}
