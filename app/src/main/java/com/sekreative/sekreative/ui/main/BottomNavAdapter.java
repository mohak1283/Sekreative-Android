package com.sekreative.sekreative.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sekreative.sekreative.R;
import com.sekreative.sekreative.models.NavigationItem;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomNavAdapter extends RecyclerView.Adapter<BottomNavAdapter.NavigationViewHolder> {

    private ArrayList<NavigationItem> navigationItems = new ArrayList<>();
    private Context context;
    private NavItemListener mListener;

    public BottomNavAdapter(Context context, ArrayList<NavigationItem> navigationItems) {
        this.context = context;
        this.navigationItems.addAll(navigationItems);
    }

    @Override
    public NavigationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_navigation_main, parent, false);
        return new NavigationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NavigationViewHolder holder, int position) {

        NavigationItem navItem = navigationItems.get(position);
        holder.bind(navItem);

    }

    public void setNavListener(NavItemListener listener) {
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        return navigationItems.size();
    }

    class NavigationViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_nav_icon)
        ImageView imgNavIcon;
        @BindView(R.id.tv_nav_title)
        TextView tvNavTitle;

        NavigationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onNavItemClicked(navigationItems.get(getAdapterPosition()));
                    }
                }
            });

        }

        void bind(NavigationItem item) {
            imgNavIcon.setImageResource(item.getIcon());
            tvNavTitle.setText(item.getTitle());
        }
    }

    public interface NavItemListener {
        void onNavItemClicked(NavigationItem item);
    }
}
