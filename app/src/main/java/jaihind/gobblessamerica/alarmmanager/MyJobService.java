package jaihind.gobblessamerica.alarmmanager;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
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
        //String msg = pb.getString("string");
        //Log.d(TAG, msg);
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(jobParameters);
        return true;
    }
    
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        
        return false;
    }
    
    private class BackgroundTask extends AsyncTask<JobParameters, Void , JobParameters[]>{
    
        private JobService jobService;
    
        public BackgroundTask(JobService jobService) {
            this.jobService = jobService;
        }
    
        @Override
        protected JobParameters[] doInBackground(JobParameters... strings) {
            String msg = strings[0].getExtras().getString("string");
            Log.d(TAG, msg + "executing in asynctask");
            return  strings;
        }
    
        @Override
        protected void onPostExecute(JobParameters[] s) {
            jobService.jobFinished(s[0],false);
        }
    }
}
