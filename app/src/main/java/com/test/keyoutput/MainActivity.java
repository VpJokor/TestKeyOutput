package com.test.keyoutput;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text=findViewById(R.id.Text_content);//获取文本对象
        String a="我喜欢你就像是烟火升空，会在心里每一个小地方，都绽放小小的烟花，在心里噼里啪啦的炸开。";//需要打印的文字
        Handler handler=new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg){
                if( msg.what==1 ){
                    text.setText(msg.obj+"");
                }//判断信息是否是对应线程传递过来的
            }
        };//Handler用于子线程同主线程即UI线程通讯
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                if ( i < a.length()) {
                    Message msg=new Message();
                    msg.what=1;//用于标识线程
                    msg.obj=a.substring(0,i+1);//传递的信息
                    handler.sendMessage(msg);//传递信息
                    i++;
                }else timer.cancel();//取消定时器
            }
        },1000,100);//设定一个定时器，每隔100ms运行一次run()方法。第一次延时1s后执行
    }
}