//package Adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.vietis.R;
//import com.example.vietis.Data.entity.Shop;
//import com.example.vietis.UI.view_holder.ShopItemViewHolder;
//
//import java.util.ArrayList;
//
//public class StoreAdapter extends RecyclerView.Adapter<ShopItemViewHolder> {
//    private ArrayList<Shop> arrayListShop;
//
//    public StoreAdapter(ArrayList<Shop> arrayListShop) {
//        this.arrayListShop = arrayListShop;
//    }
//
//    public void setShopArray(ArrayList<Shop> list) {
//        this.arrayListShop = list;
//    }
//
//    public ArrayList<Shop> getShopArray() {
//        return this.arrayListShop;
//    }
//
//    @NonNull
//    @Override
//    public ShopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater
//                .from(parent.getContext())
//                .inflate(R.layout.view_store_item, parent, false);
//        ShopItemViewHolder itemHolder = new ShopItemViewHolder(view);
//        return itemHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ShopItemViewHolder holder, int position) {
//        holder.setShopItem(this.arrayListShop.get(position), position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrayListShop.size();
//    }
//}
