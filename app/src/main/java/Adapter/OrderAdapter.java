//package Adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import com.example.vietis.Data.entity.Order;
//import com.example.vietis.R;
//import com.example.vietis.UI.view_holder.OrderItemViewHolder;
//
//import java.util.ArrayList;
//
//public class OrderAdapter extends RecyclerView.Adapter<OrderItemViewHolder> {
//    private ArrayList<Order> arrayComment;
//
//    public OrderAdapter(ArrayList<Order> arrayComment) {
//        this.arrayComment = arrayComment;
//    }
//
//    public void setOrderArray(ArrayList<Order> list) {
//        this.arrayComment = list;
//    }
//
//    @NonNull
//    @Override
//    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.view_order_item,parent,false);
//        return new OrderItemViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
//        holder.setOrderHolder(arrayComment.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrayComment.size();
//    }
//}
