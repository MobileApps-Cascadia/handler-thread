package edu.cascadia.mobile.apps.handlerthread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Worker worker;
    private TextView textDisplay;
    private Handler uiHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);

            //TODO: Use the msg object to set the textDisplay value
            textDisplay.setText(msg.obj.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textDisplay = (TextView) findViewById(R.id.textDisplay);

        //Create a new HandlerThread
        worker = new Worker();
        worker.execute( new Runnable() {
            int count=1;
            @Override
            public void run() {
                while(true) { //keep running till the thread stops
                    try {
                        Thread.sleep(1000); // sleep for 1 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message message = Message.obtain();
                    //TODO: Update the message.obj with the current count
                    message.obj = count;
                    //TODO: Send the message to the uiHandler message queue
                    uiHandler.sendMessage(message);
                    //Updates the count by 1
                    count+=1;
                }
            }
        });
    }

    @Override
    protected void onDestroy(){
        worker.quitSafely();
        super.onDestroy();

    }
}
