package com.example.tubes2.task;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes2.MainPresenter;
import com.example.tubes2.model.Pertemuan;
import com.example.tubes2.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GetAnnouncementTask {
    final String BASE_URL = "https://ifportal.labftis.net/api/v1/announcements/";
    private MainPresenter presenter;
    private Context context;
    private Gson gson;
    private User user;

    public GetAnnouncementTask(MainPresenter presenter, Context context){
        this.presenter = presenter;
        this.gson = new Gson();
        this.context = context;
        this.user = presenter.getUser();
    }

    public void execute() throws JSONException {
        this.callVolley();
    }

    public void callVolley() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("id", presenter.getUser());
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(json);
        String url = this.BASE_URL + presenter.getUser().getId();
        RequestQueue mRequestQueue = Volley.newRequestQueue(this.context);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, jsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject obj = new JSONObject();
                    try {
                        obj = (JSONObject) response.get(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    Pertemuan pertemuan = null;
                    try {
//                        pertemuan = new Pertemuan(obj.getString("id"),
//                                obj.getString("title"),
//                                obj.getString("start_datetime"),
//                                obj.getString("end_datetime"),
//                                obj.getString("description")
//                        );
                        presenter.addToListPengumuman(obj.getString("title"),
                                obj.getString("content"),
                                obj.getJSONArray("tags"));
                        presenter.homeAnnouncement(obj.getString("title"),
                                obj.getString("content"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("result", error.toString());
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
