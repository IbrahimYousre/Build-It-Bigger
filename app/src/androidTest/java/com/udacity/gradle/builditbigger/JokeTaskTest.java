package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.test.InstrumentationTestCase;

import org.junit.After;
import org.junit.Before;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class JokeTaskTest extends InstrumentationTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public final void testResultNonEmpty() throws Throwable {
        final MainActivity.JokeTask jokeTask = new MainActivity.JokeTask(null, null);
        final String[] result = new String[1];
        final CountDownLatch signal = new CountDownLatch(1);
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                result[0] = jokeTask.doInBackground();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                signal.countDown();
            }
        }.execute();
        signal.await(10, TimeUnit.SECONDS);
        String joke = result[0];
        assertTrue(joke != null && !joke.isEmpty());
    }
}