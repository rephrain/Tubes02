package com.example.tubes2;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
    boolean passwordVisible;
    private MainPresenter presenter;

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

//                if(email.trim().equals("")){
//                    binding.email.setError("Email Tidak Boleh Kosong");
//                }
//                else if(password.trim().equals("")){
//                    binding.password.setError("Password Tidak Boleh Kosong");
//                }
//                else{
//                    hideKeyboard(view);
//                    result.putString("page","home");
//                    getParentFragmentManager().setFragmentResult("changePage",result);
//                }

            }
        });

        binding.password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int right = 2;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if (motionEvent.getRawX() >= binding.password.getRight()-binding.password.getCompoundDrawables()[right].getBounds().width()){
                        int select = binding.password.getSelectionEnd();
                        if (passwordVisible){
                            binding.password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_visibility_off_24,0);
                            binding.password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        } else{
                            binding.password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_visibility_24,0);
                            binding.password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        binding.password.setSelection(select);
                        return true;
                    }
                }
                return false;
            }
        });
        return view;
    }

    public static LoginFragment newInstance(String title, MainPresenter presenter){
        LoginFragment fragmentL = new LoginFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragmentL.setArguments(args);
        fragmentL.presenter = presenter;
        return fragmentL;
    }

    private void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
}
