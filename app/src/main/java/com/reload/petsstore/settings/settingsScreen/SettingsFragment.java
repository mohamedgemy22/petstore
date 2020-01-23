package com.reload.petsstore.settings.settingsScreen;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reload.petsstore.R;
import com.reload.petsstore.settings.editprofile.EditProfileActivity;
import com.reload.petsstore.settings.language.ChangeLanguageFragment;
import com.reload.petsstore.settings.logout.LogOutFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {
    View view;
    CardView mEditProfile, mLanguage, mContactUs, mShareApp, mRateApp, mLogOut;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings, container, false);

        initViews();
        return view;
    }

    private void initViews() {
        mEditProfile = view.findViewById(R.id.edit_profile);
        mLanguage = view.findViewById(R.id.language);
        mContactUs = view.findViewById(R.id.contact_us);
        mShareApp = view.findViewById(R.id.share_app);
        mRateApp = view.findViewById(R.id.rate_app);
        mLogOut = view.findViewById(R.id.log_out);

        mEditProfile.setOnClickListener(this);
        mLanguage.setOnClickListener(this);
        mContactUs.setOnClickListener(this);
        mShareApp.setOnClickListener(this);
        mRateApp.setOnClickListener(this);
        mLogOut.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.edit_profile:
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
                break;

            case R.id.language:

                ChangeLanguageFragment changeLanguageFragment = new ChangeLanguageFragment();
                changeLanguageFragment.show(getChildFragmentManager(), "");
                break;
            case R.id.contact_us:

                break;
            case R.id.share_app:
                shareApp();
                break;
            case R.id.rate_app:
                RateApp();
                break;
            case R.id.log_out:
                LogOutFragment logOutFragment = new LogOutFragment();
                logOutFragment.show(getChildFragmentManager(), "");
                break;


        }
    }

    private void shareApp() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Share Application");
        i.putExtra(Intent.EXTRA_TEXT,
                "https://play.google.com/store/apps/details?id=" +
                        getActivity().getApplicationContext().getPackageName());
        startActivity(Intent.createChooser(i, "مشاركة مع"));

    }


    private void RateApp() {
        Uri uri = Uri.parse("market://details?id=" + this.getActivity().getApplicationContext().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + this.getActivity().getApplicationContext().getPackageName())));
        }

    }


}
