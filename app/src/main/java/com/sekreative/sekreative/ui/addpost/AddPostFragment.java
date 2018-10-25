package com.sekreative.sekreative.ui.addpost;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sekreative.sekreative.R;



public class AddPostFragment extends Fragment {

    public static AddPostFragment instantiate() {
        return new AddPostFragment();
    }

    public static final String TAG = "AddPostFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_post, container, false);
    }
}
