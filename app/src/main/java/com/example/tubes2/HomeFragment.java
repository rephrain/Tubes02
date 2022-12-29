package com.example.tubes2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.tubes2.databinding.ActivityMainBinding;

import kotlin.contracts.CallsInPlace;

public class HomeFragment extends Fragment {
    ImageView exit;

    public HomeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        Bundle result = new Bundle();
        result.putInt("page",1);
        getParentFragmentManager().setFragmentResult("changePage",result);

        this.exit = view.findViewById(R.id.btn_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Exit");
                builder.setMessage("Are you sure you want to exit?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                builder.show();            }
        });
        return view;
    }

    public static HomeFragment newInstance(String title){
        HomeFragment fragmentH = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        fragmentH.setArguments(args);
        return fragmentH;
    }
}
