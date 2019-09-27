package com.example.announcements;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> arraySubject=new ArrayList<String>();
    public static ArrayList<String> arrayContent=new ArrayList<String>();
    public static int choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null) {
            arraySubject = savedInstanceState.getStringArrayList("subs");
            arrayContent=savedInstanceState.getStringArrayList("cont");
        }
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.add(R.id.fragSubject,new subject(),"Subject");
        t.commit();

   }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("subs",arraySubject);
        outState.putStringArrayList("cont",arrayContent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(getApplicationContext(),"Hi",Toast.LENGTH_SHORT).show();
        Bundle b = getIntent().getExtras();
            if(b!=null)
            {
                String sub = b.getString("subject");
                String cont=b.getString("content");
                arraySubject.add(sub);
                arrayContent.add(cont);

            }
        //adapter.notifyDataSetChanged();
    }
    public void checkLogin(View v)
    {
        Intent lgn=new Intent(MainActivity.this,login.class);
        startActivity(lgn);
    }

}
