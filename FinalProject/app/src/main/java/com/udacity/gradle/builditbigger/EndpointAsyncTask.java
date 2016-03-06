package com.udacity.gradle.builditbigger;


import android.os.AsyncTask;
import android.util.Pair;

import com.example.juan.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;


public class EndpointAsyncTask  extends AsyncTask<Pair<MainActivity, String>, Void, String> {
    private static MyApi myApiService = null;
    private MainActivity activity;
    private EndpointTaskListener myListener;

    @Override
    protected String doInBackground(Pair<MainActivity, String>... params) {
        if(myApiService == null) {  // Only do this once
            /*MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });*/
            // end options for devappserver


            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://basicendpoint.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        activity = params[0].first;
        String name = params[0].second;

        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

        if (this.myListener != null)
            this.myListener.onComplete(result,null);

        if (activity!=null)
            activity.notifyOtherApp(result);


    }

    @Override
    protected void onCancelled() {
        if (this.myListener!=null)
            this.myListener.onComplete(null,new InterruptedException("AssyncTask cancelled"));

    }

    public EndpointAsyncTask setMyListener(EndpointTaskListener listener) {
        this.myListener = listener;
        return  this;
    }

    public static interface EndpointTaskListener {
        public void onComplete(String jsonString, Exception e);
    }
}