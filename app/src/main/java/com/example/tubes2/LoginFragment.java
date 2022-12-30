package com.example.tubes2;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.tubes2.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    String email;
    String password;

    public LoginFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentLoginBinding.inflate(inflater);
        View view = binding.getRoot();
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle result = new Bundle();
                email = binding.email.getText().toString();
                password = binding.password.getText().toString();
                if(email.trim().equals("")){
                    binding.email.setError("Email Tidak Boleh Kosong");
                }
                else if(password.trim().equals("")){
                    binding.password.setError("Password Tidak Boleh Kosong");
                }
                else{
                    hideKeyboard(view);
                    result.putInt("page",2);
                    getParentFragmentManager().setFragmentResult("changePage",result);
                }

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
    private void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
}
