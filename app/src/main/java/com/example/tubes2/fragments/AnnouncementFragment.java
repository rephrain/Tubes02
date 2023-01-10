package com.example.tubes2.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubes2.DataBaseHelper;
import com.example.tubes2.InterfacePengumuman;
import com.example.tubes2.InterfacePertemuan;
import com.example.tubes2.MainPresenter;
import com.example.tubes2.adapter.PengumumanAdapter;
import com.example.tubes2.adapter.PertemuanAdapter;
import com.example.tubes2.databinding.FragmentPengumumanBinding;
import com.example.tubes2.databinding.FragmentPertemuanBinding;
import com.example.tubes2.model.Pengumuman;
import com.example.tubes2.model.Pertemuan;

import java.util.ArrayList;

public class AnnouncementFragment extends Fragment implements View.OnClickListener, InterfacePengumuman {
    private FragmentPengumumanBinding binding;
    private PengumumanAdapter adapter;
    private MainPresenter presenter;

    public AnnouncementFragment(){}

    public static AnnouncementFragment newInstance(MainPresenter presenter){
        AnnouncementFragment fragment = new AnnouncementFragment();
        fragment.presenter = presenter;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentPengumumanBinding.inflate(inflater);
        this.adapter = new PengumumanAdapter(this,inflater,this.presenter);
        this.binding.lvListPrasyarat.setAdapter(adapter);
        this.getParentFragmentManager().setFragmentResultListener("addToListPengumuman", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
//                String id = result.getString("id");
//                String judul= result.getString("judul");
//                String tema = result.getString("tema");
//                String tanggal = result.getString("tanggalPengumuman");
//                Pengumuman pengumuman = new Pengumuman(id,judul,tema,tanggal);
//                adapter.add(pengumuman);
//                presenter.addToListPertemuan(id, judul, tanggalPertemuan, waktuPertemuan, partisipan, deskripsi);
//                db.insertPertemuan(judul, tanggalPertemuan, waktuPertemuan, partisipan, deskripsi);
            }
        });
//        DataBaseHelper db = new DataBaseHelper(getContext());
//        Cursor data = db.lihatDataPertemuan();
        View view = binding.getRoot();
        binding.menuAnnouncement.setOnClickListener(this::onClick);
        binding.menuAppointment.setOnClickListener(this::onClick);
        binding.menuHome.setOnClickListener(this::onClick);
        binding.btnAdd.setOnClickListener(this::onClick);
        return view;
    }

    @Override
    public void onClick(View view) {
        Bundle result = new Bundle();
        if(view.getId() == binding.menuAnnouncement.getId()) {
            result.putString("page", "pengumuman");
            this.getParentFragmentManager().setFragmentResult("changePage", result);
        }else if(view.getId() == binding.menuAppointment.getId()) {
            result.putString("page", "pertemuan");
            this.getParentFragmentManager().setFragmentResult("changePage", result);
        }else if(view.getId() == binding.menuHome.getId()) {
            result.putString("page", "home");
            this.getParentFragmentManager().setFragmentResult("changePage", result);
        }else if(view.getId() == binding.btnAdd.getId()) {
            result.putString("page", "addPengumuman");
            this.getParentFragmentManager().setFragmentResult("changePage", result);
        }
    }

    @Override
    public void updateListPengumuman(ArrayList<Pengumuman> pengumumens) {

    }

    @Override
    public void resetAddForm() {

    }
}
