package com.dal4.testservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.media.MediaPlayer;

public class MyIntentService extends IntentService {

    MediaPlayer player= null;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while (true) {
            long endTime = System.currentTimeMillis() +20*1000;
            player = MediaPlayer.create(this, R.raw.m2);
            player.start();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }
}