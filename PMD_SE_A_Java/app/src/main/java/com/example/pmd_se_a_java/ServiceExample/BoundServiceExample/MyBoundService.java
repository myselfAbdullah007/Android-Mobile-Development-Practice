package com.example.pmd_se_a_java.ServiceExample.BoundServiceExample;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.pmd_se_a_java.R;


public class MyBoundService extends Service {

    public Binder myBinder = new MyBinder();
    public MediaPlayer mediaPlayer;

    public MyBoundService() {
    }
    @Override
    public void onCreate(){
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.audio);
    }
    public void Play(){
        mediaPlayer.start();
        Toast.makeText(this, "Media player started", Toast.LENGTH_SHORT).show();
    }

    public void Pause(){
        mediaPlayer.pause();
    }

    public boolean isPlaying(){
        return mediaPlayer.isPlaying();
    }

    public void Stop(){
        mediaPlayer.stop();
        Toast.makeText(this, "Media Player is stoped", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder ;
    }

    public class MyBinder extends Binder {
        MyBoundService getServiceMethod(){
            return MyBoundService.this;
        }

    }
}