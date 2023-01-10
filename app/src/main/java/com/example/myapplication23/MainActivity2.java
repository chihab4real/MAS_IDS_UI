package com.example.myapplication23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity2 extends AppCompatActivity {

    TextView dttxt,svmtxt,nntxt;

    ProgressBar dtpgb,svmpgb,nnpgb;
    ImageView dtimg,svmimg,nnimg;







    LinearLayout acces;

    ScrollView scroll;







    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        MessageSender messageSender = new MessageSender();
        messageSender.execute("Start");

        dttxt = findViewById(R.id.dttxt);
        svmtxt = findViewById(R.id.svmtxt);
        nntxt = findViewById(R.id.nntxt);

        dtpgb = findViewById(R.id.dtpgb);
        svmpgb = findViewById(R.id.svmpgb);
        nnpgb = findViewById(R.id.nnpgb);

        dtimg = findViewById(R.id.dtimg);
        svmimg = findViewById(R.id.svmimg);
        nnimg = findViewById(R.id.nnimg);

        acces = findViewById(R.id.accederplt);
        scroll = findViewById(R.id.scroll);


        Thread myThread = new Thread(new MyServerThread());
        myThread.start();

        acces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                startActivity(new Intent(MainActivity2.this,MainActivity3.class));
                finish();
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

                    s.close();

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                           if(message.contains("DT")){
                               //Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                               doDT(message);
                           }

                            if(message.contains("SVM")){
                                //Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                                doSVM(message);
                            }

                            if(message.contains("NN")){
                                //Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                                doNN(message);
                                acces.setVisibility(View.VISIBLE);
                                scroll.fullScroll(View.FOCUS_DOWN);






                            }



                        }
                    });

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }

    void doDT(String message){
        dttxt.setText("Entrainement: Terminer");
        dtpgb.setVisibility(View.GONE);
        dtimg.setVisibility(View.VISIBLE);

        String newm = message.replace("DT,","");
        String fm,tr,pr;

        tr = newm.substring(newm.indexOf("TR"),newm.indexOf("FM"));
        tr = tr.replace(",","");

        fm = newm.substring(newm.indexOf("FM"),newm.indexOf("PR"));
        fm = fm.replace(",","");

        pr = newm.substring(newm.indexOf("PR"),newm.length()-1);



        /*dttraintxt.setText("\t\tLa taille de TrainData: "+tr.replace("TR",""));
        dtfmtxt.setText("\t\tF-Measure: "+(int)(Double.parseDouble(fm.replace("FM",""))*100)+"%");
        dtpretxt.setText("\t\t Precision: "+(int)(Double.parseDouble(pr.replace("PR",""))*100)+"%");*/

        Stuff.DT.setName("DT");
        Stuff.DT.setTraindataSize(Integer.parseInt(tr.replace("TR","")));
        Stuff.DT.setfMeasure(Double.parseDouble(fm.replace("FM","")));
        Stuff.DT.setPrecesion(Double.parseDouble(pr.replace("PR","")));



    }

    void doNN(String message){
        nntxt.setText("Entrainement: Terminer");
        nnpgb.setVisibility(View.GONE);
        nnimg.setVisibility(View.VISIBLE);

        String newm = message.replace("NN,","");
        String fm,tr,pr;

        tr = newm.substring(newm.indexOf("TR"),newm.indexOf("FM"));
        tr = tr.replace(",","");

        fm = newm.substring(newm.indexOf("FM"),newm.indexOf("PR"));
        fm = fm.replace(",","");

        pr = newm.substring(newm.indexOf("PR"),newm.length()-1);



        /*nntraintxt.setText("\t\tLa taille de TrainData: "+tr.replace("TR",""));
        nnfmtxt.setText("\t\tF-Measure: "+(int)(Double.parseDouble(fm.replace("FM",""))*100)+"%");
        nnpretxt.setText("\t\t Precision: "+(int)(Double.parseDouble(pr.replace("PR",""))*100)+"%");*/

        Stuff.NN.setName("NN");
        Stuff.NN.setTraindataSize(Integer.parseInt(tr.replace("TR","")));
        Stuff.NN.setfMeasure(Double.parseDouble(fm.replace("FM","")));
        Stuff.NN.setPrecesion(Double.parseDouble(pr.replace("PR","")));



    }

    void doSVM(String message){
        svmtxt.setText("Entrainement: Terminer");
        svmpgb.setVisibility(View.GONE);
        svmimg.setVisibility(View.VISIBLE);

        String newm = message.replace("SVM,","");
        String fm,tr,pr;

        tr = newm.substring(newm.indexOf("TR"),newm.indexOf("FM"));
        tr = tr.replace(",","");

        fm = newm.substring(newm.indexOf("FM"),newm.indexOf("PR"));
        fm = fm.replace(",","");

        pr = newm.substring(newm.indexOf("PR"),newm.length()-1);



        /*svmtraintxt.setText("\t\tLa taille de TrainData: "+tr.replace("TR",""));
        svmfmtxt.setText("\t\tF-Measure: "+(int)(Double.parseDouble(fm.replace("FM",""))*100)+"%");
        svmpretxt.setText("\t\t Precision: "+(int)(Double.parseDouble(pr.replace("PR",""))*100)+"%");*/


        Stuff.SVM.setName("SVM");
        Stuff.SVM.setTraindataSize(Integer.parseInt(tr.replace("TR","")));
        Stuff.SVM.setfMeasure(Double.parseDouble(fm.replace("FM","")));
        Stuff.SVM.setPrecesion(Double.parseDouble(pr.replace("PR","")));


    }

}