package com.example.swiggyclone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.swiggyclone.adapter.RestaurantDetailsAdapter;
import com.example.swiggyclone.model.RestaurantDetails;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodFragment extends Fragment {
    String url;
    private RecyclerView recyclerView;
    private List<RestaurantDetails> restaurantDetails = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FoodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodFragment newInstance(String param1, String param2) {
        FoodFragment fragment = new FoodFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_food, container, false);

        View rootView = inflater.inflate(R.layout.fragment_food,container,false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        url = "https://837d2133-b203-4f5e-baf8-0a61e87e3c80.mock.pstmn.io/restaurants";
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("response",response.toString());
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("restaurants");
                    Log.d("res",jsonArray.toString());
                    fetchTheData(jsonArray);

                } catch (Exception e) {
                    e.printStackTrace();
                }}
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
        queue.add(request);
        return rootView;
    }

    //Fetch the data from restaurant API
    private void fetchTheData(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
            JSONObject restaurant =jsonArray.getJSONObject(i);
            restaurantDetails.add(new RestaurantDetails(
                    restaurant.getString("name"),
                    restaurant.get("ratings").toString(),
                    restaurant.getString("cuisine"),
                    restaurant.getString("address"),
                    restaurant.getString("thumbnail")));
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Restaurant Details Error", Toast.LENGTH_SHORT).show();
            }
        }

        RestaurantDetailsAdapter adapter = new RestaurantDetailsAdapter(restaurantDetails,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}