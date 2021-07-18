package com.example.bead;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.Filter;
        import android.widget.Filterable;
        import android.widget.ImageView;
        import android.widget.RatingBar;
        import android.widget.TextView;
        import androidx.recyclerview.widget.RecyclerView;

        import com.bumptech.glide.Glide;

        import java.lang.annotation.Annotation;
        import java.util.ArrayList;
        import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> implements Filterable{
    // Member variables.
    private ArrayList<SalesLead> mShopingItemData = new ArrayList<>();
    private ArrayList<SalesLead> mShopingItemDataAll = new ArrayList<>();
    private Context mContext;
    private int lastPosition = -1;

    ShopAdapter(Context context, ArrayList<SalesLead> itemsData) {
        this.mShopingItemData = itemsData;
        this.mShopingItemDataAll = itemsData;
        this.mContext = context;
    }

    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ShopAdapter.ViewHolder holder, int position) {
        // Get current sport.
        SalesLead currentItem = mShopingItemData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentItem);

        /*
        if(holder.getAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastPosition = holder.getAdapterPosition();
        }*/
    }

    @Override
    public int getItemCount() {
        return mShopingItemData.size();
    }




    private Filter shopingFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<SalesLead> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(charSequence == null || charSequence.length() == 0) {
                results.count = mShopingItemDataAll.size();
                results.values = mShopingItemDataAll;
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(SalesLead item : mShopingItemDataAll) {
                    if(item.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mShopingItemData = (ArrayList)filterResults.values;
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() {
        return shopingFilter2;
    }
    private Filter shopingFilter2 = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<SalesLead> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(charSequence == null || charSequence.length() == 0) {
                results.count = mShopingItemDataAll.size();
                results.values = mShopingItemDataAll;
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(SalesLead item : mShopingItemDataAll) {
                    if(item.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mShopingItemData = (ArrayList)filterResults.values;
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {

        // Member Variables for the TextViews
        private TextView href;
        private TextView description;
        private TextView name;

        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            description = itemView.findViewById(R.id.description);
            name = itemView.findViewById(R.id.name);
            href = itemView.findViewById(R.id.href);


        }

        void bindTo(SalesLead currentItem){
            name.setText(currentItem.getName());
            description.setText(currentItem.getDescription());
            href.setText(currentItem.getHref());


            // Load the images into the ImageView using the Glide library.
        }
    }
}
