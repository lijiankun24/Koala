package com.lijiankun24.koalademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lijiankun24.koala.Cost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                costTime();
                forTest();
            }
        });
    }

    @Cost
    private void costTime() {
        for (int i = 0; i < 100000; ) {
            i++;
        }
    }

    @Cost
    public static void forTest() {

    }

    @Cost
    public static void forTest(int i) {

    }

    @Cost
    public static void forTest(int i, int j) {

    }

    @Cost
    public static void forTest(int i, int j, int z) {

    }
}
