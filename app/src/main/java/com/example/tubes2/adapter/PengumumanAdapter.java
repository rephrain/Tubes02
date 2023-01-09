package com.example.tubes2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tubes2.MainPresenter;
import com.example.tubes2.databinding.ItemListPertemuanBinding;
import com.example.tubes2.fragments.PertemuanFragment;
import com.example.tubes2.model.Pengumuman;
import com.example.tubes2.model.Pertemuan;

import java.util.List;

public class PengumumanAdapter extends BaseAdapter {
    private List<Pengumuman> listPengumuman;
    private LayoutInflater inflater;
    private ItemListPertemuanBinding itemListPertemuanBinding;
    private PertemuanFragment fragmentPertemuan;
    private MainPresenter presenter;
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
