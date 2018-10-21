package com.sekreative.sekreative.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.sekreative.sekreative.R;
import com.sekreative.sekreative.ui.addpost.AddPostFragment;
import com.sekreative.sekreative.ui.auth.AuthActivity;
import com.sekreative.sekreative.ui.feed.FeedFragment;
import com.sekreative.sekreative.ui.notifications.NotificationFragment;
import com.sekreative.sekreative.utils.NavigationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity { //implements BottomNavDrawer.MenuItemClickListener {

    private static String CURRENT_FRAGMENT = "";

   /** @BindView(R.id.bottom_bar)
    BottomAppBar bottomBar;
    @BindView(R.id.fab)
    FloatingActionButton fab; */

//    private BottomNavDrawer bottomDrawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            showFragment(FeedFragment.instantiate());
        }

     /**   fab.setOnClickListener(view -> {
            if (!CURRENT_FRAGMENT.equals(AddPostFragment.TAG)) {
                showFragment(AddPostFragment.instantiate(), AddPostFragment.TAG);
                CURRENT_FRAGMENT = AddPostFragment.TAG;
            }
        });

        bottomBar.setNavigationOnClickListener(view -> {
            bottomDrawer = BottomNavDrawer.instantiate(NavigationUtils.getMainNavigationItems());
            bottomDrawer.show(getSupportFragmentManager(), "bottom-drawer");
        }); */

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

 /**   @Override
    public void onMenuItemClicked(String tag) {
        if (bottomDrawer != null) {
          //  bottomDrawer.dismiss();
        }
        switch (tag) {
            case NavigationUtils.NAV_FEED:
                if (!CURRENT_FRAGMENT.equals(FeedFragment.TAG)) {
                    showFragment(FeedFragment.instantiate());
                    CURRENT_FRAGMENT = FeedFragment.TAG;
                }
                break;
            case NavigationUtils.NAV_NOTIFICATIONS:
                if (!CURRENT_FRAGMENT.equals(NotificationFragment.TAG)) {
                    showFragment(NotificationFragment.instantiate(), NotificationFragment.TAG);
                    CURRENT_FRAGMENT = NotificationFragment.TAG;
                }
                break;
        }
    }  */

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(this, AuthActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, AuthActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
