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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    DatabaseReference reff;
    public static ArrayList<String> arraySubject=new ArrayList<String>();
    public static ArrayList<String> arrayContent=new ArrayList<String>();
    public static int choice;
    Intent ser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null) {
            arraySubject = savedInstanceState.getStringArrayList("subs");
            arrayContent=savedInstanceState.getStringArrayList("cont");
        }
        ser= new Intent(this,updatedb.class);
        startService(ser);
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
        reff = FirebaseDatabase.getInstance().getReference().child("Announcements");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot AnnSnapshot : dataSnapshot.getChildren()) {
                    Announcements a = AnnSnapshot.getValue(Announcements.class);
                    arrayContent.add(a.content);
                    arraySubject.add(a.subject);
                }
                FragmentTransaction th = getSupportFragmentManager().beginTransaction();
                th.replace(R.id.fragSubject, new subject(), "Subject");
                th.commit();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void checkLogin(View v)
    {
        Intent lgn=new Intent(MainActivity.this,login.class);
        startActivity(lgn);
    }
    public static void changedata()
    {
       // FragmentTransaction th = getSupportFragmentManager().beginTransaction();
       // th.replace(R.id.fragSubject, new subject(), "Subject");
       // th.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(ser);
    }
}
