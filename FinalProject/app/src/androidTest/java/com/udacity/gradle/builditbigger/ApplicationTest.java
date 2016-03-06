package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Pair;

import java.util.concurrent.CountDownLatch;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends AndroidTestCase {

    private String resultFromTask = "";
    private CountDownLatch signal = null;


    public void testAlbumGetTask() throws java.lang.InterruptedException{

        signal = new CountDownLatch(1);
        EndpointAsyncTask task = new EndpointAsyncTask();
        task.setMyListener(new EndpointAsyncTask.EndpointTaskListener() {
            @Override
            public void onComplete(String result, Exception e) {
                if (e==null)
                    resultFromTask = result;
                signal.countDown();
            }
        }).execute(new Pair<Context, String>(null, "Manfred"));
        signal.await();


        assertFalse(resultFromTask.equals(""));
    }

}