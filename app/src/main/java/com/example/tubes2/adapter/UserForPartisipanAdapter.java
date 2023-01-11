package com.example.tubes2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tubes2.MainPresenter;
import com.example.tubes2.databinding.ItemListPengumumanBinding;
import com.example.tubes2.databinding.UserForPartisipanBinding;
import com.example.tubes2.fragments.AddPertemuanFragment;
import com.example.tubes2.fragments.AnnouncementFragment;
import com.example.tubes2.model.Pengumuman;
import com.example.tubes2.model.Pertemuan;
import com.example.tubes2.model.User;

import java.util.ArrayList;

public class UserForPartisipanAdapter extends BaseAdapter {
    private ArrayList<User> listUser;
    private LayoutInflater inflater;
    private UserForPartisipanBinding binding;
    private AddPertemuanFragment fragment;
    private MainPresenter presenter;

    public UserForPartisipanAdapter(AddPertemuanFragment fragment, LayoutInflater inflater, MainPresenter presenter){
        this.fragment=fragment;
        this.listUser=new ArrayList<>();
        this.inflater = inflater;
        this.presenter = presenter;
    }
    public void add(User pertemuan){
        this.listUser.add(pertemuan);
        notifyDataSetChanged();
    }

    public void setListPengumuman(ArrayList<User> listUser){
        this.listUser = listUser;
    }
    @Override
    public int getCount() {
        return listUser.size();
    }

    @Override
    public User getItem(int i) {
        return listUser.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        UserForPartisipanAdapter.ViewHolder viewHolder;
        if (view == null){
            binding = UserForPartisipanBinding.inflate(this.inflater);
            view = binding.getRoot();
            viewHolder = new UserForPartisipanAdapter.ViewHolder(binding);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (UserForPartisipanAdapter.ViewHolder) view.getTag();
        }
        viewHolder.updateView(this.getItem(i));
        return view;
    }

    private class ViewHolder{
        UserForPartisipanBinding binding;
        protected User user;

        public ViewHolder (UserForPartisipanBinding binding){
            this.binding = binding;
        }

        public void updateView(User user){
            this.user = user;
            this.binding.namaUser.setText(user.getName());
        }
    }
}
