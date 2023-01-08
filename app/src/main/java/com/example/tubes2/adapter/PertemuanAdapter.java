package com.example.tubes2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tubes2.MainPresenter;
import com.example.tubes2.databinding.ItemListPertemuanBinding;
import com.example.tubes2.fragments.PertemuanFragment;
import com.example.tubes2.model.Pertemuan;

import java.util.ArrayList;
import java.util.List;

public class PertemuanAdapter extends BaseAdapter {
    private List<Pertemuan> listPertemuan;
    private LayoutInflater inflater;
    private ItemListPertemuanBinding itemListPertemuanBinding;
    private PertemuanFragment fragmentPertemuan;
    private MainPresenter presenter;

    public PertemuanAdapter(PertemuanFragment fragmentPertemuan, LayoutInflater inflater, MainPresenter presenter){
        this.fragmentPertemuan=fragmentPertemuan;
        this.listPertemuan=new ArrayList<>();
        this.inflater = inflater;
        this.presenter = presenter;
    }

    public void setListPertemuan(List<Pertemuan> listPertemuan){
        this.listPertemuan = listPertemuan;
    }

    @Override
    public int getCount() {
        return listPertemuan.size();
    }

    @Override
    public Pertemuan getItem(int i) {
        return listPertemuan.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            itemListPertemuanBinding = ItemListPertemuanBinding.inflate(this.inflater);
            view = itemListPertemuanBinding.getRoot();
            viewHolder = new ViewHolder(itemListPertemuanBinding);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(this.getItem(i));
        return view;
    }

    private class ViewHolder{
        ItemListPertemuanBinding itemListPertemuanBinding;
        protected Pertemuan pertemuan;

        public ViewHolder (ItemListPertemuanBinding itemListPertemuanBinding){
            this.itemListPertemuanBinding = itemListPertemuanBinding;
        }

        public void updateView(Pertemuan pertemuan){
            this.pertemuan = pertemuan;
            this.itemListPertemuanBinding.tvJudulPertemuan.setText(this.pertemuan.getJudul());
            this.itemListPertemuanBinding.tvTanggalPertemuan.setText(this.pertemuan.getTanggalPertemuan());
        }
    }
}
