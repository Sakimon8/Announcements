package com.example.announcements;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addtodb extends Service {
    String con,sub;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new addtodb.MyAsyncTask().execute();
        Bundle b=intent.getExtras();
        con=b.getString("con");
        sub=b.getString("sub");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    class MyAsyncTask extends AsyncTask<Integer, String, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override // Perform our Long Running Task
        protected Void doInBackground(Integer... params) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Announcements");
            Announcements newAnn=new Announcements();
            newAnn.setContent(con);
            newAnn.setSubject(sub);
            ref.push().setValue(newAnn);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            stopSelf();
        }
    }
}
