package com.example.tubes2;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class GetSemesterDilaluiTask {
    private String BASE_URL;
    private IMainActivity iMainActivity;
    private Context context;
    private Gson gson;

    public GetSemesterDilaluiTask (String BASE_URL, IMainActivity iMainActivity, Context context){
        this.BASE_URL = BASE_URL;
        this.iMainActivity = iMainActivity;
        this.gson = new Gson();
        this.context = context;
    }
}
