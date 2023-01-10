package com.example.myapplication23;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.LegendLayout;
import com.anychart.anychart.Pie;
import com.anychart.anychart.ValueDataEntry;

import java.util.ArrayList;

public class MainContainerInfo extends AppCompatActivity {

    AnyChartView packets_detetcted;
    AnyChartView catego_anomly;
    AnyChartView algo_used;
    MessageContainer messageContainer;
    TextView total_packets;
    TextView numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container_info);

        messageContainer = Stuff.messageContainer;
        int x = Stuff.machinenumber;

        packets_detetcted = findViewById(R.id.packets_detected);
        numero = findViewById(R.id.machine_number);
        catego_anomly = findViewById(R.id.catego_anomal);
        algo_used = findViewById(R.id.algos_used);
        total_packets = findViewById(R.id.total_packets);
        total_packets.setText(messageContainer.getTotalPackets()+" Packets");
        numero.setText("Machine numéro "+x);
        doPCKTD();
        doCTGANM();
        doALGO();
    }

    void doPCKTD(){
        Pie pie = AnyChart.pie();

        ArrayList<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Normal", messageContainer.getNormalNumber()));
        data.add(new ValueDataEntry("Anomalie", messageContainer.getAnomalyNumber()));


        pie.data(data);






        pie.getLabels().setPosition("outside");

        pie.getLegend().getTitle().setEnabled(true);
        pie.getLegend().getTitle()
                .setText(" ")
                .setPadding(0d, 0d, 10d, 0d);

        pie.getLegend()
                .setPosition("center-bottom")
                .setItemsLayout(LegendLayout.HORIZONTAL)
                .setAlign(String.valueOf(Paint.Align.CENTER));


        String[] s={"#26b99a","#e84857"};

        pie.getPalette().setItems(s,"#FFFFFF");




        packets_detetcted.setChart(pie);

    }

    void doCTGANM(){

        //Normal,DOS,Probe,R2L,U2R
        Pie pie = AnyChart.pie();

        ArrayList<DataEntry> data = new ArrayList<>();
        //data.add(new ValueDataEntry("Normal", 300));
        data.add(new ValueDataEntry("DOS", messageContainer.getDosNumber()));
        data.add(new ValueDataEntry("Probe", messageContainer.getProbeNumber()));
        data.add(new ValueDataEntry("R2L", messageContainer.getR2LNumber()));
        data.add(new ValueDataEntry("U2R", messageContainer.getU2RNumber()));


        pie.data(data);






        pie.getLabels().setPosition("outside");

        pie.getLegend().getTitle().setEnabled(true);
        pie.getLegend().getTitle()
                .setText(" ")
                .setPadding(0d, 0d, 10d, 0d);

        pie.getLegend()
                .setPosition("center-bottom")
                .setItemsLayout(LegendLayout.HORIZONTAL)
                .setAlign(String.valueOf(Paint.Align.CENTER));


        String[] s={"#003049","#d62828","#f77f00","#eae2b7"};

        pie.getPalette().setItems(s,"#FFFFFF");

        catego_anomly.setChart(pie);

    }

    void doALGO(){
        Pie pie = AnyChart.pie();

        ArrayList<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("DT", messageContainer.getDTNumber()));
        data.add(new ValueDataEntry("SVM", messageContainer.getSVMNumber()));
        data.add(new ValueDataEntry("NN", messageContainer.getNNNumber()));


        pie.data(data);






        pie.getLabels().setPosition("outside");

        pie.getLegend().getTitle().setEnabled(true);
        pie.getLegend().getTitle()
                .setText(" ")
                .setPadding(0d, 0d, 10d, 0d);

        pie.getLegend()
                .setPosition("center-bottom")
                .setItemsLayout(LegendLayout.HORIZONTAL)
                .setAlign(String.valueOf(Paint.Align.CENTER));


        String[] s={"#2f4259","#fe9383","#f3dfcd"};

        pie.getPalette().setItems(s,"#FFFFFF");

        algo_used.setChart(pie);

    }
}