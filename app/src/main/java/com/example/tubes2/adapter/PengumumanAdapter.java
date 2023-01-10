package com.example.tubes2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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
        ViewHolder viewHolder;
        if (view == null){
            binding = ItemListPengumumanBinding.inflate(this.inflater);
            view = binding.getRoot();
            viewHolder = new ViewHolder(binding);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(this.getItem(i),i);
        return view;
    }

    private class ViewHolder{
        ItemListPengumumanBinding binding;
        protected Pengumuman pengumuman;

        public ViewHolder (ItemListPengumumanBinding binding){
            this.binding = binding;
        }

        public void updateView(Pengumuman pengumuman, int i){
            this.pengumuman = pengumuman;
            this.binding.tvJudulPengumuman.setText(pengumuman.getTitle());
            this.binding.tvIsiPengumuman.setText(pengumuman.getTags());
        }
    }
}
