package com.example.kccistc.recylerviewjsonexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_1 extends Fragment {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;

    public Fragment_1() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_1, null);

        mRecyclerView = v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(getActivity());
        parseJSON();


        return v;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mRecyclerView = findViewById(R.id.recycler_view);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        mExampleList = new ArrayList<>();
//
//        mRequestQueue = Volley.newRequestQueue(this);
//        parseJSON();
//
//    }


    private void parseJSON() {
        String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?numOfRows=20&ServiceKey=wDDX3rgEb9dPsphDWqvjLp3UstanaN%2BDtGoSoCOiPgtfAi9JZF6zELCxRvbIp%2FjtVEN8CVgTIR0ANa5%2F41toAg%3D%3D&arrange=P&contentTypeid=12&areaCode=1&MobileOS=AND&cat1=A02&cat2=A0201&cat3=A02010100&MobileApp=AppTest&_type=json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject responses) {   // json 반응 함수
                        try {

                            JSONObject response = responses.getJSONObject("response");
                            JSONObject body = response.getJSONObject("body");
                            JSONObject items = body.getJSONObject("items");
                            JSONArray jsonArray = items.getJSONArray("item");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject item = jsonArray.getJSONObject(i);

                                String creatorName = item.getString("title");
                                String imageUrl = item.getString("firstimage");
                                int likeCount = item.getInt("readcount");

                                mExampleList.add(new ExampleItem(imageUrl, creatorName, likeCount));

                            }

                            mExampleAdapter = new ExampleAdapter(getActivity(), mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }


}

//public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnItemClickListener {
//    private RecyclerView mRecyclerView;
//    private ExampleAdapter mExampleAdapter;
//    private ArrayList<ExampleItem> mExampleList;
//    private RequestQueue mRequestQueue;
//
//    public static final String EXTRA_URL = "imageUrl";
//    public static final String EXTRA_CREATOR = "creator";
//    public static final String EXTRA_LIKES = "likeCount";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mRecyclerView = findViewById(R.id.recycler_view);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        mExampleList = new ArrayList<>();
//
//        mRequestQueue = Volley.newRequestQueue(this);
//        parseJSON();
//
//    }
//


//////////////////////////////////////////////////JSON이다
//    private void parseJSON() {
//        String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?numOfRows=20&ServiceKey=wDDX3rgEb9dPsphDWqvjLp3UstanaN%2BDtGoSoCOiPgtfAi9JZF6zELCxRvbIp%2FjtVEN8CVgTIR0ANa5%2F41toAg%3D%3D&arrange=P&contentTypeid=12&areaCode=1&MobileOS=AND&cat1=A02&cat2=A0201&cat3=A02010100&MobileApp=AppTest&_type=json";
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject responses) {   // json 반응 함수
//                        try {
//
//                            JSONObject response = responses.getJSONObject("response");
//                            JSONObject body = response.getJSONObject("body");
//                            JSONObject items = body.getJSONObject("items");
//                            JSONArray jsonArray = items.getJSONArray("item");
//
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject item = jsonArray.getJSONObject(i);
//
//                                String creatorName = item.getString("title");
//                                String imageUrl = item.getString("firstimage");
//                                int likeCount = item.getInt("readcount");
//
//                                mExampleList.add(new ExampleItem(imageUrl, creatorName, likeCount));
//
//                            }
//
//                            mExampleAdapter = new ExampleAdapter(MainActivity.this, mExampleList);
//                            mRecyclerView.setAdapter(mExampleAdapter);
//                            mExampleAdapter.setOnItemClickListener(MainActivity.this);
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//
//        mRequestQueue.add(request);
//    }
//
//    @Override
//    public void onItemClick(int position) {
//        Intent detailIntent = new Intent(this, DetailActivity.class);
//        ExampleItem clickedItem = mExampleList.get(position);
//        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
//        detailIntent.putExtra(EXTRA_CREATOR, clickedItem.getCreator());
//        detailIntent.putExtra(EXTRA_LIKES, clickedItem.getLikeCount());
//        startActivity(detailIntent);
//    }
//}