package com.example.tubes2.task;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes2.MainPresenter;
import com.example.tubes2.model.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetUserInformationTask {
    final String BASE_URL = "https://ifportal.labftis.net/api/v1/users/self";
    private MainPresenter presenter;
    private Context context;
    private Gson gson;
    private User user;

    public GetUserInformationTask(MainPresenter presenter, Context context){
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
                String name = null;
                String id = null;
                try {
                    name = response.getString("name");
                    id = response.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("result user info", name + " " + id);
                try {
                    processResult(id, name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("result", "error ");
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

    public void callVolleyStudent(String id) throws JSONException {
        String urlStudent = "https://ifportal.labftis.net/api/v1/students/id/";
        RequestQueue mRequestQueue1 = Volley.newRequestQueue(this.context);

        JSONObject json = new JSONObject();
        json.put("id", id);
        Log.d("json id", json.toString());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlStudent + id, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String npm = null;
                String initialYear = null;
                Log.d("npm intiial year response", response.toString());
                try {
                    npm = response.getString("npm");
                    initialYear = response.getString("initial_year");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("npm init year", npm + " " + initialYear);
                presenter.setStudent(npm, initialYear);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("result error", error.toString());
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
        mRequestQueue1.add(request);
    }

    public void processResult(String id, String name) throws JSONException {
        this.presenter.setUserIdName(id, name);
        if (this.user.getRole().equals("student")){
            callVolleyStudent(id);
            Log.d("id dari process result", id);
        }
        this.presenter.setUserInformationAtHome();
    }
}
