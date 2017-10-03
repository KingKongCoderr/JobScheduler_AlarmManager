package jaihind.gobblessamerica.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private Button alarmBt, cancelAlarmBt, jobscheduleBt;
    public static final int JOB_ID = 1 ;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmBt = (Button)findViewById(R.id.alarm_bt);
        cancelAlarmBt = (Button)findViewById(R.id.cancel_alarm_bt);
        jobscheduleBt = (Button)findViewById(R.id.jobscheduler_bt);
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
        jobscheduleBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JobScheduler jb = (JobScheduler)MainActivity.this.getSystemService(JOB_SCHEDULER_SERVICE);
                JobInfo.Builder jb_builder =
                        new JobInfo.Builder(JOB_ID, new ComponentName(MainActivity.this,MyJobService.class));
                PersistableBundle pb = new PersistableBundle();
                pb.putString("string", "i am from main activity");
                long polling_interval = TimeUnit.MINUTES.toMillis(3L);
                jb_builder.setRequiresCharging(true)
                        .setExtras(pb).setPeriodic(polling_interval);
                jb.schedule(jb_builder.build());
                Log.d("list", jb.getAllPendingJobs()+"");
            }
        });
    }
}
