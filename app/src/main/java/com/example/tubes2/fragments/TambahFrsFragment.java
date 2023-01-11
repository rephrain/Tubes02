package com.example.tubes2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tubes2.MainPresenter;
import com.example.tubes2.adapter.SemesterAdapter;
import com.example.tubes2.adapter.TambahFrsAdapter;
import com.example.tubes2.databinding.FragmentSemesterBinding;
import com.example.tubes2.databinding.FragmentTambahFrsBinding;

public class TambahFrsFragment extends Fragment implements View.OnClickListener {
    private FragmentTambahFrsBinding binding;
    private TambahFrsAdapter adapter;
    private MainPresenter presenter;

    public TambahFrsFragment() {
    }

    public static TambahFrsFragment newInstance(MainPresenter presenter) {
        TambahFrsFragment fragment = new TambahFrsFragment();
        fragment.presenter = presenter;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentTambahFrsBinding.inflate(inflater);
        this.adapter = new TambahFrsAdapter(this, inflater, this.presenter);
        this.binding.lvListMatkul.setAdapter(adapter);
        View view = binding.getRoot();
        binding.btnBack.setOnClickListener(this::onClick);
        binding.submit.setOnClickListener(this::onClick);
        return view;
    }

    @Override
    public void onClick(View view) {

        Bundle result = new Bundle();
        if(view.getId() == binding.btnBack.getId()) {
            result.putString("page", "semester");
            this.getParentFragmentManager().setFragmentResult("changePage", result);
        }else if(view.getId() == binding.submit.getId()) {
            result.putString("page", "semester");
            this.getParentFragmentManager().setFragmentResult("changePage", result);
        }
    }
}
