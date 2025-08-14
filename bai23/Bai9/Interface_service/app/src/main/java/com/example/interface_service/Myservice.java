package com.example.interface_service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.interface_service.R;

public class Myservice extends Service {
    private static final String TAG = "MyService";
    MediaPlayer mymedia;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: Service is created");
        mymedia = MediaPlayer.create(this, R.raw.tinhte);
        mymedia.setLooping(true); // Tự động lặp lại bài hát
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mymedia.isPlaying())
            mymedia.pause();
        else
            mymedia.start();
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mymedia.stop();
    }
}