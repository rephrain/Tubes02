package com.example.tubes2.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes2.DataBaseHelper;
import com.example.tubes2.InterfacePengumuman;
import com.example.tubes2.InterfacePertemuan;
import com.example.tubes2.MainPresenter;
import com.example.tubes2.R;
import com.example.tubes2.adapter.PengumumanAdapter;
import com.example.tubes2.adapter.PertemuanAdapter;
import com.example.tubes2.databinding.FragmentPengumumanBinding;
import com.example.tubes2.databinding.FragmentPertemuanBinding;
import com.example.tubes2.model.Pengumuman;
import com.example.tubes2.model.Pertemuan;
import com.example.tubes2.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
                String judul = result.getString("judul");
                String[] tags= result.getStringArray("tags");
                String content = result.getString("content");
                Pengumuman pengumuman = new Pengumuman(judul,content,tags);
                adapter.add(pengumuman);
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
        binding.menuFrs.setOnClickListener(this::onClick);
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
        }else if(view.getId() == binding.menuFrs.getId()) {
            result.putString("page", "frs");
            this.getParentFragmentManager().setFragmentResult("changePage", result);
        }
    }

    @Override
    public void updateListPengumuman(ArrayList<Pengumuman> pengumumans) {
        adapter.setListPengumuman(pengumumans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void resetAddForm() {

    }
}
