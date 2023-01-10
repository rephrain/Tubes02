package com.example.tubes2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tubes2.MainPresenter;
import com.example.tubes2.databinding.ItemListFrsBinding;
import com.example.tubes2.databinding.ItemListSemesterBinding;
import com.example.tubes2.fragments.FrsFragment;
import com.example.tubes2.fragments.SemesterFragment;

import java.util.ArrayList;
import java.util.List;

public class SemesterAdapter extends BaseAdapter {
    private List<String> matkul;
    private LayoutInflater inflater;
    private ItemListSemesterBinding binding;
    private SemesterFragment fragment;
    private MainPresenter presenter;

    public SemesterAdapter(SemesterFragment fragment, LayoutInflater inflater, MainPresenter presenter) {
        this.fragment = fragment;
        this.matkul = new ArrayList<>();
        this.inflater = inflater;
        this.presenter = presenter;
    }

    public void add(List<String> matkul) {
        this.matkul = matkul;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return matkul.size();
    }

    @Override
    public String getItem(int i) {
        return matkul.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            binding = ItemListSemesterBinding.inflate(this.inflater);
            view = binding.getRoot();
            viewHolder = new ViewHolder(binding);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(getItem(i));
        return view;
    }

    //blm jalan
    private class ViewHolder {
        ItemListSemesterBinding binding;
        protected String matkul;

        public ViewHolder(ItemListSemesterBinding binding) {
            this.binding = binding;
        }

        public void updateView(String matkul) {
            this.matkul = matkul;
            this.binding.namaMatkul.setText(this.matkul);
        }
    }
}