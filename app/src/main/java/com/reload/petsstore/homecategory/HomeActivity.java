package com.reload.petsstore.homecategory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.reload.petsstore.R;
import com.reload.petsstore.aboutus.AboutFragment;
import com.reload.petsstore.common.SessionMangment;
import com.reload.petsstore.fav.FavFragment;
import com.reload.petsstore.homecategory.homeFragment.HomeFragment;
import com.reload.petsstore.settings.settingsScreen.SettingsFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mOpenDrawer;
    CircleImageView mUserImg;
    DrawerLayout mDrawer;
    TextView mHomeItem, mFavItem, mHistoryItem, mSettingsItem, mAboutItem, mPageTitle, mUserName;
    public static int navItemIndex = 0;
    SessionMangment mSessionMangment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mSessionMangment = new SessionMangment(this);
        initViews();
        loadFragment(new HomeFragment(), "Home", 0);
    }

    private void initViews() {
        mOpenDrawer = findViewById(R.id.opne_drawer);
        mOpenDrawer.setOnClickListener(this);
        mDrawer = findViewById(R.id.drawer_layout);
        mHomeItem = findViewById(R.id.home_item);
        mFavItem = findViewById(R.id.fav_item);
        mHistoryItem = findViewById(R.id.history_item);
        mSettingsItem = findViewById(R.id.settings_item);
        mAboutItem = findViewById(R.id.about_item);
        mHomeItem.setOnClickListener(this);
        mFavItem.setOnClickListener(this);
        mHistoryItem.setOnClickListener(this);
        mSettingsItem.setOnClickListener(this);
        mAboutItem.setOnClickListener(this);
        mPageTitle = findViewById(R.id.page_title);
        mUserName = findViewById(R.id.user_name);
        mUserImg = findViewById(R.id.user_img);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.opne_drawer:
                mDrawer.openDrawer(Gravity.LEFT);
                break;
            case R.id.home_item:
                loadFragment(new HomeFragment(), "Home", 0);
                break;
            case R.id.fav_item:
                loadFragment(new FavFragment(), "Favourites", 1);
                break;
            case R.id.history_item:

                break;
            case R.id.settings_item:
                loadFragment(new SettingsFragment(), "Settings", 3);
                break;
            case R.id.about_item:
                loadFragment(new AboutFragment(), "About us", 4);
                break;

        }


    }

    void loadFragment(Fragment fragment, String pageTitle, int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.page_container, fragment).commit();
        mPageTitle.setText(pageTitle);
        mDrawer.closeDrawers();
        navItemIndex = index;

    }


    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(Gravity.LEFT)) {
            mDrawer.closeDrawer(Gravity.LEFT);
            return;
        }
        if (true) {
            if (navItemIndex != 0) {
                navItemIndex = 0;
                loadFragment(new HomeFragment(), "Home", 0);
                return;
            }
        }
        super.onBackPressed();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mUserName.setText(mSessionMangment.getUserDetails().get(SessionMangment.KEY_FNAME) + " " + mSessionMangment.getUserDetails().get(SessionMangment.KEY_LNAME));
        Glide.with(HomeActivity.this)
                .load(mSessionMangment.getUserDetails().get(SessionMangment.KEY_IMAGE))
                .placeholder(getResources().getDrawable(R.drawable.avatar))
                .into(mUserImg);


    }

}
