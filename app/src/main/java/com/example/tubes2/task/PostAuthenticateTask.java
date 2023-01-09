package com.example.tubes2.task;

import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes2.MainPresenter;
import com.example.tubes2.model.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class PostAuthenticateTask {
    final String BASE_URL = "https://ifportal.labftis.net/api/v1/authenticate";
    private MainPresenter presenter;
    private Context context;
    private Gson gson;
    private User user;

    public PostAuthenticateTask(MainPresenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
        this.gson = new Gson();
    }

    public void execute(String email, String password, String role) throws JSONException {
        this.user = new User(email, password, role);
        JSONObject json = new JSONObject(this.gson.toJson(user));
        Log.d("json", json.toString());
        this.callVolley(json);
    }

    public void callVolley(JSONObject json){
        RequestQueue mRequestQueue = Volley.newRequestQueue(this.context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, this.BASE_URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String token = null;
                try {
                    token = response.getString("token");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("result", token);
                try {
                    processResult(token);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("result", "error");
                presenter.notifyLoginFailed();
            }
        });
        mRequestQueue.add(request);
    }

    public void processResult(String token) throws JSONException {
        this.user.setToken(token);
        this.presenter.loginAuthenticated(this.user);
    }
}
