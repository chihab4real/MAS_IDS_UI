package com.example.myapplication23;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication23.ui.agents.AgentsFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAgentsAdapter extends ArrayAdapter<MessageContainer> {
    private static final  String TAG ="MyAgentsAdapter";
    private Context context;
    private int resource;
    private ArrayList<MessageContainer> containerForAdapters;


    public MyAgentsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MessageContainer> liste) {
        super(context, resource, liste);
        this.context = context;
        this.resource = resource;
        this.containerForAdapters = liste;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(this.resource,parent,false);


        TextView number;
        TextView stat_sub;
        TextView stat_sniff;
        TextView stat_analysor;
        TextView nb_pckts;
        TextView nb_anom;
        ProgressBar progressBar;
        TextView progresstxt;

        LinearLayout details;

        number = convertView.findViewById(R.id.number);
        stat_sub = convertView.findViewById(R.id.agent_list_sub_stat);
        stat_sniff = convertView.findViewById(R.id.agent_list_sniff_stat);
        stat_analysor = convertView.findViewById(R.id.agent_list_anlysor_stat);
        nb_pckts = convertView.findViewById(R.id.agent_list_nb_pckt);
        nb_anom = convertView.findViewById(R.id.agent_list_nb_anom);
        progressBar = convertView.findViewById(R.id.agent_list_progress);
        progresstxt = convertView.findViewById(R.id.agent_list_percentage_anom);
        details = convertView.findViewById(R.id.agent_list_details);

        number.setText("Machine Numero\t\t"+containerForAdapters.get(position).getMachineNumber());
        nb_pckts.setText(String.valueOf(containerForAdapters.get(position).getTotalPackets())+" packets");
        nb_anom.setText(String.valueOf(containerForAdapters.get(position).getAnomalyNumber())+" anomalies");

        progressBar.setProgress(containerForAdapters.get(position).getAnomalyNumber()*100/containerForAdapters.get(position).getTotalPackets());
        progresstxt.setText(containerForAdapters.get(position).getAnomalyNumber()*100/containerForAdapters.get(position).getTotalPackets()+"%");


        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Stuff.messageContainer = containerForAdapters.get(position);
                Stuff.machinenumber = position+1;
                context.startActivity(new Intent(context,MainContainerInfo.class));
                //MessageSender messageSender1 = new MessageSender();
                //messageSender1.execute("Start");
            }
        });





        return convertView;
    }



    public static int convertDpToPixels(float dp, Context context){
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                resources.getDisplayMetrics()
        );
    }
}
