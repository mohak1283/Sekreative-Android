package com.sekreative.sekreative.ui.wallet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sekreative.sekreative.R;

import androidx.fragment.app.Fragment;

public class WalletFragment extends Fragment {

    public static WalletFragment instantiate() {
        return new WalletFragment();
    }

    public static final String TAG = "WalletFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmet_wallet, container, false);
    }
}
