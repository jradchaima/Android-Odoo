package com.example.venteproductapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Product extends AppCompatActivity {

    private static final String URL_DATA = "http://192.168.43.12:8080/project/api.php";
    private String URLline = "http://192.168.43.12:8080/project/search.php";
    private RequestQueue rQueue;
    ArrayList<ProductData> listproduct;
    Button btn;
    EditText srh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        RecyclerView recyclerView = findViewById(R.id.Recycler);
        btn = findViewById(R.id.search);
        srh = findViewById(R.id.search_name);

        listproduct = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        load_user();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                search();
            }
        });


    }

    private void load_user() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONArray array = null;
                        try {
                            array = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = null;
                            try {
                                object = array.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            String name = null;
                            try {
                                name = object.getString("name");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String price = null;
                            try {
                                price = object.getString("list_price");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String image = null;
                            try {
                                image = object.getString("image_medium");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            ProductData prod = new ProductData(name, price, image);
                            listproduct.add(prod);
                            ProductAdapter useradapter = new ProductAdapter(listproduct, Product.this);
                            RecyclerView recyclerView = findViewById(R.id.Recycler);
                            recyclerView.setAdapter(useradapter);

                        }

                    }
                },


                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Product.this, error.getMessage() + " error Loading Users", Toast.LENGTH_LONG).show();

                    }
                });
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        Volley.newRequestQueue(this).add(stringRequest);

    }

    private void search() {
listproduct.clear();
        final String search = srh.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLline,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        rQueue.getCache().clear();

                        JSONArray array = null;
                        try {
                            array = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = null;
                            try {
                                object = array.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            String name = null;
                            try {
                                name = object.getString("name");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String price = null;
                            try {
                                price = object.getString("list_price");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String image = null;
                            try {
                                image = object.getString("image_medium");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            ProductData prod = new ProductData(name, price, image);
                            listproduct.add(prod);
                            ProductAdapter useradapter = new ProductAdapter(listproduct, Product.this);
                            RecyclerView recyclerView = findViewById(R.id.Recycler);
                            recyclerView.setAdapter(useradapter);

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Product.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("search", search);


                return params;
            }

        };
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        rQueue = Volley.newRequestQueue(Product.this);
        rQueue.add(stringRequest);
    }



}

