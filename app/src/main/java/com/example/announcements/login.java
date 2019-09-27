package com.example.announcements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void check(View v)
    {

        Intent lgn=new Intent(login.this,addAnnouncement.class);
        EditText e1=findViewById(R.id.editText);
        String username=e1.getText().toString();
        EditText e2=findViewById(R.id.editText3);
        String Password=e2.getText().toString();
        if(username.equals("admin")&&Password.equals("admin")) {
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
            startActivity(lgn);
        }
        else
            Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
    }
}
