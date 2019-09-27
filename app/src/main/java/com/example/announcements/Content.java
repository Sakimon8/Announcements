package com.example.announcements;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Content extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_content, container, false);

        TextView tv =v.findViewById(R.id.ContentText);
        tv.setText(MainActivity.arrayContent.get(MainActivity.choice));
        return v;
    }

}
