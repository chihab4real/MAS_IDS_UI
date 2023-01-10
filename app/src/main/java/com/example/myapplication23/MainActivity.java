package com.example.myapplication23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    RelativeLayout button;
    String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.start);



        //Thread myThread = new Thread(new MyServerThread());
        //myThread.start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,MainActivity3.class));
                finish();
                //MessageSender messageSender = new MessageSender();
                //messageSender.execute("Start");
            }
        });





    }

    public class MyServerThread implements Runnable{
        Socket s;
        ServerSocket ss;
        InputStreamReader isr;
        PrintWriter pw;
        BufferedReader bufferedReader;
        String message;

        Handler handler = new Handler();
        public MyServerThread()  {
        }

        @Override
        public void run() {
            try {
                ss = new ServerSocket(7801);
                while (true){
                    s =ss.accept();
                    isr = new InputStreamReader(s.getInputStream());
                    bufferedReader = new BufferedReader(isr);
                    message = bufferedReader.readLine();

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();


                        }
                    });

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}