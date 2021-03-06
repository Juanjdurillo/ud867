package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.udacity.gradle.imageactivity.JokeActivity;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    public void tellJoke(View view){

        Toast.makeText(this, new Joker().getJoke(), Toast.LENGTH_SHORT).show();
    }


    public void launchLibraryActivity(View view) {
        Intent myIntent = new Intent(this, JokeActivity.class) ;
        startActivity(myIntent);
    }
*/

    public void notifyOtherApp(String joke) {
        Intent myIntent = new Intent(this, JokeActivity.class) ;
        Log.e("joooooooooooooooooooKEKEKEKEKEKKEK", joke);
        Bundle bundle = new Bundle();
        bundle.putString("JOKE",joke);
        myIntent.putExtras(bundle);
        startActivity(myIntent);
    }

    public void tellJoke(View view) {
        new EndpointAsyncTask().execute(new Pair<MainActivity, String>(this, "Manfred"));
    }

}
