package com.example.manikkam.myapplication.Activities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.manikkam.myapplication.DB.DBHandler;
import com.example.manikkam.myapplication.R;
import com.example.manikkam.myapplication.Utils.CommonFuns;
import com.example.manikkam.myapplication.Utils.Constants;
import com.example.manikkam.myapplication.Utils.GlobalValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ProductAdapter mProductAdapter;
    ArrayList<Product> mList;

    GlobalValues mGlobalValues;
    DBHandler mDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBHandler = new DBHandler(this);
        mGlobalValues = new GlobalValues(this);
        mRecyclerView= (RecyclerView) findViewById(R.id.product_recyclerview);




        mDBHandler.deleteAll();
        getDetailsFromServer();
    }

    void getDetailsFromServer() {

        if (CommonFuns.isNetworkConnected(this)) {

            CommonFuns.showProgressDialog(this);

            // Get
            //AndroidNetworking.get(Constants.GET_DETAILS)

            AndroidNetworking.post(Constants.GET_DETAILS)
                    .setTag("GetDetails")
                    .setPriority(Priority.HIGH)
                    .addBodyParameter("name", "34") // Parameter Name and Value
                    .addBodyParameter("pass", "454")
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // do anything with response


                            Log.d("TAGW", "Server Response:" + response);

                            CommonFuns.stopProgressDialog(MainActivity.this);

                            try {

                                response=new JSONObject(Constants.SampleJSON);

                                if(response.has("items"))
                                {
                                    JSONArray itemArray=response.getJSONArray("items");

                                    for(int i=0;i<itemArray.length();i++) {
                                        JSONObject jObj=itemArray.getJSONObject(i);
                                        mDBHandler.addProductJsonData(jObj.getString("id"),jObj.getString("name"),jObj.getString("price"),jObj.getString("uom"));
                                    }
                                    Toast.makeText(MainActivity.this, "Stored in Local Database", Toast.LENGTH_SHORT).show();

                                    fetchProducts();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(ANError error) {
                            // handle error
                            //prepareResponse(error.getErrorBody());
                            CommonFuns.stopProgressDialog(MainActivity.this);
                            Toast.makeText(MainActivity.this, error.getErrorBody(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

	/*
    *  Fetch Details from Local Database
    * */

    void fetchProducts() {

        mList=new ArrayList<>();
        Cursor cursor = mDBHandler.getControlData();

        if (cursor != null) {

            if (cursor.moveToFirst()) {
                do {

                    Product item=new Product();
                    item.setId(cursor.getString(0));
                    item.setName(cursor.getString(1));
                    item.setUom(cursor.getString(2));
                    item.setPrice(cursor.getString(3));
                    mList.add(item);

                } while (cursor.moveToNext());
            }

        }

        if(mList.size()>0) {

            if (mProductAdapter == null) {

                mProductAdapter = new ProductAdapter(this, mList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
                mRecyclerView.setAdapter(mProductAdapter);
                mProductAdapter.notifyDataSetChanged();

                mProductAdapter.setOnClickListener(new ProductAdapter.OnClickListener() {
                    @Override
                    public void onLayoutClick(int position) {

                    }
                });
            } else {
                mProductAdapter.setProductList(mList);
                mProductAdapter.notifyDataSetChanged();
            }
        }

    }

}
