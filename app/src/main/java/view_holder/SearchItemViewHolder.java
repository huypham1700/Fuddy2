package view_holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.inteface.IListView;


public class SearchItemViewHolder<T> extends RecyclerView.ViewHolder {

    //UI holders
    private ShopItemViewHolder shopItemViewHolder;
    private FoodItemViewHolder foodItemViewHolder;


    public SearchItemViewHolder(@NonNull View itemView, IListView parent, boolean isStore) {
        super(itemView);
        if (isStore) {
            this.shopItemViewHolder = new ShopItemViewHolder(itemView);
            this.shopItemViewHolder.setParent(parent);
        } else {
            this.foodItemViewHolder = new FoodItemViewHolder(itemView);
            this.foodItemViewHolder.setParent(parent);
        }
    }

    public ShopItemViewHolder getShopItemViewHolder() {
        return shopItemViewHolder;
    }

    public FoodItemViewHolder getFoodItemViewHolder() {
        return foodItemViewHolder;
    }
}
