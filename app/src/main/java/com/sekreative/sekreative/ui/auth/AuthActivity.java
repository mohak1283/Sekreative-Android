package com.sekreative.sekreative.ui.auth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.sekreative.sekreative.R;

import butterknife.ButterKnife;


public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);


        if (savedInstanceState == null) {
            showFragment(AuthFragment.instantiate());
        }

    }

    private void showFragment(Fragment fragment) {
        showFragment(fragment, "");
    }

    private void showFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_auth, fragment);
        if (!TextUtils.isEmpty(tag)) {
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }

}
