package com.example.myapplication23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class MainMessages extends AppCompatActivity {

    ArrayList<Message> arrayList;
    MyMessagesAdapter myMessagesAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_messages);

        listView = findViewById(R.id.messages_list);
        arrayList = Stuff.messages;



        methode((arrayList.size()*100)+5);


        myMessagesAdapter = new MyMessagesAdapter(MainMessages.this, R.layout.message_list2,arrayList);
        myMessagesAdapter.notifyDataSetChanged();
        listView.setAdapter(null);





        listView.setAdapter(myMessagesAdapter);
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
}