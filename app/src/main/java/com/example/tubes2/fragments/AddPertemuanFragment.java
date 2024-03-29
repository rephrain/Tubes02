package com.example.tubes2.fragments;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import com.example.tubes2.MainPresenter;
import com.example.tubes2.adapter.UserForPartisipanAdapter;
import com.example.tubes2.databinding.PertemuanAddBinding;
import com.example.tubes2.databinding.UserForPartisipanBinding;

import org.json.JSONException;

import java.util.Calendar;

public class AddPertemuanFragment extends Fragment implements View.OnClickListener{
    private PertemuanAddBinding binding;
    DatePickerDialog.OnDateSetListener setListener;

    String judul;
    String tanggalPertemuan;
    String waktuPertemuan;
    String waktuAkhir;
    String partisipan;
    String addPartisipan;
    String deskripsi;
    private UserForPartisipanAdapter adapter;

    MainPresenter presenter;

    public AddPertemuanFragment(){

    }

    public static AddPertemuanFragment newInstance(String title, MainPresenter presenter){
        AddPertemuanFragment fragment = new AddPertemuanFragment();
        fragment.presenter = presenter;
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = PertemuanAddBinding.inflate(inflater);
        View view = binding.getRoot();

//        users = new ArrayAdapter<String>(this, UserForPartisipanBinding.inflate(getLayoutInflater()), this.presenter.getUsers());

        this.adapter = new UserForPartisipanAdapter(this, inflater, this.presenter);
        binding.spinnerPartisipanPertemuan.setAdapter(adapter);

        binding.btnSimpan.setOnClickListener(this::onClick);
        binding.btnTanggalPertemuan.setOnClickListener(this::onClick);
        binding.etWaktuPertemuan.setOnClickListener(this::onClick);
        binding.etWaktuAkhir.setOnClickListener(this::onClick);
//        binding.spinnerPartisipanPertemuan.setOnClickListener(this::onClick);
        binding.btnBack.setOnClickListener(this::onClick);

        try {
            this.presenter.getUsersForPartisipan();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
    @Override
    public void onClick(View view) {
        Bundle result = new Bundle();
        if(view == binding.btnBack){
            result.putString("page","pertemuan");
            this.getParentFragmentManager().setFragmentResult("changePage",result);
        }else if(view.getId() == binding.btnTanggalPertemuan.getId()){
            Calendar calendar = Calendar.getInstance();
            final int year = calendar.get(Calendar.YEAR);
            final int month = calendar.get(Calendar.MONTH);
            final int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddPertemuanFragment.this.getActivity(),android.R.style.
                    Theme_Holo_Light_Dialog_MinWidth,setListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    month = month + 1;
                    String date = day+"/"+month+"/"+year;
                    binding.btnTanggalPertemuan.setText(date);
                }
            }, year, month, day);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        }else if(view.getId() == binding.etWaktuPertemuan.getId()){
            Calendar calendar = Calendar.getInstance();
            final int hour = calendar.get(Calendar.HOUR_OF_DAY);
            final int minute = calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(AddPertemuanFragment.this.getActivity(),  new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                    binding.etWaktuPertemuan.setText(hour+":"+minute);
                }
            }, hour, minute, true);
            timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            timePickerDialog.show();
        }else if(view.getId() == binding.etWaktuAkhir.getId()){
            Calendar calendar = Calendar.getInstance();
            final int jam = calendar.get(Calendar.HOUR_OF_DAY);
            final int menit = calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(AddPertemuanFragment.this.getActivity(),  new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int jam, int menit) {
                    binding.etWaktuAkhir.setText(jam+":"+menit);
                }
            }, jam, menit, true);
            timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            timePickerDialog.show();
        }
        else if(view == binding.spinnerPartisipanPertemuan){

        }
        else if(view.getId() == binding.btnSimpan.getId()){

            Bundle newPertemuan = new Bundle();
            judul = binding.etJudulPertemuan.getText().toString();
            tanggalPertemuan = binding.btnTanggalPertemuan.getText().toString();
            waktuPertemuan = binding.etWaktuPertemuan.getText().toString();
            partisipan = binding.spinnerPartisipanPertemuan.getSelectedItem().toString();
            deskripsi = binding.etDeskripsiPertemuan.getText().toString();
            if(judul.trim().equals("")){
                binding.etJudulPertemuan.setError("Judul Tidak Boleh Kosong");
            }else if(tanggalPertemuan.trim().equals("")){
                binding.btnTanggalPertemuan.setError("Tanggal Pertemuan Tidak Boleh Kosong");
            }
            else if(waktuPertemuan.trim().equals("")){
                binding.etWaktuPertemuan.setError("Waktu Pertemuan Tidak Boleh Kosong");
            }else if(waktuAkhir.trim().equals("")){
                binding.etWaktuAkhir.setError("Waktu Akhir Tidak Boleh Kosong");
            }
//            else if(partisipan.trim().equals("")){
//                binding.etPartisipanPertemuan.setError("Partisipan Tidak Boleh Kosong");
//            }
            else if(deskripsi.trim().equals("")){
                binding.etDeskripsiPertemuan.setError("Deskripsi Pertemuan Tidak Boleh Kosong");
            }

            else{
                hideKeyboard(view);
                newPertemuan.putString("judul",judul);
                newPertemuan.putString("tanggalPertemuan",tanggalPertemuan);
                newPertemuan.putString("waktuPertemuan",waktuPertemuan);
                newPertemuan.putString("waktuAkhir",waktuPertemuan);
                newPertemuan.putString("partisipan",partisipan);
                newPertemuan.putString("deskripsi",deskripsi);
                result.putString("page","pertemuan");
                this.getParentFragmentManager().setFragmentResult("addToListPertemuan",newPertemuan);
                this.getParentFragmentManager().setFragmentResult("changePage",result);
            }
        }
    }
    private void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
}
