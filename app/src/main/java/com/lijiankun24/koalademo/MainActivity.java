package com.lijiankun24.koalademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lijiankun24.koala.KoalaLog;

/**
 * MainActivity.java
 * <p>
 * Created by lijiankun24 on 2018/7/29.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printPerson(new Person(20, "lijiankun24"), 100, true, (byte) 0, 'A');
            }
        });
    }

    @KoalaLog
    private Person printPerson(Person person, int x, boolean flag, byte time, char temp) {
        Log.i(TAG, "flag is " + flag);
        Log.i(TAG, "time is " + time);
        Log.i(TAG, "temp is " + temp);
        person.setName("new Name");
        person.setAge(x);
        return person;
    }
}
