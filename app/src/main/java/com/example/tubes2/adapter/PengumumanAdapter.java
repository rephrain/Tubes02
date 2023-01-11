package com.example.tubes2.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentTransaction;

import com.example.tubes2.MainPresenter;
import com.example.tubes2.databinding.ItemListPengumumanBinding;
import com.example.tubes2.databinding.ItemListPertemuanBinding;
import com.example.tubes2.fragments.AnnouncementFragment;
import com.example.tubes2.fragments.PertemuanFragment;
import com.example.tubes2.model.Pengumuman;
import com.example.tubes2.model.Pertemuan;

import java.util.ArrayList;
import java.util.List;

public class PengumumanAdapter extends BaseAdapter {
    private ArrayList<Pengumuman> listPengumuman;
    private LayoutInflater inflater;
    private ItemListPengumumanBinding binding;
    private AnnouncementFragment fragment;
    private MainPresenter presenter;

    public PengumumanAdapter(AnnouncementFragment fragment, LayoutInflater inflater, MainPresenter presenter){
        this.fragment=fragment;
        this.listPengumuman=new ArrayList<>();
        this.inflater = inflater;
        this.presenter = presenter;
    }
    public void add(Pengumuman pengumuman){
        this.listPengumuman.add(pengumuman);
        notifyDataSetChanged();
    }

    public void setListPengumuman(ArrayList<Pengumuman> listPengumuman){
        this.listPengumuman = listPengumuman;
    }
    @Override
    public int getCount() {
        return listPengumuman.size();
    }

    @Override
    public Pengumuman getItem(int i) {
        return listPengumuman.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        binding = ItemListPengumumanBinding.inflate(this.inflater);
        view = binding.getRoot();
        binding.tvJudulPengumuman.setText(this.listPengumuman.get(i).getTitle());
//        String tags = "";
//        for(int j=0; j<listPengumuman.get(i).getTags().size(); j++){
//            tags += listPengumuman.get(i).getTags()+",";
//        }
//        tags = tags.substring(0,tags.length()-1);
        binding.tvIsiPengumuman.setText("tes");
        return view;
    }

    private class ViewHolder{
        ItemListPengumumanBinding binding;
        protected Pengumuman pengumuman;

        public ViewHolder (ItemListPengumumanBinding binding){
            this.binding = binding;
        }

        public void updateView(Pengumuman pengumuman){
            this.pengumuman = pengumuman;
//            this.binding.tvJudulPengumuman.setText(pengumuman.getTitle());
//            this.binding.tvIsiPengumuman.setText(pengumuman.getContent());
        }
    }
}
