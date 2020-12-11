//package Adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.vietis.Data.entity.Food;
//import com.example.vietis.R;
//import com.example.vietis.Data.entity.Shop;
//import com.example.vietis.UI.view_holder.SearchItemViewHolder;
//import com.example.vietis.Data.inteface.IListView;
//
//import java.util.ArrayList;
//
//public class SearchAdapter<T> extends RecyclerView.Adapter<SearchItemViewHolder> {
//    private ArrayList<T> dataObject;
//    private IListView parent;
//    final Class<T> typeParameterClass;
//
//    public SearchAdapter(IListView parent, ArrayList<T> arrayList,Class<T> typeParameterClass) {
//        this.parent = parent;
//        this.dataObject = arrayList;
//        this.typeParameterClass = typeParameterClass;
//    }
//
//    /**
//     * set data to list with array list T
//     * @param list
//     */
//    public void setObjectArray(ArrayList<T> list) {
//        this.dataObject.clear();
//        this.dataObject.addAll(list);
//        notifyDataSetChanged();
//        notifyItemInserted(0);
//    }
//
//    @NonNull
//    @Override
//    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view;
//        if(typeParameterClass==Shop.class) {
//            view = LayoutInflater
//                    .from(parent.getContext())
//                    .inflate(R.layout.view_store_item, parent, false);
//        }else{
//            view = LayoutInflater
//                    .from(parent.getContext())
//                    .inflate(R.layout.view_food_item, parent, false);
//        }
//        return new SearchItemViewHolder(view,this.parent,typeParameterClass == Shop.class);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull SearchItemViewHolder holder, int position) {
//        if(typeParameterClass == Shop.class){
//            holder.getShopItemViewHolder().setShopItem((Shop)this.dataObject.get(position), position);
//        }else{
//            holder.getFoodItemViewHolder().setFoodItem((Food)this.dataObject.get(position), position);
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataObject.size();
//    }
//}
