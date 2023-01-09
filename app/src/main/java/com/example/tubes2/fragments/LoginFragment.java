package com.example.tubes2.fragments;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;

import com.example.tubes2.MainPresenter;
import com.example.tubes2.R;
import com.example.tubes2.databinding.FragmentLoginBinding;
import com.example.tubes2.model.User;

import org.json.JSONException;

import java.util.Locale;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private FragmentLoginBinding binding;
    private String email;
    private String password;
    private String role;
    boolean passwordVisible;
    private MainPresenter presenter;

    public LoginFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentLoginBinding.inflate(inflater);
        View view = binding.getRoot();

        binding.btnLogin.setOnClickListener(this);

//        Bundle result = new Bundle();
//        result.putString("page", "home");
//        this.getParentFragmentManager().setFragmentResult("changePage", result);

        binding.password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int right = 2;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if (motionEvent.getRawX() >= binding.password.getRight()-binding.password.getCompoundDrawables()[right].getBounds().width()){
                        int select = binding.password.getSelectionEnd();
                        if (passwordVisible){
                            binding.password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_visibility_off_24,0);
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

    @Override
    public void onClick(View view) {
        if(view.getId() == binding.btnLogin.getId()){
            //                Bundle result = new Bundle();
            this.email = binding.email.getText().toString();
            this.password = binding.password.getText().toString();
            this.role = binding.role.getText().toString();

            if(email.trim().equals("")){
                binding.email.setError("Email Tidak Boleh Kosong");
            }
            else if(password.trim().equals("")){
                binding.password.setError("Password Tidak Boleh Kosong");
            }
            else if(role.toLowerCase().trim().equals("")){
                binding.role.setError("Role Tidak Boleh Kosong");
            }
            else{
                hideKeyboard(view);
                try {
                    this.presenter.callAuthenticateTask(this.email, this.password, this.role);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                    result.putString("page","home");
//                    getParentFragmentManager().setFragmentResult("changePage",result);
            }
        }
    }

    public static LoginFragment newInstance(String title, MainPresenter presenter){
        LoginFragment fragmentL = new LoginFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragmentL.setArguments(args);
        fragmentL.presenter = presenter;
        fragmentL.email = new String();
        fragmentL.password = new String();
        fragmentL.role = new String();
        return fragmentL;
    }

    private void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
}
