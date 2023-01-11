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
import com.example.tubes2.model.Pengumuman;
import com.example.tubes2.model.Pertemuan;
import com.example.tubes2.model.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostAnnouncementTask {
    final String BASE_URL = "https://ifportal.labftis.net/api/v1/announcements";
    private MainPresenter presenter;
    private Context context;
    private Gson gson;
    protected ArrayList<Pengumuman> pengumumans;
    private User user;

    public PostAnnouncementTask(MainPresenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
        this.gson = new Gson();
        this.user = presenter.getUser();
        this.pengumumans = new ArrayList<>();
    }

    public void execute(String title, String[] tags, String content) throws JSONException {
//        this.pengumumans.add(new Pengumuman(title,content,tags));
        JSONObject json = new JSONObject(this.gson.toJson(pengumumans));
        Log.d("json", json.toString());
        this.callVolley(json);
    }

    public void callVolley(JSONObject json){
        RequestQueue mRequestQueue = Volley.newRequestQueue(this.context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, this.BASE_URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String id = null;
                String title = null;
                String content = null;
                String updated_at = null;
                try {
                    id = response.getString("id");
                    title = response.getString("title");
                    content = response.getString("content");
                    updated_at = response.getString("updated_at");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("result", id);
                try {
                    processResult(id,title,content,updated_at);
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
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<String, String>();
                header.put("Authorization", "Bearer " + user.getToken());
                return header;
            }
        };
        mRequestQueue.add(request);
    }

    public void processResult(String id, String title, String content, String updated_at) throws JSONException {
//        this.presenter.addedAnnouncement(this.pengumumans);
    }
}
