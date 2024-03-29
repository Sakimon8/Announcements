package com.example.announcements;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updatedb extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new MyAsyncTask().execute();
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
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    MainActivity.arraySubject.clear();
                    MainActivity.arrayContent.clear();
                    for (DataSnapshot AnnSnapshot : dataSnapshot.getChildren()) {
                        Announcements a = AnnSnapshot.getValue(Announcements.class);
                        MainActivity.arrayContent.add(a.content);
                        MainActivity.arraySubject.add(a.subject);
                    }
                    MainActivity.changeData();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(updatedb.this, "Database Connection Error", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            stopSelf();
        }
    }
}
