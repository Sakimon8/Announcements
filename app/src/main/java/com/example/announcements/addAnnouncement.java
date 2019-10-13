package com.example.announcements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class addAnnouncement extends AppCompatActivity {
DatabaseReference reff;
Announcements newAnn;
static Intent ser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcement);
    }
    public void addAnnouncement(View v)
    {
        Intent addAnn=new Intent(addAnnouncement.this,MainActivity.class);
        EditText e1=findViewById(R.id.editText2);
        EditText e2=findViewById(R.id.editText4);
        String sub=e1.getText().toString();
        String con=e2.getText().toString();
        addAnn.putExtra("subject",sub);
        addAnn.putExtra("content",con);
        reff= FirebaseDatabase.getInstance().getReference().child("Announcements");
        newAnn=new Announcements();
        newAnn.setContent(con);
        newAnn.setSubject(sub);
        reff.push().setValue(newAnn);
        ser=new Intent(this,updatedb.class);
        startService(ser);
        startActivity(addAnn);
    }
}
