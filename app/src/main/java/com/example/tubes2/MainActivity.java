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

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    LoginFragment fragmentL;
    HomeFragment fragmentH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        fragmentL = LoginFragment.newInstance("New Fragment 1");
        fragmentH = HomeFragment.newInstance("New Fragment 2");

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,fragmentL).commit();

        getSupportFragmentManager().setFragmentResultListener("changePage",this,new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                int page = result.getInt("page");
                changePage(page);
            }
        });
    }

    public void changePage(int page){
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if (page == 1){
            if (this.fragmentL.isAdded()){
                ft.show(this.fragmentL);
            } else{
                ft.add(R.id.fragment_container,this.fragmentL);
            }
            if (this.fragmentH.isAdded()){
                ft.hide(this.fragmentH);
            }
            closeKeyboard();
        } else if (page == 2){
            if (this.fragmentH.isAdded()){
                ft.show(this.fragmentH);
            } else{
                ft.add(R.id.fragment_container,this.fragmentH).addToBackStack(null);
            }
            if (this.fragmentL.isAdded()){
                ft.hide(this.fragmentL);
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
}