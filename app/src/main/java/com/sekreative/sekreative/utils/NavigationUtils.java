package com.sekreative.sekreative.utils;

import com.sekreative.sekreative.R;
import com.sekreative.sekreative.models.NavigationItem;

import java.util.ArrayList;

public final class NavigationUtils {

    public static final String NAV_FEED = "nav-feed";
    public static final String NAV_CHAT = "nav-chat";
    public static final String NAV_NOTIFICATIONS = "nav-notifications";
    public static final String NAV_WALLET = "nav-wallet";

    public static ArrayList<NavigationItem> getMainNavigationItems() {
        ArrayList<NavigationItem> navigationItems = new ArrayList<>();
        navigationItems.add(new NavigationItem(R.drawable.ic_home_black_24dp, R.string.title_feed, NAV_FEED));
        navigationItems.add(new NavigationItem(R.drawable.ic_message_black_24dp, R.string.title_chat, NAV_CHAT));
//        navigationItems.add(new NavigationItem(R.drawable.ic_add_black_24dp, R.string.title_add_post));
        navigationItems.add(new NavigationItem(R.drawable.ic_notifications_black_24dp, R.string.title_notifications, NAV_NOTIFICATIONS));
        navigationItems.add(new NavigationItem(R.drawable.ic_wallet_black_24dp, R.string.title_wallet, NAV_WALLET));
        return navigationItems;
    }

}
