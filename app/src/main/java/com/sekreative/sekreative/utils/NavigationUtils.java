package com.sekreative.sekreative.utils;

import com.sekreative.sekreative.R;
import com.sekreative.sekreative.models.NavigationItem;

import java.util.ArrayList;

public final class NavigationUtils {

    public static final String NAV_FEED = "nav-feed";
    public static final String NAV_NOTIFICATIONS = "nav-notifications";

    public static ArrayList<NavigationItem> getMainNavigationItems() {
        ArrayList<NavigationItem> navigationItems = new ArrayList<>();
        navigationItems.add(new NavigationItem(R.drawable.ic_home_black_24dp, R.string.title_feed, NAV_FEED));
        navigationItems.add(new NavigationItem(R.drawable.ic_notifications_black_24dp, R.string.title_notifications, NAV_NOTIFICATIONS));
        return navigationItems;
    }

}
