package com.example.tubes2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.tubes2.databinding.ActivityMainBinding;
import com.example.tubes2.fragments.AddPengumumanFragment;
import com.example.tubes2.fragments.AddPertemuanFragment;
import com.example.tubes2.fragments.AnnouncementFragment;
import com.example.tubes2.fragments.HomeFragment;
import com.example.tubes2.fragments.LoginFragment;
import com.example.tubes2.fragments.PertemuanFragment;
import com.example.tubes2.model.Pertemuan;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IMainActivity{
    ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    LoginFragment fragmentL;
    HomeFragment fragmentH;
    PertemuanFragment fragmentP;
    AddPertemuanFragment fragmentAP;
    AnnouncementFragment fragmentA;
    AddPengumumanFragment fragmentAPengumuman;
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        fragmentL = LoginFragment.newInstance("Fragment Login", this.presenter);
        fragmentH = HomeFragment.newInstance("Fragment Home");
        fragmentP = PertemuanFragment.newInstance(presenter);
        fragmentAP = AddPertemuanFragment.newInstance("Fragment Add Pertemuan", presenter);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,fragmentH).commit();

        getSupportFragmentManager().setFragmentResultListener("changePage",this,new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String page = result.getString("page");
                changePage(page);
            }
        });
    }

    public void changePage(String page){
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if (page.equals("login")){
            if (this.fragmentL.isAdded()){
                ft.show(this.fragmentL);
            } else{
                ft.add(R.id.fragment_container,this.fragmentL);
            }
            if (this.fragmentH.isAdded()){
                ft.hide(this.fragmentH);
            }
            if (this.fragmentP.isAdded()){
                ft.hide(this.fragmentP);
            }
            if (this.fragmentAP.isAdded()){
                ft.hide(this.fragmentAP);
            }
            closeKeyboard();
        } else if (page.equals("home")){
            if (this.fragmentH.isAdded()){
                ft.show(this.fragmentH);
            } else{
                ft.add(R.id.fragment_container,this.fragmentH).addToBackStack(null);
            }
            if (this.fragmentL.isAdded()){
                ft.hide(this.fragmentL);
            }
            if (this.fragmentP.isAdded()){
                ft.hide(this.fragmentP);
            }
            if (this.fragmentAP.isAdded()){
                ft.hide(this.fragmentAP);
            }
            closeKeyboard();
        } else if (page.equals("pertemuan")){
            if (this.fragmentP.isAdded()){
                ft.show(this.fragmentP);
            } else{
                ft.add(R.id.fragment_container,this.fragmentP).addToBackStack(null);
            }
            if (this.fragmentL.isAdded()){
                ft.hide(this.fragmentL);
            }
            if (this.fragmentH.isAdded()){
                ft.hide(this.fragmentH);
            }
            if (this.fragmentAP.isAdded()){
                ft.hide(this.fragmentAP);
            }
            closeKeyboard();
        }else if (page.equals("addPertemuan")){
            if (this.fragmentAP.isAdded()){
                ft.show(this.fragmentAP);
            } else{
                ft.add(R.id.fragment_container,this.fragmentAP).addToBackStack(null);
            }
            if (this.fragmentL.isAdded()){
                ft.hide(this.fragmentL);
            }
            if (this.fragmentH.isAdded()){
                ft.hide(this.fragmentH);
            }
            if (this.fragmentP.isAdded()){
                ft.hide(this.fragmentP);
            }
            closeKeyboard();
        }
        ft.commit();
    }

    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    @Override
    public void executeGetSemesterDilalui() {

    }

    @Override
    public void updateListPertemuan(ArrayList<Pertemuan> pertemuans) {

    }

    @Override
    public void resetAddForm() {

    }
}