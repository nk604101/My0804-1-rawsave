package com.work.hsinwei.my0804_1_rawsave;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputStream is = null;
        is = getResources().openRawResource(R.raw.anim);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = is.read(buffer)) != -1)
                result.write(buffer, 0, length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        try {
            Log.d("T0804", result.toString("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/


        //

        try {
            str = result.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //JSON  Start
        JSONArray array = null;
        try {
            array = new JSONArray(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj=null;
            try {
                obj = array.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                Log.d("T0804-Json", "[" + i + "]地區:" + obj.getString("district"));
                Log.d("T0804-Json", "[" + i + "]地址:" + obj.getString("address"));
                Log.d("T0804-Json", "[" + i + "]電話:" + obj.getString("tel"));
                Log.d("T0804-Json", "[" + i + "]電話:" + obj.getString("opening_hours"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Json End

        //Gson
        Gson gson = new Gson();
        // TypeToken<ArrayList<dataInfo>>(){} 取出 ArrayList<dataInfo>這個 Type
        ArrayList<dataInfo> glist = gson.fromJson(str,new TypeToken<ArrayList<dataInfo>>(){}.getType());

        int i=0;
        for (dataInfo di : glist)
        {

            Log.d("T080-Gson","["+i+"地區]"+di.district);
            Log.d("T0804-Gson","["+i+"地址]"+di.address);
            Log.d("T0804-Gson","["+i+"電話]"+di.tel);
            i++;
        }

    }
}

