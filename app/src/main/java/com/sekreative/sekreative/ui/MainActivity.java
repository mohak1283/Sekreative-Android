package com.sekreative.sekreative.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sekreative.sekreative.R;
import com.sekreative.sekreative.ui.chat.ChatFragment;
import com.sekreative.sekreative.ui.feed.FeedFragment;
import com.sekreative.sekreative.ui.notifications.NotificationFragment;
import com.sekreative.sekreative.ui.wallet.WalletFragment;
import com.sekreative.sekreative.utils.NavigationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavDrawer.MenuItemClickListener {

    private static String CURRENT_FRAGMENT = "";

    @BindView(R.id.bottom_bar)
    BottomAppBar bottomBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private BottomNavDrawer bottomDrawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            showFragment(FeedFragment.instantiate());
        }

        bottomBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, "Id is " + item.getItemId(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        bottomBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDrawer = BottomNavDrawer.instantiate(NavigationUtils.getMainNavigationItems());
                bottomDrawer.show(getSupportFragmentManager(), "bottom-drawer");
            }
        });

    }

    private void showFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frame_main, fragment);
        if (!tag.equals("")) {
            transaction.addToBackStack(tag);
        }

        transaction.commit();
    }

    private void showFragment(Fragment fragment) {
        showFragment(fragment, "");
    }

    @Override
    public void onMenuItemClicked(String tag) {
        if (bottomDrawer != null) {
            bottomDrawer.dismiss();
        }
        switch (tag) {
            case NavigationUtils.NAV_FEED:
                if (!CURRENT_FRAGMENT.equals(FeedFragment.TAG)) {
                    showFragment(FeedFragment.instantiate());
                    CURRENT_FRAGMENT = FeedFragment.TAG;
                }
                break;
            case NavigationUtils.NAV_CHAT:
                if (!CURRENT_FRAGMENT.equals(ChatFragment.TAG)) {
                    showFragment(ChatFragment.instantiate());
                    CURRENT_FRAGMENT = ChatFragment.TAG;
                }
                break;
            case NavigationUtils.NAV_NOTIFICATIONS:
                if (!CURRENT_FRAGMENT.equals(NotificationFragment.TAG)) {
                    showFragment(NotificationFragment.instantiate());
                    CURRENT_FRAGMENT = NotificationFragment.TAG;
                }
                break;
            case NavigationUtils.NAV_WALLET:
                if (!CURRENT_FRAGMENT.equals(WalletFragment.TAG)) {
                    showFragment(WalletFragment.instantiate());
                    CURRENT_FRAGMENT = WalletFragment.TAG;
                }
                break;
        }
    }
}
