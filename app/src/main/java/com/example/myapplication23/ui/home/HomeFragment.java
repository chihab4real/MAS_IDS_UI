package com.example.myapplication23.ui.home;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication23.R;
import com.example.myapplication23.Stuff;
import com.example.myapplication23.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    LinearLayout availbaleclass,classeslayout;
    LinearLayout dtlayout,svmlayout,nnlayout;
    LinearLayout underdt,undersvm,undernn;

    TextView dttraintxt,svmtraintxt,nntraintxt;
    TextView dtfmtxt,svmfmtxt,nnfmtxt;
    TextView dtpretxt,svmpretxt,nnpretxt;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        dttraintxt = root.findViewById(R.id.dttraindatatxt);
        svmtraintxt = root.findViewById(R.id.svmtraindatatxt);
        nntraintxt = root.findViewById(R.id.nntraindatatxt);

        dtfmtxt = root.findViewById(R.id.dtfmtxt);
        svmfmtxt = root.findViewById(R.id.svmfmtxt);
        nnfmtxt = root.findViewById(R.id.nnfmtxt);

        dtpretxt = root.findViewById(R.id.dtpretxt);
        svmpretxt = root.findViewById(R.id.svmpretxt);
        nnpretxt = root.findViewById(R.id.nnpretxt);

        availbaleclass = root.findViewById(R.id.availbaleclass);
        classeslayout = root.findViewById(R.id.classeslayout);


        dtlayout = root.findViewById(R.id.dtlayout);
        svmlayout = root.findViewById(R.id.svmlayout);
        nnlayout = root.findViewById(R.id.nnlayout);

        underdt = root.findViewById(R.id.underdt);
        undersvm = root.findViewById(R.id.undersvm);
        undernn = root.findViewById(R.id.undernn);

        dttraintxt.setText("\t\tLa taille de TrainData: "+ Stuff.DT.getTraindataSize());
        dtfmtxt.setText("\t\tF-Measure: "+(int)(Stuff.DT.getfMeasure()*100)+"%");
        dtpretxt.setText("\t\t Precision: "+(int)(Stuff.DT.getPrecesion()*100)+"%");

        svmtraintxt.setText("\t\tLa taille de TrainData: "+Stuff.SVM.getTraindataSize());
        svmfmtxt.setText("\t\tF-Measure: "+(int)(Stuff.SVM.getfMeasure()*100)+"%");
        svmpretxt.setText("\t\t Precision: "+(int)(Stuff.SVM.getPrecesion()*100)+"%");

        nntraintxt.setText("\t\tLa taille de TrainData: "+Stuff.NN.getTraindataSize());
        nnfmtxt.setText("\t\tF-Measure: "+(int)(Stuff.NN.getfMeasure()*100)+"%");
        nnpretxt.setText("\t\t Precision: "+(int)(Stuff.NN.getPrecesion()*100)+"%");





        classeslayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        availbaleclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(classeslayout.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(classeslayout,new AutoTransition());
                    classeslayout.setVisibility(View.VISIBLE);
                }else{
                    TransitionManager.beginDelayedTransition(classeslayout,new AutoTransition());
                    classeslayout.setVisibility(View.GONE);
                    underdt.setVisibility(View.GONE);
                    undersvm.setVisibility(View.GONE);
                    undernn.setVisibility(View.GONE);
                }
            }
        });
        dtlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(underdt.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(underdt,new AutoTransition());
                    underdt.setVisibility(View.VISIBLE);
                }else{
                    TransitionManager.beginDelayedTransition(underdt,new AutoTransition());
                    underdt.setVisibility(View.GONE);
                }
            }
        });
        svmlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(undersvm.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(undersvm,new AutoTransition());
                    undersvm.setVisibility(View.VISIBLE);
                }else{
                    TransitionManager.beginDelayedTransition(undersvm,new AutoTransition());
                    undersvm.setVisibility(View.GONE);
                }
            }
        });
        nnlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(undernn.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(undernn,new AutoTransition());
                    undernn.setVisibility(View.VISIBLE);
                }else{
                    TransitionManager.beginDelayedTransition(undernn,new AutoTransition());
                    undernn.setVisibility(View.GONE);
                }
            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}