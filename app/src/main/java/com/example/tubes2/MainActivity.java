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
import android.widget.Toast;

import com.example.tubes2.databinding.ActivityMainBinding;
import com.example.tubes2.fragments.AddPengumumanFragment;
import com.example.tubes2.fragments.AddPertemuanFragment;
import com.example.tubes2.fragments.AnnouncementFragment;
import com.example.tubes2.fragments.HomeFragment;
import com.example.tubes2.fragments.LoginFragment;
import com.example.tubes2.fragments.PertemuanFragment;
import com.example.tubes2.model.Pertemuan;
import com.example.tubes2.model.User;
import com.example.tubes2.task.GetAcademicYears;
import com.example.tubes2.task.GetAppointmentsTask;
import com.example.tubes2.task.GetUserInformationTask;
import com.example.tubes2.task.PostAuthenticateTask;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IMainActivity{
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private LoginFragment fragmentL;
    private HomeFragment fragmentH;
    private PertemuanFragment fragmentP;
    private AddPertemuanFragment fragmentAP;
    private MainPresenter presenter;
    private AnnouncementFragment fragmentAnnouncement;
    private AddPengumumanFragment fragmentAPengumuman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        this.presenter = new MainPresenter(this, this);

        fragmentL = LoginFragment.newInstance("Fragment Login", this.presenter);
        fragmentH = HomeFragment.newInstance("Fragment Home", this.presenter);
        fragmentP = PertemuanFragment.newInstance(presenter);
        fragmentAP = AddPertemuanFragment.newInstance("Fragment Add Pertemuan", presenter);
        fragmentAnnouncement = AnnouncementFragment.newInstance(presenter);
        fragmentAPengumuman = AddPengumumanFragment.newInstance("Fragment Add Pengumuman", presenter);

        fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container,fragmentL).commit();

        getSupportFragmentManager().setFragmentResultListener("changePage",this,new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String page = result.getString("page");
                changePage(page);
            }
        });
    }

    @Override
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
            if (this.fragmentAnnouncement.isAdded()){
                ft.hide(this.fragmentAnnouncement);
            }
            if (this.fragmentAPengumuman.isAdded()){
                ft.hide(this.fragmentAPengumuman);
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
            if (this.fragmentAnnouncement.isAdded()){
                ft.hide(this.fragmentAnnouncement);
            }
            if (this.fragmentAPengumuman.isAdded()){
                ft.hide(this.fragmentAPengumuman);
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
            if (this.fragmentAnnouncement.isAdded()){
                ft.hide(this.fragmentAnnouncement);
            }
            if (this.fragmentAPengumuman.isAdded()){
                ft.hide(this.fragmentAPengumuman);
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
            if (this.fragmentAnnouncement.isAdded()){
                ft.hide(this.fragmentAnnouncement);
            }
            if (this.fragmentAPengumuman.isAdded()){
                ft.hide(this.fragmentAPengumuman);
            }
            closeKeyboard();
        }else if (page.equals("pengumuman")){
            if (this.fragmentAnnouncement.isAdded()){
                ft.show(this.fragmentAnnouncement);
            } else{
                ft.add(R.id.fragment_container,this.fragmentAnnouncement).addToBackStack(null);
            }
            if (this.fragmentP.isAdded()){
                ft.hide(this.fragmentP);
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
            if (this.fragmentAPengumuman.isAdded()){
                ft.hide(this.fragmentAPengumuman);
            }
            closeKeyboard();
        }else if (page.equals("addPengumuman")){
            if (this.fragmentAPengumuman.isAdded()){
                ft.show(this.fragmentAPengumuman);
            } else{
                ft.add(R.id.fragment_container,this.fragmentAPengumuman).addToBackStack(null);
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
            if (this.fragmentAP.isAdded()){
                ft.hide(this.fragmentAP);
            }
            if (this.fragmentAnnouncement.isAdded()){
                ft.hide(this.fragmentAnnouncement);
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
    public void loginUser(String email, String password, String role) throws JSONException {
        PostAuthenticateTask task = new PostAuthenticateTask(this.presenter, this);
        task.execute(email, password, role);
    }

    @Override
    public void updateListPertemuan(ArrayList<Pertemuan> pertemuans) {
        fragmentP.updateListPertemuan(pertemuans);
    }

    @Override
    public void notifyLoginFailed() {
        Toast.makeText(this,
                "Masukkan salah!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void getAcademicYears() throws JSONException {
        GetAcademicYears task = new GetAcademicYears(this.presenter, this);
        task.execute();
    }

    @Override
    public void setUserInformationAtHome(String role, String name) {
        this.fragmentH.setUserInformation(role, name);
    }

    @Override
    public void runGetUserInfoTask() throws JSONException {
        GetUserInformationTask task = new GetUserInformationTask(this.presenter, this);
        task.execute();
    }

    @Override
    public void getAppointments() throws JSONException {
        GetAppointmentsTask task = new GetAppointmentsTask(this.presenter, this);
        task.execute();
    }

//    @Override
//    public void hideAddAppointmentForAdmin() {
//        this.fragmentP.hideAddAppointment();
//    }
}