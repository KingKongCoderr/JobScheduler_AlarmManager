package jaihind.gobblessamerica.alarmmanager;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.PersistableBundle;
import android.util.Log;

/**
 * Created by nande on 10/1/2017.
 */

public class MyJobService extends JobService {
    private static final String TAG = "MyJobService";
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        PersistableBundle pb = jobParameters.getExtras();
        String msg = pb.getString("string");
        Log.d(TAG, msg);
        return false;
    }
    
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        
        return false;
    }
}
