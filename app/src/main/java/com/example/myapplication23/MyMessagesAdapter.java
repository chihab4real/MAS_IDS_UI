package com.example.myapplication23;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyMessagesAdapter extends ArrayAdapter<Message> {
    private static final  String TAG ="MyMessagesAdapter";
    private Context context;
    private int resource;
    private ArrayList<Message> messages;


    public MyMessagesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Message> liste) {
        super(context, resource, liste);
        this.context = context;
        this.resource = resource;
        this.messages = liste;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(this.resource,parent,false);

        TextView number,sender,reciever,content,time;

        number=convertView.findViewById(R.id.message_list_number);
        sender=convertView.findViewById(R.id.message_list_sender);
        reciever=convertView.findViewById(R.id.message_list_reciever);
        content=convertView.findViewById(R.id.message_list_content);
        time=convertView.findViewById(R.id.message_list_temps);

        number.setText("N°"+(position+1));
        sender.setText(messages.get(position).getSender());
        reciever.setText(messages.get(position).getReciever());
        content.setText(messages.get(position).getContent());
        time.setText(messages.get(position).getTime());




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
