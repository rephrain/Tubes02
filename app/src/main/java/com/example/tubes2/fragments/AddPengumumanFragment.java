package com.example.tubes2.fragments;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;

import com.example.tubes2.MainPresenter;
import com.example.tubes2.databinding.PengumumanAddBinding;
import com.example.tubes2.databinding.PertemuanAddBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class AddPengumumanFragment  extends Fragment implements View.OnClickListener{
    private PengumumanAddBinding binding;

    String judul;
    String tags;
    String content;

    MainPresenter presenter;

    public AddPengumumanFragment(){

    }

    public static AddPengumumanFragment newInstance(String title, MainPresenter presenter){
        AddPengumumanFragment fragment = new AddPengumumanFragment();
        fragment.presenter = presenter;
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = PengumumanAddBinding.inflate(inflater);
        View view = binding.getRoot();
        binding.submit.setOnClickListener(this::onClick);
        binding.btnBack.setOnClickListener(this::onClick);
        return view;
    }

    @Override
    public void onClick(View view) {
        Bundle result = new Bundle();
        if(view.getId() == binding.submit.getId()) {

            Bundle newPengumuman = new Bundle();
            judul = binding.judulPengumuman.getText().toString();
            tags = binding.tagPengumuman.getText().toString();
            content = binding.isiPengumuman.getText().toString();
            if(judul.trim().equals("")){
                binding.judulPengumuman.setError("Judul Pengumuman Tidak Boleh Kosong");
            }else if(tags.trim().equals("")){
                binding.tagPengumuman.setError("Tag Pengumuman Tidak Boleh Kosong");
            }else if(content.trim().equals("")){
                binding.isiPengumuman.setError("Isi Pengumuman Tidak Boleh Kosong");
            }else{
                hideKeyboard(view);
                newPengumuman.putString("judul",judul);
                newPengumuman.putString("tags",tags);
                newPengumuman.putString("content",content);
                result.putString("page","pengumuman");
                this.getParentFragmentManager().setFragmentResult("addToListPengumuman",newPengumuman);
                this.getParentFragmentManager().setFragmentResult("changePage",result);
            }
        }else if(view.getId() == binding.submit.getId()) {
            result.putString("page","pengumuman");
            this.getParentFragmentManager().setFragmentResult("changePage",result);
        }
    }

    public String getCurrentDate(){
        final Calendar c = Calendar.getInstance();
        int year, month, day;
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DATE);
        return day + "/" + (month+1) + "/" + year;
    }

    private void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
}
