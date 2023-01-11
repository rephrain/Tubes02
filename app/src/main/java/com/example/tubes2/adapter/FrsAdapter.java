package com.example.tubes2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tubes2.MainPresenter;
import com.example.tubes2.databinding.ItemListFrsBinding;
import com.example.tubes2.databinding.ItemListPertemuanBinding;
import com.example.tubes2.fragments.FrsFragment;
import com.example.tubes2.fragments.PertemuanFragment;
import com.example.tubes2.model.Pertemuan;

import java.util.ArrayList;
import java.util.List;

public class FrsAdapter extends BaseAdapter {
    private ArrayList<String> semesterTahun;
    private LayoutInflater inflater;
    private ItemListFrsBinding binding;
    private FrsFragment fragment;
    private MainPresenter presenter;

    public FrsAdapter(FrsFragment fragment, LayoutInflater inflater, MainPresenter presenter){
        this.fragment=fragment;
        this.semesterTahun=new ArrayList<>();
        this.inflater = inflater;
        this.presenter = presenter;
    }

    public void add(String semesterTahun){
        this.semesterTahun.add(semesterTahun);
        notifyDataSetChanged();
    }
    public void setListSemester(ArrayList<String> listSemester){
        this.semesterTahun = listSemester;
    }
    @Override
    public int getCount() {
        return semesterTahun.size();
    }

    @Override
    public String getItem(int i) {
        return semesterTahun.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            binding = ItemListFrsBinding.inflate(this.inflater);
            view = binding.getRoot();
            viewHolder = new ViewHolder(binding);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(getItem(i));
        return view;
    }
//blm jalan
    private class ViewHolder{
        ItemListFrsBinding binding;
        protected String semesterTahun;

        public ViewHolder (ItemListFrsBinding binding){
            this.binding = binding;
        }

        public void updateView(String namaSemester){
            this.semesterTahun = namaSemester;
            this.binding.namaSemester.setText(this.semesterTahun);
        }
    }
}
