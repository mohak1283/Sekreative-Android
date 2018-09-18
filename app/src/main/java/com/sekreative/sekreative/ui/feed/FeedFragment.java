package com.sekreative.sekreative.ui.feed;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sekreative.sekreative.R;

import androidx.fragment.app.Fragment;

public class FeedFragment extends Fragment {

    public static FeedFragment instantiate() {
        return new FeedFragment();
    }

    public static final String TAG = "FeedFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }
}
