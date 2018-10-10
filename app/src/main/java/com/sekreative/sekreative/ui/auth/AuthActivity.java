package com.sekreative.sekreative.ui.auth;

import android.os.Bundle;
import android.text.TextUtils;

import com.sekreative.sekreative.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        if (savedInstanceState == null) {
            showFragment(LoginFragment.instantiate());
        }

    }

    private void showFragment(Fragment fragment) {
        showFragment(fragment, "");
    }

    private void showFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (!TextUtils.isEmpty(tag)) {
            transaction.addToBackStack(tag);
        }

        transaction.replace(R.id.frame_auth, fragment);
    }

}
