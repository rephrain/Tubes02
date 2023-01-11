package com.example.tubes2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tubes2.MainPresenter;
import com.example.tubes2.adapter.FrsAdapter;
import com.example.tubes2.adapter.PertemuanAdapter;
import com.example.tubes2.databinding.FragmentFrsBinding;
import com.example.tubes2.databinding.FragmentPertemuanBinding;
import com.example.tubes2.model.Pertemuan;

import org.json.JSONException;

import java.util.ArrayList;

public class FrsFragment extends Fragment implements View.OnClickListener{
    private FragmentFrsBinding binding;
    private FrsAdapter adapter;
    private MainPresenter presenter;

    public FrsFragment(){}

    public static FrsFragment newInstance(MainPresenter presenter){
        FrsFragment fragment = new FrsFragment();
        fragment.presenter = presenter;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentFrsBinding.inflate(inflater);
        this.adapter = new FrsAdapter(this, inflater, this.presenter);
        this.binding.lvListPrasyarat.setAdapter(adapter);
        View view = binding.getRoot();
        try {
            presenter.getAcademicYears();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        binding.titleListPrasyarat.setOnClickListener(this::onClick); //sementara ke title dulu karna belum connect API
        binding.menuAnnouncement.setOnClickListener(this::onClick);
        binding.menuAppointment.setOnClickListener(this::onClick);
        binding.menuHome.setOnClickListener(this::onClick);
        binding.menuFrs.setOnClickListener(this::onClick);
        return view;
    }

    @Override
    public void onClick(View view) {
        Bundle result = new Bundle();
        if(view.getId() == binding.titleListPrasyarat.getId()) { //sementara ke title dulu karna belum connect API
            result.putString("page", "semester");
            this.getParentFragmentManager().setFragmentResult("changePage", result);
        }else if(view.getId() == binding.menuAnnouncement.getId()) {
            result.putString("page", "pengumuman");
            this.getParentFragmentManager().setFragmentResult("changePage", result);
        }else if(view.getId() == binding.menuAppointment.getId()) {
            result.putString("page", "pertemuan");
            this.getParentFragmentManager().setFragmentResult("changePage", result);
        }else if(view.getId() == binding.menuHome.getId()) {
            result.putString("page", "home");
            this.getParentFragmentManager().setFragmentResult("changePage", result);
        }else if(view.getId() == binding.menuFrs.getId()) {
            result.putString("page", "frs");
            this.getParentFragmentManager().setFragmentResult("changePage", result);
        }
    }

    public void updateListSemester(ArrayList<String> listSemester) {
        adapter.setListSemester(listSemester);
        adapter.notifyDataSetChanged();
    }
}
