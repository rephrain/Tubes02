package com.example.tubes2.task;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes2.IMainActivity;
import com.example.tubes2.MainPresenter;
import com.example.tubes2.model.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetAcademicYears {
    final String BASE_URL = "https://ifportal.labftis.net/api/v1/academic-years";
    private MainPresenter presenter;
    private Context context;
    private Gson gson;
    private User user;

    public GetAcademicYears(MainPresenter presenter, Context context){
        this.presenter = presenter;
        this.gson = new Gson();
        this.context = context;
        this.user = presenter.getUser();
    }

    public void execute() throws JSONException {
        this.callVolley();
    }

    public void callVolley(){
        RequestQueue mRequestQueue = Volley.newRequestQueue(this.context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, this.BASE_URL, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("result", response.getJSONArray("academic_years").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                processResult(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("result", "error " + user.getToken());
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<String, String>();
                header.put("Authorization", "Bearer " + user.getToken());
                return header;
            }
        };
        mRequestQueue.add(request);
    }

//    public void processResult(String token){
//        this.user.setToken(token);
//        this.presenter.loginAuthenticated(this.user);
//    }
}
