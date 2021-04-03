package com.dal4.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Timer;

public class CounterService_Bound extends Service {
    public int counter = 0;
    BinderCounter binderCounter = new BinderCounter();
    Timer timer = new Timer();

    public CounterService_Bound() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    //دي اللي بتودي الحاجه فا انا محتاج اودي معها الكلاس بتاعت البايندر IBinder
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binderCounter;
    }

    //هنا هعمل سب كلاس من بايندر علشان اوصل الكلاس دا للمين اكتيفيتي
    class BinderCounter extends Binder {
        //هنا بعمل مثود بترجع نوع الكلاس نفسو اللي هو CounterService_Bound
        public CounterService_Bound getCounterService() {
            return CounterService_Bound.this;
        }
    }

    public int StartTimer() {
        return ++counter;
    }


}