package com.example.l176310_a4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.security.ConfirmationAlreadyPresentingException;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.l176310_a4.AlarmReceiver.mediaPlayer;

public class MainActivity extends AppCompatActivity {

    int trigger=-1;
    int cAlarm=-1;

    TimePicker tPicker1;
    Button setAlarm1;
    Button setAlarm2;
    Button cancelAlarm1;
    Button cancelAlarm2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tPicker1=findViewById(R.id.timePicker1);
        setAlarm1=findViewById(R.id.setAlarm1);
        setAlarm2=findViewById(R.id.setAlarm2);
        cancelAlarm1=findViewById(R.id.cancelAlarm1);
        cancelAlarm2=findViewById(R.id.cancelAlarm2);

        setAlarm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calender= Calendar.getInstance();
                calender.set(calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH),
                        tPicker1.getHour(),
                        tPicker1.getMinute(),
                        0

                );
                trigger=0;
                setAlarm(calender.getTimeInMillis());

            }
        });

        setAlarm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calender= Calendar.getInstance();
                calender.set(calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH),
                        tPicker1.getHour(),
                        tPicker1.getMinute(),
                        0

                );
                trigger=1;
                setAlarm(calender.getTimeInMillis());

            }
        });
        cancelAlarm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cAlarm=0;
                cancelAlarm();
            }
        });
        cancelAlarm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cAlarm=1;
                cancelAlarm();
            }
        });
    }

    private void setAlarm(long timeInMillis) {
        if(trigger==0) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent goToAlarmReceiver = new Intent(this, AlarmReceiver.class);
            PendingIntent intent = PendingIntent.getBroadcast(this, 1, goToAlarmReceiver, 0);
            alarmManager.setExact(AlarmManager.RTC, timeInMillis, intent);
        }
        if(trigger==1) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent goToAlarmReceiver = new Intent(this, AlarmReceiver.class);
            PendingIntent intent = PendingIntent.getBroadcast(this, 2, goToAlarmReceiver, 0);
            alarmManager.setExact(AlarmManager.RTC, timeInMillis, intent);
        }

        Toast.makeText(this,"The Alarm is set",Toast.LENGTH_SHORT).show();
    }
    private void cancelAlarm()
    {
        if(cAlarm==0) {

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent goToAlarmReceiver = new Intent(this, AlarmReceiver.class);
            PendingIntent intent = PendingIntent.getBroadcast(this, 1, goToAlarmReceiver, 0);
            if (alarmManager != null) {
                alarmManager.cancel(intent);
                mediaPlayer.stop();
            }
        }

        else if(cAlarm==1)
        {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent goToAlarmReceiver = new Intent(this, AlarmReceiver.class);
            PendingIntent intent = PendingIntent.getBroadcast(this, 2, goToAlarmReceiver, 0);

            if (alarmManager != null) {
                alarmManager.cancel(intent);
                mediaPlayer.stop();
            }
        }

    }

}
