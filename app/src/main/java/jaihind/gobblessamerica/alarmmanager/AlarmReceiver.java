package jaihind.gobblessamerica.alarmmanager;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {
    
    private static final String TAG = "AlarmReceiver";
    NotificationCompat.Builder  notfBuilder;
    NotificationManager notificationManager;
    
    
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"I am in onReceive of AlarmReceiver" );
        notificationManager= (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notfBuilder = new NotificationCompat.Builder(context);
        
        notfBuilder
                .setContentText("alarm running at"+ getDate(System.currentTimeMillis(), "h:mm a"))
                .setContentTitle("Alarm Manager")
                .setSmallIcon(R.drawable.notification_icon);
        notificationManager.notify(0,notfBuilder.build());
        Toast.makeText(context,"alarm running", Toast.LENGTH_LONG);
    }
    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        
        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
