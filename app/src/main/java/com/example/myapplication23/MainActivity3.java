package com.example.myapplication23;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

import weka.gui.Main;

public class MainActivity3 extends AppCompatActivity {


    RelativeLayout acc_layout,plat_layout,para_layout;
    ScrollView Scrollacc,Scrollplat,Scrollpara;

    TextView acc_txt,plat_txt,para_txt;
    TextView number_machines,time_start,time_trainig;
    ConstraintLayout line;


    ListView listView;
    MyAgentsAdapter myAgentsAdapter;
    ArrayList<MessageContainer> arrayList;



    //LinearLayout dt_details,svm_details,nn_details;



    TextView dtfmtxt,svmfmtxt,nnfmtxt;
    TextView dtpretxt,svmpretxt,nnpretxt;

    LinearLayout refresh,shutdown,retrain,getmessages;

    ProgressBar dtpgb,svmpgb,nnpgb;

    LinearLayout cls_details;

    int clicked=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        MessageSender messageSender1 = new MessageSender();
        messageSender1.execute("Start");

        cls_details = findViewById(R.id.cls_details);
        shutdown = findViewById(R.id.shutdown);
        retrain = findViewById(R.id.retrain);
        getmessages = findViewById(R.id.agents_messages);




        refresh= findViewById(R.id.refresh);
        dtpgb= findViewById(R.id.dtpgb);
        svmpgb= findViewById(R.id.svmpgb);
        nnpgb= findViewById(R.id.nnpgb);

        number_machines= findViewById(R.id.number_machines);
        time_start= findViewById(R.id.lunsh_time);
        time_trainig= findViewById(R.id.training_time);




        scrollvieewAccu();
        acc_layout = findViewById(R.id.acc_layout);
        plat_layout = findViewById(R.id.plat_layout);
        para_layout = findViewById(R.id.para_layout);
        line = findViewById(R.id.biglayout);

        Scrollacc = findViewById(R.id.scrollAcc);
        Scrollplat = findViewById(R.id.scrollPlat);
        Scrollpara = findViewById(R.id.scrollPara);

        acc_txt = findViewById(R.id.acc_txt);
        plat_txt = findViewById(R.id.plat_txt);
        para_txt = findViewById(R.id.para_txt);

        listView=findViewById(R.id.listofcontainers);


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity3.this,MainContainerInfo.class));



                MessageSender messageSender = new MessageSender();
                messageSender.execute("getAgents");
            }
        });

        myAnimation();



        cls_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this,MainClassifDetails.class));
            }
        });

        shutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SHUTDOWN
                MessageSender messageSender = new MessageSender();
                messageSender.execute("shutdown");
            }
        });

        retrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RETRAIN
                cls_details.setVisibility(View.GONE);
                dtpgb.setVisibility(View.VISIBLE);
                dtfmtxt.setText("Entraînement en cours...");
                dtpretxt.setText("");
                svmpgb.setVisibility(View.VISIBLE);
                svmfmtxt.setText("Entraînement en cours...");
                svmpretxt.setText("");
                nnpgb.setVisibility(View.VISIBLE);
                nnfmtxt.setText("Entraînement en cours...");
                nnpretxt.setText("");
                MessageSender messageSender = new MessageSender();
                messageSender.execute("retrain");
            }
        });

        getmessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //GETMESSAGES
                /*Stuff.messages=new ArrayList<>();
                Stuff.messages.add(new Message("Manager","Classify","GetTrain","20:20:20"));
                Stuff.messages.add(new Message("Manager","Classify","GetTrain","20:20:20"));
                Stuff.messages.add(new Message("Manager","Classify","GetTrain","20:20:20"));
                Stuff.messages.add(new Message("Manager","Classify","GetTrain","20:20:20"));
                startActivity(new Intent(MainActivity3.this,MainMessages.class));*/
                MessageSender messageSender = new MessageSender();
                messageSender.execute("getMessages");
            }
        });

        Thread myThread = new Thread(new MyServerThread());
        myThread.start();




    }


    void myAnimation(){

        String mauve,vert,mauve2;

        mauve="#8d98c7";
        vert="#9ccccf";
        mauve2="#506874";
        acc_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(clicked!=1){

                    line.setBackgroundColor(Color.parseColor(vert));
                    acc_txt.setTextColor(Color.parseColor("#FFFFFF"));
                    acc_layout.setBackgroundColor(Color.parseColor(vert));

                    plat_txt.setTextColor(Color.parseColor(vert));
                    plat_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    para_txt.setTextColor(Color.parseColor(vert));
                    para_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    if(clicked==2){
                        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
                        Scrollplat.startAnimation(animation1);
                        Scrollplat.setVisibility(View.GONE);
                    }

                    if(clicked==3){
                        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
                        Scrollpara.startAnimation(animation1);
                        Scrollpara.setVisibility(View.GONE);
                    }


                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_left);
                    Scrollacc.setVisibility(View.VISIBLE);
                    Scrollacc.startAnimation(animation);


                }


                clicked=1;






            }
        });


        plat_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(clicked!=2){
                    line.setBackgroundColor(Color.parseColor(mauve));
                    acc_txt.setTextColor(Color.parseColor(mauve));
                    acc_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    plat_txt.setTextColor(Color.parseColor("#FFFFFF"));
                    plat_layout.setBackgroundColor(Color.parseColor(mauve));

                    para_txt.setTextColor(Color.parseColor(mauve));
                    para_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    if(clicked==1){
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_left);
                        Scrollacc.startAnimation(animation);
                        Scrollacc.setVisibility(View.GONE);

                        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                        Scrollplat.setVisibility(View.VISIBLE);
                        Scrollplat.startAnimation(animation2);


                    }

                    if(clicked==3){

                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
                        Scrollpara.startAnimation(animation);
                        Scrollpara.setVisibility(View.GONE);

                        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_left);
                        Scrollplat.setVisibility(View.VISIBLE);
                        Scrollplat.startAnimation(animation2);

                    }




                    clicked=2;

                }



            }
        });

        para_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(clicked!=3){
                    acc_txt.setTextColor(Color.parseColor(mauve2));
                    acc_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    line.setBackgroundColor(Color.parseColor(mauve2));

                    plat_txt.setTextColor(Color.parseColor(mauve2));
                    plat_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    para_txt.setTextColor(Color.parseColor("#FFFFFF"));
                    para_layout.setBackgroundColor(Color.parseColor(mauve2));


                    if(clicked==1){
                        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_left);
                        Scrollacc.startAnimation(animation1);
                        Scrollacc.setVisibility(View.GONE);
                    }

                    if(clicked==2){
                        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_left);
                        Scrollplat.startAnimation(animation1);
                        Scrollplat.setVisibility(View.GONE);
                    }



                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                    Scrollpara.setVisibility(View.VISIBLE);
                    Scrollpara.startAnimation(animation);
                }

                clicked=3;




            }
        });
    }

    void scrollvieewAccu(){
        ///////////////////SCROLL ACC///////////////////////


        dtfmtxt = findViewById(R.id.dtfmtxt);
        svmfmtxt = findViewById(R.id.svmfmtxt);
        nnfmtxt = findViewById(R.id.nnfmtxt);

        dtpretxt = findViewById(R.id.dtpretxt);
        svmpretxt = findViewById(R.id.svmpretxt);
        nnpretxt = findViewById(R.id.nnpretxt);




        ///////////////////////////////////////////////////////////////////
    }
    public  void methode(float sizeAdded){

        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height=0;
        layoutParams.height+= convertDpToPixels(sizeAdded,getApplicationContext()); //this is in pixels
        listView.setLayoutParams(layoutParams);
    }

    public static int convertDpToPixels(float dp, Context context){
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                resources.getDisplayMetrics()
        );
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
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void run() {

                            if(message.contains("LTime")){
                                String x = message.replace("LTime:","");
                                time_start.setText(x);
                            }

                            if(message.contains("DTOK,")){
                                //Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                                doDT(message);
                            }

                            if(message.contains("SVMOK,")){
                                //Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                                doSVM(message);
                            }

                            if(message.contains("NNOK,")){
                                //Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                                doNN(message);
                                LocalDateTime localDateTime = LocalDateTime.now();
                                time_trainig.setText(localDateTime.getHour()+":"+localDateTime.getMinute()+":"+localDateTime.getSecond());
                                cls_details.setVisibility(View.VISIBLE);


                            }

                            if(message.contains("GA:")){
                                decr(message);
                            }

                            if(message.contains("GM:")){
                                String x = ""+message.charAt(0)+""+message.charAt(1)+message.charAt(2);
                                if(x.equals("GM:")){
                                    decrM(message);
                                    startActivity(new Intent(MainActivity3.this,MainMessages.class));
                                }

                            }



                            //Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                            //decr(message);



                        }
                    });

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    void decr(String message){
        //String x="GA:N1,SSA,SSNA,SAA,NP120,NA60|"+"N2,SSA,SSNA,SAA,NP130,NA60|"+"N3,SSA,SSNA,SAA,NP20,NA5|";

        Stuff.messageContainers=new ArrayList<>();

        message=message.replace("GA:","");
        //ArrayList<String> arrayList = new ArrayList<>();
        /*
        String word="";
        for(int i=0;i<message.length();i++){
            if(message.charAt(i)=='|'){
                Stuff.strings.add(word);
                word="";
            }else{
                word+=message.charAt(i);
            }
        }*/

        String word="";
        for(int i=0;i<message.length();i++){
            if(message.charAt(i)=='|'){
                Stuff.messageContainers.add(new MessageContainer(word));
                word="";
            }else{
                word+=message.charAt(i);
            }
        }





        arrayList = Stuff.messageContainers;

        number_machines.setText(arrayList.size()+" Machines");

        methode((arrayList.size()*250)+5);


        myAgentsAdapter = new MyAgentsAdapter(MainActivity3.this, R.layout.agent_list,arrayList);
        myAgentsAdapter.notifyDataSetChanged();
        listView.setAdapter(null);





        listView.setAdapter(myAgentsAdapter);


/*



        String number =k.substring(k.indexOf("N"),k.indexOf("SS")-1);

        number=number.replace("N_","");


        String ss=k.substring(k.indexOf("SS"),k.indexOf("SSN")-1);
        ss=ss.replace("SS_","");



        String ssn=k.substring(k.indexOf("SSN"),k.indexOf("SA")-1);
        ssn=ssn.replace("SSN_","");



        String sa=k.substring(k.indexOf("SA"),k.indexOf("NP")-1);
        sa=sa.replace("SA_","");

        String np=k.substring(k.indexOf("NP"),k.indexOf("NA")-1);
        np=np.replace("NP_","");

        String na=k.substring(k.indexOf("NA"),k.length()-1);
        na=na.replace("NA_","");

        System.out.println(k+"\n"+number+"\n"+ss+"\n"+ssn+"\n"+sa+"\n"+np+"\n"+na+"\n");*/

    }

    void decrM(String message){
        //String x="GA:N1,SSA,SSNA,SAA,NP120,NA60|"+"N2,SSA,SSNA,SAA,NP130,NA60|"+"N3,SSA,SSNA,SAA,NP20,NA5|";

        Stuff.messages=new ArrayList<>();

        message=message.replace("GM:","");


        String word="";
        for(int i=0;i<message.length();i++){
            if(message.charAt(i)=='|'){
                Stuff.messages.add(new Message(word));
                word="";
            }else{
                word+=message.charAt(i);
            }
        }






    }

    void doDT(String message){

        dtpgb.setVisibility(View.GONE);

        message = message.replace("DTOK,","");
        String word="";
        ArrayList<String> arrayList=new ArrayList<>();
        for(int i=0;i<message.length();i++){
            if(message.charAt(i)==','){
                arrayList.add(word);
                word="";
            }else{
                word+=message.charAt(i);
            }
        }

        //dttraintxt.setText("\t\tLa taille de TrainData: "+tr.replace("TR",""));
        Stuff.DT.setName("DT");
        Stuff.DT.setTraindataSize(Integer.parseInt(arrayList.get(0)));
        Stuff.DT.setfMeasure(Double.parseDouble(arrayList.get(1)));
        Stuff.DT.setPrecesion(Double.parseDouble(arrayList.get(2)));
        Stuff.DT.setFalsePositive(Double.parseDouble(arrayList.get(3)));
        Stuff.DT.setKappa(Double.parseDouble(arrayList.get(4)));
        dtfmtxt.setText("\t\tF-Measure: "+(int)Stuff.DT.getfMeasure()*100+"%");
        dtpretxt.setText("\t\t Précision: "+(int)Stuff.DT.getPrecesion()*100+"%");




        /*
        String newm = message.replace("DT,","");
        String fm,tr,pr;

        tr = newm.substring(newm.indexOf("TR"),newm.indexOf("FM"));
        tr = tr.replace(",","");

        fm = newm.substring(newm.indexOf("FM"),newm.indexOf("PR"));
        fm = fm.replace(",","");

        pr = newm.substring(newm.indexOf("PR"),newm.length()-1);



        //dttraintxt.setText("\t\tLa taille de TrainData: "+tr.replace("TR",""));
        dtfmtxt.setText("\t\tF-Measure: "+(int)(Double.parseDouble(fm.replace("FM",""))*100)+"%");
        dtpretxt.setText("\t\t Precision: "+(int)(Double.parseDouble(pr.replace("PR",""))*100)+"%");

        Stuff.DT.setName("DT");
        Stuff.DT.setTraindataSize(Integer.parseInt(tr.replace("TR","")));
        Stuff.DT.setfMeasure(Double.parseDouble(fm.replace("FM","")));
        Stuff.DT.setPrecesion(Double.parseDouble(pr.replace("PR","")));*/



    }

    void doNN(String message){
        /*

        nnpgb.setVisibility(View.GONE);


        String newm = message.replace("NN,","");
        String fm,tr,pr;

        tr = newm.substring(newm.indexOf("TR"),newm.indexOf("FM"));
        tr = tr.replace(",","");

        fm = newm.substring(newm.indexOf("FM"),newm.indexOf("PR"));
        fm = fm.replace(",","");

        pr = newm.substring(newm.indexOf("PR"),newm.length()-1);



        //nntraintxt.setText("\t\tLa taille de TrainData: "+tr.replace("TR",""));
        nnfmtxt.setText("\t\tF-Measure: "+(int)(Double.parseDouble(fm.replace("FM",""))*100)+"%");
        nnpretxt.setText("\t\t Precision: "+(int)(Double.parseDouble(pr.replace("PR",""))*100)+"%");

        Stuff.NN.setName("NN");
        Stuff.NN.setTraindataSize(Integer.parseInt(tr.replace("TR","")));
        Stuff.NN.setfMeasure(Double.parseDouble(fm.replace("FM","")));
        Stuff.NN.setPrecesion(Double.parseDouble(pr.replace("PR","")));*/

        nnpgb.setVisibility(View.GONE);

        message = message.replace("NNOK,","");
        String word="";
        ArrayList<String> arrayList=new ArrayList<>();
        for(int i=0;i<message.length();i++){
            if(message.charAt(i)==','){
                arrayList.add(word);
                word="";
            }else{
                word+=message.charAt(i);
            }
        }

        //dttraintxt.setText("\t\tLa taille de TrainData: "+tr.replace("TR",""));
        Stuff.NN.setName("NN");
        Stuff.NN.setTraindataSize(Integer.parseInt(arrayList.get(0)));
        Stuff.NN.setfMeasure(Double.parseDouble(arrayList.get(1)));
        Stuff.NN.setPrecesion(Double.parseDouble(arrayList.get(2)));
        Stuff.NN.setFalsePositive(Double.parseDouble(arrayList.get(3)));
        Stuff.NN.setKappa(Double.parseDouble(arrayList.get(4)));

        nnfmtxt.setText("\t\tF-Measure: "+(int)(Stuff.NN.getfMeasure()*100)+"%");
        nnpretxt.setText("\t\t Précision: "+(int)(Stuff.NN.getPrecesion()*100)+"%");




    }

    void doSVM(String message){

        /*
        svmpgb.setVisibility(View.GONE);


        String newm = message.replace("SVM,","");
        String fm,tr,pr;

        tr = newm.substring(newm.indexOf("TR"),newm.indexOf("FM"));
        tr = tr.replace(",","");

        fm = newm.substring(newm.indexOf("FM"),newm.indexOf("PR"));
        fm = fm.replace(",","");

        pr = newm.substring(newm.indexOf("PR"),newm.length()-1);



        //svmtraintxt.setText("\t\tLa taille de TrainData: "+tr.replace("TR",""));
        svmfmtxt.setText("\t\tF-Measure: "+(int)(Double.parseDouble(fm.replace("FM",""))*100)+"%");
        svmpretxt.setText("\t\t Precision: "+(int)(Double.parseDouble(pr.replace("PR",""))*100)+"%");


        Stuff.SVM.setName("SVM");
        Stuff.SVM.setTraindataSize(Integer.parseInt(tr.replace("TR","")));
        Stuff.SVM.setfMeasure(Double.parseDouble(fm.replace("FM","")));
        Stuff.SVM.setPrecesion(Double.parseDouble(pr.replace("PR","")));*/


        svmpgb.setVisibility(View.GONE);

        message = message.replace("SVMOK,","");
        String word="";
        ArrayList<String> arrayList=new ArrayList<>();
        for(int i=0;i<message.length();i++){
            if(message.charAt(i)==','){
                arrayList.add(word);
                word="";
            }else{
                word+=message.charAt(i);
            }
        }

        //dttraintxt.setText("\t\tLa taille de TrainData: "+tr.replace("TR",""));
        Stuff.SVM.setName("SVM");
        Stuff.SVM.setTraindataSize(Integer.parseInt(arrayList.get(0)));
        Stuff.SVM.setfMeasure(Double.parseDouble(arrayList.get(1)));
        Stuff.SVM.setPrecesion(Double.parseDouble(arrayList.get(2)));
        Stuff.SVM.setFalsePositive(Double.parseDouble(arrayList.get(3)));
        Stuff.SVM.setKappa(Double.parseDouble(arrayList.get(4)));
        svmfmtxt.setText("\t\tF-Measure: "+(int)(Stuff.SVM.getfMeasure()*100)+"%");
        svmpretxt.setText("\t\t Précision: "+(int)(Stuff.SVM.getPrecesion()*100)+"%");


    }

}