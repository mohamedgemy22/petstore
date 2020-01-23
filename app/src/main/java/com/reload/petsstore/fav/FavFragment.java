package com.reload.petsstore.fav;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reload.petsstore.R;
import com.reload.petsstore.common.ApiService;
import com.reload.petsstore.common.SessionMangment;
import com.reload.petsstore.common.WebServiceClient;
import com.reload.petsstore.items.ItemAdapter;
import com.reload.petsstore.items.Model.ItemResponse;
import com.reload.petsstore.items.Model.ItemResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavFragment extends Fragment {
    RecyclerView mRecyclerView;
    ArrayList<ItemResult> mList;
    FavAdapter mAdapter;

    View view;

    SessionMangment mSessionMangment;


    public FavFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fav, container, false);
        mSessionMangment = new SessionMangment(getActivity());
        initViews();
        return view;
    }


    private void initViews() {

        mRecyclerView = view.findViewById(R.id.item_rv);
        mList = new ArrayList<>();
        callGetFav();
        mAdapter = new FavAdapter(mList, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    void callGetFav(){
        ApiService apiService = WebServiceClient.getRetrofit().create(ApiService.class);
        Call<ItemResponse> call = apiService.getUserFav(mSessionMangment.getUserDetails().get(SessionMangment.KEY_ID));
        call.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                mList = (ArrayList<ItemResult>) response.body().getResult();
                mAdapter = new FavAdapter(mList,getActivity());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {

            }
        });
    }
}
