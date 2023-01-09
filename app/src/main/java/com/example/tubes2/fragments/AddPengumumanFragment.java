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

import java.util.Calendar;

public class AddPengumumanFragment  extends Fragment implements View.OnClickListener{
    private PengumumanAddBinding binding;

    String judul;
    String tema;
    String tanggalPengumuman;

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
        binding.uploadFile.setOnClickListener(this::onClick);
        binding.submit.setOnClickListener(this::onClick);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == binding.uploadFile.getId()){

        }else if(view.getId() == binding.submit.getId()) {
            Bundle result = new Bundle();
            Bundle newPengumuman = new Bundle();
            judul = binding.etJudulPengumuman.getText().toString();
            tema = binding.etTemaPengumuman.getText().toString();
            tanggalPengumuman = getCurrentDate();
            if(judul.trim().equals("")){
                binding.etJudulPengumuman.setError("Judul Tidak Boleh Kosong");
            }else if(tema.trim().equals("")){
                binding.etTemaPengumuman.setError("Tema Tidak Boleh Kosong");
            }else{
                hideKeyboard(view);
                newPengumuman.putString("judul",judul);
                newPengumuman.putString("tema",tema);
                newPengumuman.putString("tanggalPengumuman",tanggalPengumuman);
                result.putString("page","pengumuman");
                this.getParentFragmentManager().setFragmentResult("addToListPengumuman",newPengumuman);
                this.getParentFragmentManager().setFragmentResult("changePage",result);
            }
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
