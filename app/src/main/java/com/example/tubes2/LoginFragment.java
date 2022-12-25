package com.example.tubes2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {
    Button btn_login;

    public LoginFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        btn_login = view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle result = new Bundle();
                result.putInt("page",2);
                getParentFragmentManager().setFragmentResult("changePage",result);
            }
        });
        return view;
    }

    public static LoginFragment newInstance(String title){
        LoginFragment fragmentL = new LoginFragment();
        Bundle args = new Bundle();
        fragmentL.setArguments(args);
        return fragmentL;
    }
}
