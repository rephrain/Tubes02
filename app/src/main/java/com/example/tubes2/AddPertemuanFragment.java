package com.example.tubes2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import com.example.tubes2.databinding.PertemuanAddBinding;

import java.util.Calendar;

public class AddPertemuanFragment extends Fragment implements View.OnClickListener{
    private PertemuanAddBinding binding;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    public void onClick(View view) {
        if(view.getId() == binding.btnTanggalPertemuan.getId()){
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
                    Log.d("debughaha2","masuk2");
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
        }
//        else if(view == binding.jadwalDosen){
//            FragmentManager fm = getParentFragmentManager();
//            FragmentTransaction ft = fm.beginTransaction();
//
//        }
//        else if(view.getId() == binding.btnSimpan.getId()){
//            Bundle result = new Bundle();
//            Bundle newPertemuan = new Bundle();
//            judul = binding.etJudulPertemuan.getText().toString();
//            isiDokter = binding.isiDokter.getText().toString();
//            isiSpesialis = binding.isiSpesialis.getText().toString();
//            keluhan = binding.etKeluhanPasien.getText().toString();
//            tanggalPertemuan = binding.btnTanggalPertemuan.getText().toString();
//            waktuPertemuan = binding.etWaktuPertemuan.getText().toString();
//            if(namaPasien.trim().equals("")){
//                binding.etNamaPasien.setError("Nama Pasien Tidak Boleh Kosong");
//            }else if(isiDokter.trim().equals("")){
//                binding.isiDokter.setError("Nama Dokter Tidak Boleh Kosong");
//            }
//            else if(isiSpesialis.trim().equals("")){
//                binding.isiSpesialis.setError("Spesialis Dokter Tidak Boleh Kosong");
//            }
//            else if(keluhan.trim().equals("")){
//                binding.etKeluhanPasien.setError("Keluhan Tidak Boleh Kosong");
//            }
//            else if(tanggalPertemuan.trim().equals("")){
//                binding.btnTanggalPertemuan.setError("Tanggal Pertemuan Tidak Boleh Kosong");
//            }
//            else if(waktuPertemuan.trim().equals("")){
//                binding.etWaktuPertemuan.setError("Waktu Pertemuan Tidak Boleh Kosong");
//            }
//
//            else{
//                hideKeyboard(view);
//                newPertemuan.putString("namaPasien",namaPasien);
//                newPertemuan.putString("isiDokter",isiDokter);
//                newPertemuan.putString("isiSpesialis",isiSpesialis);
//                newPertemuan.putString("keluhan",keluhan);
//                newPertemuan.putString("tanggalPertemuan",tanggalPertemuan);
//                newPertemuan.putString("waktuPertemuan",waktuPertemuan);
//                result.putString("page", "pertemuan");
//                this.getParentFragmentManager().setFragmentResult("addToListPertemuan",newPertemuan);
//                this.getParentFragmentManager().setFragmentResult("changePage",result);
//            }
//        }
    }
}
