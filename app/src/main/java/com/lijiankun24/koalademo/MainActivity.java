package com.lijiankun24.koalademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lijiankun24.koala.Cost;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getList();
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
    private List<String> getList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

//
//    @Cost
//    public static void forTest() {
//
//    }
//
//    @Cost
//    public static void forTest(int i) {
//
//    }
//
//    @Cost
//    public static void forTest(int i, int j) {
//
//    }
//
//    @Cost
//    public static void forTest(int i, int j, int z) {
//
//    }
}
