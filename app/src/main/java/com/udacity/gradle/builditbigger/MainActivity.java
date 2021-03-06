package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.jokeandroidlib.JokeDisplay;
//import com.example.jokerlib.Joker;
//import com.udacity.gradle.builditbigger.cloudControl.EndpointsAsyncTask;


public class MainActivity extends AppCompatActivity {
   // Joker joker = new Joker();
    public final String JOKE_KEY = "JOKE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));

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

    public void tellJoke(View view) {

        Intent intent = new Intent(this ,JokeDisplay.class);
        intent.putExtra(JOKE_KEY , "dd");
        startActivity(intent);

        Toast.makeText(this, "dd", Toast.LENGTH_SHORT).show();
    }


}
