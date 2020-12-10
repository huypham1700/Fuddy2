package view_holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.entity.Order;
import com.example.vietis.Data.inteface.IView;
import com.example.vietis.R;
import com.squareup.picasso.Picasso;

public class OrderItemViewHolder extends RecyclerView.ViewHolder implements IView {

    //UI holders
    private ImageView imgOrder;
    private TextView txtDateCreate;
    private TextView txtOrderDescription;
    private TextView txtOrderQuantity;
    private TextView txtOrderStatus;

    public OrderItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mappingUI();
        setupUI();
    }

    public void setOrderHolder(Order comment) {
        Picasso.get().load(comment.getImageURL())
                .placeholder(R.drawable.ic_launcher_foreground)
                .resize(80, 80)
                .centerCrop()
                .into(imgOrder);
        txtDateCreate.setText(comment.getCreateAt());
        txtOrderDescription.setText(comment.getDescription());
        txtOrderStatus.setText(comment.getStatus() == 1 ? "Coming" : comment.getStatus() == 2 ? "Completed" : "Canceled");
        //txtOrderStatus.setBackground(comment.getStatus() == 1 ?  R.drawable.order_coming
        //        : comment.getStatus() == 2 ? R.drawable.order_completed
        //        :  R.drawable.order_cancel);

    }

    @Override
    public void mappingUI() {
        imgOrder = itemView.findViewById(R.id.imgOrder);
        txtDateCreate = itemView.findViewById(R.id.txtDateCreate);
        txtOrderDescription = itemView.findViewById(R.id.txtOrderDescription);
        txtOrderQuantity = itemView.findViewById(R.id.txtOrderQuantity);
        txtOrderStatus = itemView.findViewById(R.id.txtOrderStatus);
    }

    @Override
    public void setupUI() {

    }
}
