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

public class GetAppointmentsTask {
    final String BASE_URL = "https://ifportal.labftis.net/api/v1/appointments/";
    private MainPresenter presenter;
    private Context context;
    private Gson gson;
    private User user;

    public GetAppointmentsTask(MainPresenter presenter, Context context){
        this.presenter = presenter;
        this.gson = new Gson();
        this.context = context;
        this.user = presenter.getUser();
    }

    public void execute() throws JSONException {
        this.callVolley();
    }

    public void callVolley() throws JSONException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();
        String start_date = dateFormat.format(date);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +7);
        Date todate1 = cal.getTime();
        String end_date = dateFormat.format(todate1);

        JSONObject json = new JSONObject();
        json.put("start_date", start_date);
        json.put("end_date", end_date);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(json);

        Log.d("json", json.toString());

        String url = this.BASE_URL + "start-date/" + start_date + "/end-date/" + end_date;
        Log.d("url", url);

        RequestQueue mRequestQueue = Volley.newRequestQueue(this.context);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, jsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("result appoinments", response.toString());
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
                        presenter.addToListPertemuan(obj.getString("id"),
                                obj.getString("title"),
                                obj.getString("start_datetime"),
                                obj.getString("end_datetime"),
                                obj.getString("description"));
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
