package com.example.amado.groceries;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Amado on 02/07/2015.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "RPrbD1gNIjG2QtEU1PdQrKJnyNwdsS2IyURbwZll", "UjSS4isyfdACK8qiuD9Nnie6J742VOtKAifvTyO2");
    }
}
