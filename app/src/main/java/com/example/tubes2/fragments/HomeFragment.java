package com.example.tubes2.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes2.MainPresenter;
import com.example.tubes2.adapter.DrawerAdapter;
import com.example.tubes2.R;
import com.example.tubes2.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private DrawerLayout drawerLayout;
    ImageView btMenu;
    private RecyclerView recyclerView;
    static ArrayList<String> arrayList = new ArrayList<>();
    private MainPresenter presenter;
    DrawerAdapter adapter;

    public HomeFragment(){

    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentHomeBinding.inflate(inflater);
        View view = binding.getRoot();
        Bundle result = new Bundle();
//        result.putString("page","home");
//        getParentFragmentManager().setFragmentResult("changePage",result);

        binding.btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Exit");
                builder.setMessage("Are you sure you want to exit?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                builder.show();            }
        });

        binding.menuAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.putString("page","pengumuman");
                getParentFragmentManager().setFragmentResult("changePage",result);
            }
        });

        binding.menuAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.putString("page","pertemuan");
                getParentFragmentManager().setFragmentResult("changePage",result);
            }
        });

        binding.addPengumuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.putString("page","addPengumuman");
                getParentFragmentManager().setFragmentResult("changePage",result);
            }
        });

        binding.menuFrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.putString("page","frs");
                getParentFragmentManager().setFragmentResult("changePage",result);
            }
        });

//        drawerLayout = view.findViewById(R.id.drawer_layout);
//        btMenu = view.findViewById(R.id.bt_menu);
        recyclerView = view.findViewById(R.id.recycle_view);

//        arrayList.clear();
//        arrayList.add("Profile");
//        arrayList.add("Home");
//        arrayList.add("Settings");
//        arrayList.add("Log out");
//
//        adapter = new DrawerAdapter(getActivity(),arrayList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(adapter);

//        btMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                drawerLayout.openDrawer(GravityCompat.END);
//            }
//        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    public static HomeFragment newInstance(String title, MainPresenter presenter){
        HomeFragment fragmentH = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        fragmentH.setArguments(args);
        fragmentH.presenter = presenter;
        return fragmentH;
    }

    public void setUserInformation(String role, String name){
        binding.role.setText(role);
        binding.studentName.setText(name);
    }

    public void homeAnnouncement(String title, String content) {
        binding.judulPengumumanHome.setText(title);
        binding.isiPengumumanHome.setText(content);
    }
}
