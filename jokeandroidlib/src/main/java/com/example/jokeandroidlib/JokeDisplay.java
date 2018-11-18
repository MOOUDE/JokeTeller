package com.example.jokeandroidlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplay extends AppCompatActivity {
TextView joke;
    public final String JOKE_KEY = "JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        joke = (TextView) findViewById(R.id.jokeDisplay);

        String jokeString = getIntent().getStringExtra(JOKE_KEY);

        joke.setText(jokeString);



    }
}
