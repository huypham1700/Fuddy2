package view_holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis_fuddy.R;
import com.squareup.picasso.Picasso;

import Entity.Shop;
import Interface.IListView;
import Interface.IView;

public class ShopItemViewHolder extends RecyclerView.ViewHolder implements IView {

    //UI holders
    private LinearLayout linearLayoutShopItem;
    private ImageView imageViewShopIcon;
    private TextView textViewShopName;
    private TextView textViewShopAddress;
    private RatingBar ratingBarShopRating;
    private TextView textViewVoucher;

    //Parent
    private IListView parent;

    public ShopItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mappingUI();
        setupUI();
    }

    public void setParent(IListView parent) {
        this.parent = parent;
    }

    public void setShopItem(final Shop shop, int pos) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parent != null) {
                    parent.navigateToStoreDetail(shop.getID());
                }
            }
        };
//        Config.setChildViewOnClickListener(linearLayoutShopItem, listener);

        Picasso.get().load(shop.getImageURL())
                .placeholder(R.drawable.ic_launcher_foreground)
                .resize(100, 100)
                .centerCrop()
                .into(imageViewShopIcon);
        textViewShopName.setText(shop.getName());
        textViewShopAddress.setText(shop.getAddress());
        ratingBarShopRating.setRating(shop.getRating());
    }

    @Override
    public void mappingUI() {
        linearLayoutShopItem = itemView.findViewById(R.id.linearShopItem);
        imageViewShopIcon = itemView.findViewById(R.id.imageViewShopIcon);
        textViewShopName = itemView.findViewById(R.id.textViewShopName);
        textViewShopAddress = itemView.findViewById(R.id.textViewShopAddress);
        ratingBarShopRating = itemView.findViewById(R.id.ratingBarShopRating);
        textViewVoucher = itemView.findViewById(R.id.textViewVoucher);
    }

    @Override
    public void setupUI() {
    }
}
