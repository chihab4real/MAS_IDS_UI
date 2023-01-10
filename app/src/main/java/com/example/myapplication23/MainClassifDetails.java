package com.example.myapplication23;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;


import com.anychart.anychart.Cartesian;
import com.anychart.anychart.CartesianSeriesColumn;
import com.anychart.anychart.DataEntry;

import com.anychart.anychart.HoverMode;
import com.anychart.anychart.LegendLayout;
import com.anychart.anychart.Pie;
import com.anychart.anychart.Position;
import com.anychart.anychart.TooltipPositionMode;
import com.anychart.anychart.ValueDataEntry;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;


import java.util.ArrayList;



public class MainClassifDetails extends AppCompatActivity {




    RadarChart radarChart;
    AnyChartView barChartFM,barChartPR,barChartFP,barChartKP;
    PieChart pieChart;

    LinearLayout info_real_layout;



    RelativeLayout fmeasure_layout,pres_layout,falsep_layout,kapp_layout,all_layout,info_layout;
    TextView fmeasure_txt,pres_txt,falsep_txt,kapp_txt,all_txt,info_txt;
    
    TextView dttrain_data_size,dt_fm,dt_pre,dt_fp,dt_kappa;
    TextView svmtrain_data_size,svm_fm,svm_pre,svm_fp,svm_kappa;
    TextView nntrain_data_size,nn_fm,nn_pre,nn_fp,nn_kappa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_classif_details);
        
        dttrain_data_size = findViewById(R.id.dttrain_data_size);
        svmtrain_data_size = findViewById(R.id.svmtrain_data_size);
        nntrain_data_size = findViewById(R.id.nntrain_data_size);

        dt_fm = findViewById(R.id.dt_fm);
        svm_fm = findViewById(R.id.svm_fm);
        nn_fm = findViewById(R.id.nn_fm);

        dt_pre = findViewById(R.id.dt_pre);
        svm_pre = findViewById(R.id.svm_pre);
        nn_pre = findViewById(R.id.nn_pre);

        dt_fp = findViewById(R.id.dt_fp);
        svm_fp = findViewById(R.id.svm_fp);
        nn_fp = findViewById(R.id.nn_fp);

        dt_kappa = findViewById(R.id.dt_kappa);
        svm_kappa = findViewById(R.id.svm_kappa);
        nn_kappa = findViewById(R.id.nn_kappa);



        fmeasure_layout = findViewById(R.id.fmeasur_layout);
        pres_layout = findViewById(R.id.pres_layout);
        falsep_layout = findViewById(R.id.falsep_layout);
        kapp_layout = findViewById(R.id.kapp_layout);
        all_layout = findViewById(R.id.all_layout);
        info_layout = findViewById(R.id.info_layout);
        info_real_layout = findViewById(R.id.info_real_layout);

        fmeasure_txt = findViewById(R.id.fmeasur_txt);
        pres_txt = findViewById(R.id.pres_txt);
        falsep_txt = findViewById(R.id.falsep_txt);
        kapp_txt = findViewById(R.id.kapp_txt);
        all_txt = findViewById(R.id.all_txt);
        info_txt = findViewById(R.id.info_txt);


        radarChart = findViewById(R.id.piechart);
        barChartFM = findViewById(R.id.barchartFM);
        barChartPR = findViewById(R.id.barchartPR);
        barChartFP = findViewById(R.id.barchartFP);
        barChartKP = findViewById(R.id.barchartKP);
        //pie= AnyChart.pie();
        //pie.setBackground("#f9f8fd");
        
        dttrain_data_size.setText(String.valueOf(Stuff.DT.getTraindataSize()));
        dt_fm.setText((int)(Stuff.DT.getfMeasure()*100)+"%");
        dt_pre.setText((int)(Stuff.DT.getPrecesion()*100)+"%");
        dt_fp.setText((int)(Stuff.DT.getFalsePositive()*100)+"%");
        dt_kappa.setText((int)(Stuff.DT.getKappa()*100)+"%");

        svmtrain_data_size.setText(String.valueOf(Stuff.SVM.getTraindataSize()));
        svm_fm.setText((int)(Stuff.SVM.getfMeasure()*100)+"%");
        svm_pre.setText((int)(Stuff.SVM.getPrecesion()*100)+"%");
        svm_fp.setText((int)(Stuff.SVM.getFalsePositive()*100)+"%");
        svm_kappa.setText((int)(Stuff.SVM.getKappa()*100)+"%");

        nntrain_data_size.setText(String.valueOf(Stuff.NN.getTraindataSize()));
        nn_fm.setText((int)(Stuff.NN.getfMeasure()*100)+"%");
        nn_pre.setText((int)(Stuff.NN.getPrecesion()*100)+"%");
        nn_fp.setText((int)(Stuff.NN.getFalsePositive()*100)+"%");
        nn_kappa.setText((int)(Stuff.NN.getKappa()*100)+"%");



        doRadar();
        doBarFM();
        //doPCKTD();
        doBarPre();
        doBarFP();
        doBarKP();









        info_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info_real_layout.setVisibility(View.VISIBLE);
                barChartFM.setVisibility(View.GONE);
                barChartPR.setVisibility(View.GONE);
                barChartFP.setVisibility(View.GONE);
                barChartKP.setVisibility(View.GONE);
                radarChart.setVisibility(View.GONE);

                info_layout.setBackgroundColor(Color.parseColor("#7fb8af"));
                info_txt.setTextColor(Color.parseColor("#FFFFFF"));

                all_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                all_txt.setTextColor(Color.parseColor("#7fb8af"));

                fmeasure_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                fmeasure_txt.setTextColor(Color.parseColor("#7fb8af"));

                pres_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                pres_txt.setTextColor(Color.parseColor("#7fb8af"));


                falsep_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                falsep_txt.setTextColor(Color.parseColor("#7fb8af"));


                kapp_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                kapp_txt.setTextColor(Color.parseColor("#7fb8af"));

            }
        });


        all_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                info_real_layout.setVisibility(View.GONE);
                barChartFM.setVisibility(View.GONE);
                barChartPR.setVisibility(View.GONE);
                barChartFP.setVisibility(View.GONE);
                barChartKP.setVisibility(View.GONE);
                radarChart.setVisibility(View.VISIBLE);

                info_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                info_txt.setTextColor(Color.parseColor("#7fb8af"));

                all_layout.setBackgroundColor(Color.parseColor("#7fb8af"));
                all_txt.setTextColor(Color.parseColor("#FFFFFF"));

                fmeasure_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                fmeasure_txt.setTextColor(Color.parseColor("#7fb8af"));

                pres_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                pres_txt.setTextColor(Color.parseColor("#7fb8af"));


                falsep_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                falsep_txt.setTextColor(Color.parseColor("#7fb8af"));


                kapp_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                kapp_txt.setTextColor(Color.parseColor("#7fb8af"));


            }
        });

        fmeasure_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //9ccccf
                //doBarFM();

                info_real_layout.setVisibility(View.GONE);
                barChartPR.setVisibility(View.GONE);
                barChartFP.setVisibility(View.GONE);
                barChartKP.setVisibility(View.GONE);
                radarChart.setVisibility(View.GONE);
                barChartFM.setVisibility(View.VISIBLE);

                info_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                info_txt.setTextColor(Color.parseColor("#7fb8af"));

                all_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                all_txt.setTextColor(Color.parseColor("#7fb8af"));

                fmeasure_layout.setBackgroundColor(Color.parseColor("#7fb8af"));
                fmeasure_txt.setTextColor(Color.parseColor("#FFFFFF"));


                pres_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                pres_txt.setTextColor(Color.parseColor("#7fb8af"));


                falsep_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                falsep_txt.setTextColor(Color.parseColor("#7fb8af"));


                kapp_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                kapp_txt.setTextColor(Color.parseColor("#7fb8af"));


            }
        });

        pres_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //8d98c7
                info_real_layout.setVisibility(View.GONE);
                barChartFM.setVisibility(View.GONE);

                barChartFP.setVisibility(View.GONE);
                barChartKP.setVisibility(View.GONE);
                radarChart.setVisibility(View.GONE);
                barChartPR.setVisibility(View.VISIBLE);

                info_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                info_txt.setTextColor(Color.parseColor("#7fb8af"));

                all_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                all_txt.setTextColor(Color.parseColor("#7fb8af"));

                fmeasure_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                fmeasure_txt.setTextColor(Color.parseColor("#7fb8af"));


                pres_layout.setBackgroundColor(Color.parseColor("#7fb8af"));
                pres_txt.setTextColor(Color.parseColor("#FFFFFF"));


                falsep_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                falsep_txt.setTextColor(Color.parseColor("#7fb8af"));


                kapp_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                kapp_txt.setTextColor(Color.parseColor("#7fb8af"));
            }
        });

        falsep_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //468bff

                info_real_layout.setVisibility(View.GONE);
                barChartFM.setVisibility(View.GONE);
                barChartPR.setVisibility(View.GONE);

                barChartKP.setVisibility(View.GONE);
                radarChart.setVisibility(View.GONE);
                barChartFP.setVisibility(View.VISIBLE);

                info_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                info_txt.setTextColor(Color.parseColor("#7fb8af"));

                all_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                all_txt.setTextColor(Color.parseColor("#7fb8af"));

                fmeasure_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                fmeasure_txt.setTextColor(Color.parseColor("#7fb8af"));


                pres_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                pres_txt.setTextColor(Color.parseColor("#7fb8af"));


                falsep_layout.setBackgroundColor(Color.parseColor("#7fb8af"));
                falsep_txt.setTextColor(Color.parseColor("#FFFFFF"));


                kapp_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                kapp_txt.setTextColor(Color.parseColor("#7fb8af"));
            }
        });

        kapp_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //e84857

                info_real_layout.setVisibility(View.GONE);
                barChartFM.setVisibility(View.GONE);
                barChartPR.setVisibility(View.GONE);
                barChartFP.setVisibility(View.GONE);

                radarChart.setVisibility(View.GONE);
                barChartKP.setVisibility(View.VISIBLE);

                info_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                info_txt.setTextColor(Color.parseColor("#7fb8af"));

                all_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                all_txt.setTextColor(Color.parseColor("#7fb8af"));

                fmeasure_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                fmeasure_txt.setTextColor(Color.parseColor("#7fb8af"));


                pres_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                pres_txt.setTextColor(Color.parseColor("#7fb8af"));


                falsep_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                falsep_txt.setTextColor(Color.parseColor("#7fb8af"));


                kapp_layout.setBackgroundColor(Color.parseColor("#7fb8af"));
                kapp_txt.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });




    }

    void doRadar(){
        ArrayList<RadarEntry> entriesDT = new ArrayList<>();
        ArrayList<RadarEntry> entriesSVM = new ArrayList<>();
        ArrayList<RadarEntry> entriesNN = new ArrayList<>();

        RadarDataSet radarDataSetDT;
        RadarDataSet radarDataSetSVM;
        RadarDataSet radarDataSetNN;


        entriesDT.add(new RadarEntry((float) ((int)(Stuff.DT.getfMeasure()*100))));
        entriesDT.add(new RadarEntry((float) ((int)(Stuff.DT.getPrecesion()*100))));
        entriesDT.add(new RadarEntry((float) ((int)(Stuff.DT.getFalsePositive()*100))));
        entriesDT.add(new RadarEntry((float) ((int)(Stuff.DT.getKappa()*100))));

        radarDataSetDT = new RadarDataSet(entriesDT,"Arbre de décision");
        radarDataSetDT.setColors(Color.parseColor("#2f4259"));
        radarDataSetDT.setLineWidth(2f);
        radarDataSetDT.setDrawFilled(true);
        radarDataSetDT.setFillColor(Color.parseColor("#2f4259"));
        radarDataSetDT.setValueTextColor(Color.parseColor("#2f4259"));
        radarDataSetDT.setValueTextSize(14f);

        entriesSVM.add(new RadarEntry((float) ((int)(Stuff.SVM.getfMeasure()*100))));
        entriesSVM.add(new RadarEntry((float) ((int)(Stuff.SVM.getPrecesion()*100))));
        entriesSVM.add(new RadarEntry((float) ((int)(Stuff.SVM.getFalsePositive()*100))));
        entriesSVM.add(new RadarEntry((float) ((int)(Stuff.SVM.getKappa()*100))));

        radarDataSetSVM = new RadarDataSet(entriesSVM,"Support Vector Machine");
        radarDataSetSVM.setColors(Color.parseColor("#fe9383"));
        radarDataSetSVM.setLineWidth(2f);
        radarDataSetSVM.setDrawFilled(true);
        radarDataSetSVM.setFillColor(Color.parseColor("#fe9383"));
        radarDataSetSVM.setValueTextColor(Color.parseColor("#fe9383"));
        radarDataSetSVM.setValueTextSize(14f);


        entriesNN.add(new RadarEntry((float) ((int)(Stuff.NN.getfMeasure()*100))));
        entriesNN.add(new RadarEntry((float) ((int)(Stuff.NN.getPrecesion()*100))));
        entriesNN.add(new RadarEntry((float) ((int)(Stuff.NN.getFalsePositive()*100))));
        entriesNN.add(new RadarEntry((float) ((int)(Stuff.NN.getKappa()*100))));

        radarDataSetNN = new RadarDataSet(entriesNN,"Réseau de neurones");
        radarDataSetNN.setColors(Color.parseColor("#f3dfcd"));
        radarDataSetNN.setLineWidth(2f);
        radarDataSetNN.setDrawFilled(true);
        radarDataSetNN.setFillColor(Color.parseColor("#f3dfcd"));
        radarDataSetNN.setValueTextColor(Color.parseColor("#f3dfcd"));
        radarDataSetNN.setValueTextSize(14f);






        RadarData radarData = new RadarData();
        radarData.addDataSet(radarDataSetDT);
        radarData.addDataSet(radarDataSetSVM);
        radarData.addDataSet(radarDataSetNN);


        String[] lables = {"F-Measure","Précision","Faux positif","Kappa"};
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(lables));
        radarChart.getDescription().setText("");
        radarChart.setData(radarData);


    }

    void doBarFM(){

        Cartesian cartesian = AnyChart.column();




        ArrayList<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Arbre de décision", (int)(Stuff.DT.getfMeasure()*100)));
        data.add(new ValueDataEntry("Support Vector Machine", (int)(Stuff.SVM.getfMeasure()*100)));
        data.add(new ValueDataEntry("Réseau de neurones",  (int)(Stuff.NN.getfMeasure()*100)));

        CartesianSeriesColumn column = cartesian.column(data);

        column.setColor("#98c1d9");










        column.getTooltip()
                .setTitleFormat("{%X}")
                .setPosition(Position.CENTER_BOTTOM)
                .setOffsetX(0d)
                .setOffsetY(5d);



        cartesian.setAnimation(true);

        cartesian.setTitle(" ");


        cartesian.getYScale().setMinimum(0d);
        cartesian.getYScale().setMaximum(100d);
        //cartesian.getYAxis().getLabels().setFormat("${%Value}{groupsSeparator: }");







        cartesian.getTooltip().setPositionMode(TooltipPositionMode.POINT);
        cartesian.getInteractivity().setHoverMode(HoverMode.BY_X);

        cartesian.getXAxis().setTitle("Classifcateurs");


        cartesian.getYAxis().setTitle("F-Measure");









        barChartFM.setChart(cartesian);


    }

    void doBarPre(){

        Cartesian cartesian = AnyChart.column();


        ArrayList<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Arbre de décision", (int)(Stuff.DT.getPrecesion()*100)));
        data.add(new ValueDataEntry("Support Vector Machine", (int)(Stuff.SVM.getPrecesion()*100)));
        data.add(new ValueDataEntry("Réseau de neurones",  (int)(Stuff.NN.getPrecesion()*100)));

        CartesianSeriesColumn column = cartesian.column(data);

        column.setColor("#98c1d9");





        column.getTooltip()
                .setTitleFormat("{%X}")
                .setPosition(Position.CENTER_BOTTOM)
                .setOffsetX(0d)
                .setOffsetY(5d);



        cartesian.setAnimation(true);
        cartesian.setTitle(" ");


        cartesian.getYScale().setMinimum(0d);
        cartesian.getYScale().setMaximum(100d);
        //cartesian.getYAxis().getLabels().setFormat("${%Value}{groupsSeparator: }");






        cartesian.getTooltip().setPositionMode(TooltipPositionMode.POINT);
        cartesian.getInteractivity().setHoverMode(HoverMode.BY_X);

        cartesian.getXAxis().setTitle("Classifcateurs");


        cartesian.getYAxis().setTitle("Précision");








        barChartPR.setChart(cartesian);


    }

    void doBarFP(){

        Cartesian cartesian = AnyChart.column();


        ArrayList<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Arbre de décision", (int)(Stuff.DT.getFalsePositive()*100)));
        data.add(new ValueDataEntry("Support Vector Machine", (int)(Stuff.SVM.getFalsePositive()*100)));
        data.add(new ValueDataEntry("Réseau de neurones",  (int)(Stuff.NN.getFalsePositive()*100)));

        CartesianSeriesColumn column = cartesian.column(data);

        column.setColor("#98c1d9");





        column.getTooltip()
                .setTitleFormat("{%X}")
                .setPosition(Position.CENTER_BOTTOM)
                .setOffsetX(0d)
                .setOffsetY(5d);



        cartesian.setAnimation(true);
        cartesian.setTitle(" ");


        cartesian.getYScale().setMinimum(0d);
        cartesian.getYScale().setMaximum(100d);
        //cartesian.getYAxis().getLabels().setFormat("${%Value}{groupsSeparator: }");






        cartesian.getTooltip().setPositionMode(TooltipPositionMode.POINT);
        cartesian.getInteractivity().setHoverMode(HoverMode.BY_X);

        cartesian.getXAxis().setTitle("Classifcateurs");


        cartesian.getYAxis().setTitle("Faux positif");








        barChartFP.setChart(cartesian);


    }

    void doBarKP(){

        Cartesian cartesian = AnyChart.column();


        ArrayList<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Arbre de décision", (int)(Stuff.DT.getKappa()*100)));
        data.add(new ValueDataEntry("Support Vector Machine", (int)(Stuff.SVM.getKappa()*100)));
        data.add(new ValueDataEntry("Réseau de neurones",  (int)(Stuff.NN.getKappa()*100)));

        CartesianSeriesColumn column = cartesian.column(data);

        column.setColor("#98c1d9");





        column.getTooltip()
                .setTitleFormat("{%X}")
                .setPosition(Position.CENTER_BOTTOM)
                .setOffsetX(0d)
                .setOffsetY(5d);



        cartesian.setAnimation(true);
        cartesian.setTitle(" ");


        cartesian.getYScale().setMinimum(0d);
        cartesian.getYScale().setMaximum(100d);
        //cartesian.getYAxis().getLabels().setFormat("${%Value}{groupsSeparator: }");






        cartesian.getTooltip().setPositionMode(TooltipPositionMode.POINT);
        cartesian.getInteractivity().setHoverMode(HoverMode.BY_X);

        cartesian.getXAxis().setTitle("Classifcateurs");


        cartesian.getYAxis().setTitle("F-Measure");









        barChartKP.setChart(cartesian);


    }

    void doPCKTD(){
        Pie pie = AnyChart.pie();

        ArrayList<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Normal", 100));
        data.add(new ValueDataEntry("Anomalie", 150));


        pie.data(data);






        pie.getLabels().setPosition("outside");

        pie.getLegend().getTitle().setEnabled(true);
        pie.getLegend().getTitle()
                .setText("Retail channels")
                .setPadding(0d, 0d, 10d, 0d);

        pie.getLegend()
                .setPosition("center-bottom")
                .setItemsLayout(LegendLayout.HORIZONTAL)
                .setAlign(String.valueOf(Paint.Align.CENTER));


        String[] s={"#FFFFFF","#FFFFFF","#FFFFFF"};

        pie.getPalette().setItems(s,"#FFFFFF");

        barChartFM.setChart(pie);

    }




}