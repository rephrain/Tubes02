package com.example.tubes2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tubes2.databinding.ActivityMainBinding;

public class HomeFragment extends Fragment {
    public HomeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        Bundle result = new Bundle();
        result.putInt("page",1);
        getParentFragmentManager().setFragmentResult("changePage",result);
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
