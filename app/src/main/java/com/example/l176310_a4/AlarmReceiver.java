package com.example.l176310_a4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;

public class AlarmReceiver extends BroadcastReceiver {

    static  MediaPlayer mediaPlayer;
    public void onReceive(Context context, Intent intent) {
      mediaPlayer =MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
      mediaPlayer.start();
    }
}
