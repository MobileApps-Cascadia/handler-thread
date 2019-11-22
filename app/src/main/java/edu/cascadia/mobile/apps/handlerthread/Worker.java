package edu.cascadia.mobile.apps.handlerthread;

import android.os.Handler;
import android.os.HandlerThread;

public class Worker extends HandlerThread {
    private static final String THREADNAME = "Worker Bee";

    private Handler handler;

    public Worker(){
        super(THREADNAME);
        start(); // Begin the thread's looper
        handler = new Handler(getLooper()); //assigns a handler Worker's looper
    }

    public Worker execute(Runnable myTask){
        handler.post(myTask); //Handler places the task onto the Thread's message queue
        return this;
    }
}
