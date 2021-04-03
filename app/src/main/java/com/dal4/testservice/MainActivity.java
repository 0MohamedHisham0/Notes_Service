package com.dal4.testservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//دمات غير مقيدة (Unbound / Started Services)
//خدمات مقيدة (Bound Services)
//و فيما يأتي الفرق بينهما :
//
//1-الخدمات غير المقيدة هي خدمات يتم استدعاؤها من الـ Activity وايقافها منها ايضا لتقوم بعمليات معينة بشكل منفصل عن الـ Activity
//
//2-الخدمات المقيدة هي خدمات مرتبطة بالـ Activity حيث يمكن معرفة تقدم سير العمليات مثلا لو كان البرنامج عبارة عن برنامج يقوم بتحميل ملف من الانترنت ستتمكن من معرفة النسبة المئوية لتقدم التحميل أو حالة التحميل ان كان متوقفا او مستمرا وغيرها من العمليات من خلال الـ Activity وكذلك بالنسبة لباقي العمليات .
//
//خلاصة : الخدمات المقيدة خدمات تستطيع معرفة معلومات اكثر عنها من خلال الواجهة التي يتعامل معها المستخدم على عكس غير المقيدة

//Service Connection this is for CounterService_Bound
public class MainActivity extends AppCompatActivity implements ServiceConnection {
    MyService myService;
    Button btn, btn2, btn3;
    CounterService_Bound.BinderCounter binderCounter;
    CounterService_Bound counterService_bound;
    TextView TextCounterBinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BroadCast Low Battery
        registerReceiver(new BroadcastReceiver_LowBattery(), new IntentFilter(Intent.ACTION_BATTERY_LOW));

        Intent intentServiceToBind = new Intent(MainActivity.this, CounterService_Bound.class);
        bindService(intentServiceToBind, this, CounterService_Bound.BIND_AUTO_CREATE);

        TextCounterBinder = findViewById(R.id.TextCounterBinder);
        btn = findViewById(R.id.btn);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, MyService.class);
                startService(in);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, MyService.class);
                stopService(in);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextCounterBinder.setText("" + counterService_bound.StartTimer());
            }
        });


    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        binderCounter = (CounterService_Bound.BinderCounter) binder;
        counterService_bound = ((CounterService_Bound.BinderCounter) binder).getCounterService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}