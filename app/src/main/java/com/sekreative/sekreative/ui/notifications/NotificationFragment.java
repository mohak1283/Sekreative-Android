package com.sekreative.sekreative.ui.notifications;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sekreative.sekreative.R;

import androidx.fragment.app.Fragment;

public class NotificationFragment extends Fragment {

    public static NotificationFragment instantiate() {
        return new NotificationFragment();
    }

    public static final String TAG = "NotificationFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }
}
