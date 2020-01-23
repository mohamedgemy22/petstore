package com.reload.petsstore.homecategory.homeFragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reload.petsstore.R;
import com.reload.petsstore.common.ApiService;
import com.reload.petsstore.common.WebServiceClient;
import com.reload.petsstore.homecategory.HomeActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    View view;
    RecyclerView mRecyclerView;
    ArrayList<HomeCatResult> mList;
    HomeAdapter mAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        initViews();
        return view;
    }

    private void initViews() {
        mRecyclerView = view.findViewById(R.id.home_rv);
        mList = new ArrayList<>();
        callGetAllCatApi();
        mAdapter = new HomeAdapter(mList, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(layoutManager);

    }

    private void callGetAllCatApi() {
        ApiService apiService = WebServiceClient.getRetrofit().create(ApiService.class);
        Call<HomeCatResponse> call = apiService.getAllCat();


        call.enqueue(new Callback<HomeCatResponse>() {
            @Override
            public void onResponse(Call<HomeCatResponse> call, Response<HomeCatResponse> response) {
                mList = (ArrayList<HomeCatResult>) response.body().getResult();
                mAdapter = new HomeAdapter(mList, getActivity());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<HomeCatResponse> call, Throwable t) {

            }
        });
    }

}
