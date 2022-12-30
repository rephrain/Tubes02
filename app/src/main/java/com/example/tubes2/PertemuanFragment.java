package com.example.tubes2;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubes2.databinding.FragmentPertemuanBinding;

import java.util.ArrayList;

public class PertemuanFragment extends Fragment implements View.OnClickListener, InterfacePertemuan{
    private FragmentPertemuanBinding binding;
    private PertemuanAdapter adapter;
    private MainPresenter presenter;

    public PertemuanFragment(){}

    public static PertemuanFragment newInstance(MainPresenter presenter){
        PertemuanFragment fragment = new PertemuanFragment();
        fragment.presenter = presenter;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentPertemuanBinding.inflate(inflater);
        this.adapter = new PertemuanAdapter(this, inflater, this.presenter);
        this.binding.lvListPertemuan.setAdapter(adapter);
        DataBaseHelper db = new DataBaseHelper(getContext());
        Cursor data = db.lihatDataPertemuan();
        View view = binding.getRoot();
        this.getParentFragmentManager().setFragmentResultListener("addToListPertemuan", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Integer id = result.getInt("id");
                String judul= result.getString("namaPasien");
                String tanggalPertemuan = result.getString("tanggalPertemuan");
                String waktuPertemuan = result.getString("waktuPertemuan");
                String partisipan = result.getString("partisipan");
                String deskripsi = result.getString("deskripsi");
                presenter.addToListPertemuan(id, judul, tanggalPertemuan, waktuPertemuan, partisipan, deskripsi);
                db.insertPertemuan(judul, tanggalPertemuan, waktuPertemuan, partisipan, deskripsi);
            }
        });
        binding.btnAdd.setOnClickListener(this::onClick);
        while(data.moveToNext()){
            presenter.addToListPertemuan(data.getInt(0),data.getString(1), data.getString(2), data.getString(3),data.getString(4),data.getString(5));
        }
        return view;
    }
    @Override
    public void onClick(View view) {
        Bundle result = new Bundle();
        result.putInt("page", 3);
        this.getParentFragmentManager().setFragmentResult("changePage", result);
    }

    @Override
    public void updateListPertemuan(ArrayList<Pertemuan> pertemuans) {
        adapter.setListPertemuan(pertemuans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void resetAddForm() {

    }
}
