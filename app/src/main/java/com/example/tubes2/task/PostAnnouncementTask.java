package com.example.tubes2.task;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes2.MainPresenter;
import com.example.tubes2.model.Pengumuman;
import com.example.tubes2.model.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PostAnnouncementTask {
    final String BASE_URL = "https://ifportal.labftis.net/api/v1/announcements";
    private MainPresenter presenter;
    private Context context;
    private Gson gson;
    private Pengumuman pengumuman;

    public PostAnnouncementTask(MainPresenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
        this.gson = new Gson();
    }

    public void execute(String title, String content, ArrayList<String> tags) throws JSONException {
//        this.pengumuman = new Pengumuman(title,content,tags);
        JSONObject json = new JSONObject(this.gson.toJson(pengumuman));
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
                String author_id = null;
                String created_at = null;
                String updated_at = null;
//                ArrayList<String> tags = null;
                try {
                    id = response.getString("id");
                    title = response.getString("title");
                    content = response.getString("content");
                    author_id = response.getString("author_id");
                    created_at = response.getString("created_at");
                    updated_at = response.getString("updated_at");
//                    tags = response.getJSONArray("tags");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("result", id);
                try {
                    processResult(id);
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

    public void processResult(String id) throws JSONException {
//        this.id.setToken(token);
//        this.presenter.loginAuthenticated(this.user);
    }
}
