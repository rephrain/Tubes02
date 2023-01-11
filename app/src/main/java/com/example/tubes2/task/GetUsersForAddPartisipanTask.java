package com.example.tubes2.task;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes2.MainPresenter;
import com.example.tubes2.model.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GetUsersForAddPartisipanTask {
    final String BASE_URL = "https://ifportal.labftis.net/api/v1/users";
    private MainPresenter presenter;
    private Context context;
    private Gson gson;
    private User user;

    public GetUsersForAddPartisipanTask(MainPresenter presenter, Context context){
        this.presenter = presenter;
        this.gson = new Gson();
        this.context = context;
        this.user = presenter.getUser();
    }

    public void execute() throws JSONException {
        this.callVolley();
    }

    public void callVolley() throws JSONException {
        RequestQueue mRequestQueue = Volley.newRequestQueue(this.context);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, this.BASE_URL, new JSONArray(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("result response", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("result error", "error");
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<String, String>();
                header.put("Authorization", "Bearer " + user.getToken());
                Log.d("token", user.getToken());
                return header;
            }
        };
        mRequestQueue.add(request);
    }

    public void processResult(){
    }
}
