package com.example.studyandroid.IOC;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.library.IOCLibrary.InjectManager;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InjectManager.inject(this);
    }
}
