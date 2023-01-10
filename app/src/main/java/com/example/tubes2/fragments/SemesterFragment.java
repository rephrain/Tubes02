package com.example.tubes2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tubes2.MainPresenter;
import com.example.tubes2.adapter.FrsAdapter;
import com.example.tubes2.adapter.SemesterAdapter;
import com.example.tubes2.databinding.FragmentFrsBinding;
import com.example.tubes2.databinding.FragmentSemesterBinding;

public class SemesterFragment extends Fragment implements View.OnClickListener{
    private FragmentSemesterBinding binding;
    private SemesterAdapter adapter;
    private MainPresenter presenter;

    public SemesterFragment(){}

    public static SemesterFragment newInstance(MainPresenter presenter){
        SemesterFragment fragment = new SemesterFragment();
        fragment.presenter = presenter;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentSemesterBinding.inflate(inflater);
        this.adapter = new SemesterAdapter(this, inflater, this.presenter);
        this.binding.lvListMatkul.setAdapter(adapter);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onClick(View view) {
        Bundle result = new Bundle();
    }
}
