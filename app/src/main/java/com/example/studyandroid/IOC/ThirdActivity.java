package com.example.studyandroid.IOC;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.IOCLibrary.annotation.ContentView;
import com.example.library.IOCLibrary.annotation.InjectView;

import com.example.library.IOCLibrary.annotation.OnClick;
import com.example.library.IOCLibrary.annotation.OnLongClick;
import com.example.studyandroid.R;


@ContentView(R.layout.activity_third)
public class ThirdActivity extends BaseActivity {

    @InjectView(R.id.ta)
    private TextView textView;

    @InjectView(R.id.btn)
    private Button btn;

    @OnClick({R.id.btn,R.id.ta})
    public void show(View view){
        Toast.makeText(this,"show(view)",Toast.LENGTH_SHORT).show();
    }

    @OnLongClick({R.id.btn,R.id.ta})
    public void show(){
        Toast.makeText(this,"show()",Toast.LENGTH_SHORT).show();
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_third);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),btn.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
