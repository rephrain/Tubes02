package com.example.tubes2;

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
import java.util.ArrayList;

import kotlin.contracts.CallsInPlace;

public class HomeFragment extends Fragment {
    ImageView exit;
    private DrawerLayout drawerLayout;
    ImageView btMenu;
    private RecyclerView recyclerView;
    static ArrayList<String> arrayList = new ArrayList<>();
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
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        Bundle result = new Bundle();
        result.putInt("page",1);
        getParentFragmentManager().setFragmentResult("changePage",result);

        this.exit = view.findViewById(R.id.btn_exit);
        exit.setOnClickListener(new View.OnClickListener() {
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

        drawerLayout = view.findViewById(R.id.drawer_layout);
        btMenu = view.findViewById(R.id.bt_menu);
        recyclerView = view.findViewById(R.id.recycle_view);

        arrayList.clear();
        arrayList.add("Profile");
        arrayList.add("Home");
        arrayList.add("Settings");
        arrayList.add("Log out");

        adapter = new DrawerAdapter(getActivity(),arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    public static HomeFragment newInstance(String title){
        HomeFragment fragmentH = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        fragmentH.setArguments(args);
        return fragmentH;
    }
}
