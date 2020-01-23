package com.reload.petsstore.itemdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.reload.petsstore.R;
import com.reload.petsstore.common.ApiService;
import com.reload.petsstore.common.SessionMangment;
import com.reload.petsstore.common.WebServiceClient;
import com.reload.petsstore.itemdetails.Model.ItemDetailsResponse;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailsActivity extends AppCompatActivity {
ArrayList<String> mList = new ArrayList<>();
    SliderAdapterExample adapter;
    SliderView sliderView;
    SessionMangment mSessionMangment;
    String mRecivedName , mRecivedID ;
    TextView mPageTitle , mItemTitle ,mItemDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        mSessionMangment = new SessionMangment(this);
        Intent intent = getIntent();
        mRecivedID = intent.getStringExtra("itemId");
        mRecivedName = intent.getStringExtra("itemName");
        mPageTitle = findViewById(R.id.page_title);
        mPageTitle.setText(mRecivedName);
        mItemTitle =findViewById(R.id.item_title);
        mItemDesc = findViewById(R.id.item_desc);

        sliderView = findViewById(R.id.imageSlider);

         adapter = new SliderAdapterExample(this,mList);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }

    @Override
    protected void onResume() {
        super.onResume();
        callItemDetilsApi();
    }

    void callItemDetilsApi(){
        ApiService apiService = WebServiceClient.getRetrofit().create(ApiService.class);
        Call<ItemDetailsResponse> call = apiService.getItemDetails(mRecivedID,"en",mSessionMangment.getUserDetails().get(SessionMangment.KEY_ID));
        call.enqueue(new Callback<ItemDetailsResponse>() {
            @Override
            public void onResponse(Call<ItemDetailsResponse> call, Response<ItemDetailsResponse> response) {
                mList = (ArrayList<String>) response.body().getResult().get(0).getImages();
                adapter = new SliderAdapterExample(ItemDetailsActivity.this,mList);
                sliderView.setSliderAdapter(adapter);
                mItemTitle.setText(response.body().getResult().get(0).getName());
                mItemDesc.setText(response.body().getResult().get(0).getDescription());
            }

            @Override
            public void onFailure(Call<ItemDetailsResponse> call, Throwable t) {

            }
        });
    }
}
