package com.example.myapplication23.ui.agents;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication23.ContainerForAdapter;
import com.example.myapplication23.MyAgentsAdapter;
import com.example.myapplication23.R;
import com.example.myapplication23.Stuff;
import com.example.myapplication23.databinding.FragmentAgentsBinding;

import java.util.ArrayList;


public class AgentsFragment extends Fragment {

    private FragmentAgentsBinding binding;

    public static ListView listView;
    MyAgentsAdapter myAdapter;
    ArrayList<ContainerForAdapter> myArray;
    public static  Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        binding = FragmentAgentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = getContext();
        listView = root.findViewById(R.id.listAgents);
        myArray = Stuff.containerForAdapters;
        methode((myArray.size()*200)+5);


        //myAdapter = new MyAgentsAdapter(root.getContext(), R.layout.agent_list,myArray);
        myAdapter.notifyDataSetChanged();
        listView.setAdapter(null);




        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //openDialog(myArray.get(position));
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void methode(float sizeAdded){

        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height+= convertDpToPixels(sizeAdded,getContext()); //this is in pixels
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