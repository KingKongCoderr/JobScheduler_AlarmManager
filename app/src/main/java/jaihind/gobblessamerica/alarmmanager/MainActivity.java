package jaihind.gobblessamerica.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private Button alarmBt, cancelAlarmBt;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmBt = (Button)findViewById(R.id.alarm_bt);
        cancelAlarmBt = (Button)findViewById(R.id.cancel_alarm_bt);
        final Intent broadcastIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        final AlarmManager am = (AlarmManager)MainActivity.this.getSystemService(ALARM_SERVICE);
        final PendingIntent pi = PendingIntent
                .getBroadcast(MainActivity.this,0,broadcastIntent , PendingIntent.FLAG_UPDATE_CURRENT);
        alarmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                long triggerTime =  System.currentTimeMillis()
                        , delayTime = TimeUnit.SECONDS.toMillis(10L);
                am.setRepeating(AlarmManager.RTC_WAKEUP, (triggerTime + delayTime*6 ),delayTime, pi );
            }
        });
        cancelAlarmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                am.cancel(pi);
            }
        });
    }
}
