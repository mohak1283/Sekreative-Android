package com.sekreative.sekreative.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.sekreative.sekreative.R;
import com.sekreative.sekreative.models.NavigationItem;
import com.sekreative.sekreative.ui.main.BottomNavAdapter;

import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;

/**public class BottomNavDrawer extends BottomSheetDialogFragment {

    @BindView(R.id.rv_menu_options)
    RecyclerView rvMenuOptions;

    public static BottomNavDrawer instantiate(ArrayList<NavigationItem> navigationItems) {
        BottomNavDrawer drawerFragment = new BottomNavDrawer();
        Bundle arguments = new Bundle();
        arguments.putParcelableArrayList(EXTRAS_NAVIGATION_ITEMS, navigationItems);
        drawerFragment.setArguments(arguments);
        return drawerFragment;
    }

    private static final String EXTRAS_NAVIGATION_ITEMS = "extras-navigation-items";
    private MenuItemClickListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottomsheet, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        if (arguments == null || !arguments.containsKey(EXTRAS_NAVIGATION_ITEMS)) {
            throw new RuntimeException("Navigation menu items not passed in");
        }

        ArrayList<NavigationItem> navigationItems = arguments.getParcelableArrayList(EXTRAS_NAVIGATION_ITEMS);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);
        BottomNavAdapter bottomNavAdapter = new BottomNavAdapter(requireContext(), navigationItems);

        rvMenuOptions.setLayoutManager(linearLayoutManager);
        rvMenuOptions.setAdapter(bottomNavAdapter);

        bottomNavAdapter.setNavListener(item -> mListener.onMenuItemClicked(item.getTag()));

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MenuItemClickListener) {
            mListener = (MenuItemClickListener) context;
        } else {
            throw new ClassCastException("" + context + " should instantiate MenuItemClickListener");
        }
    }

    public interface MenuItemClickListener {
        void onMenuItemClicked(String tag);
    }

} */
