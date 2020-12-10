package view_holder;

import android.app.Notification;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis_fuddy.R;
import com.example.vietis_fuddy.StoreDetailActivity;

import Fragment.NotificationsFragment;
import Interface.IView;
import Ultilities.AppResources;


public class NotificationsViewHolder extends RecyclerView.ViewHolder implements IView {
    private ImageView imageShop;
    private TextView txtShopName;
    private TextView txtNotification;
    private NotificationsFragment notificationActivity;
    private LinearLayout linearLayoutShopItem;

    public NotificationsViewHolder(@NonNull View itemView) {
        super(itemView);
        mappingUI();
        setupUI();

    }
    public void setNotification(Notification notification){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (notification.getIdType().equals("1")) {
                    intent = new Intent(AppResources.getContext(), StoreDetailActivity.class);
                    intent.putExtra("id", notification.getStoreId() + "");
                } else {
                    intent = new Intent(AppResources.getContext(), FoodDetailActivity.class);
                    intent.putExtra("id", notification.getFoodId() + "");
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                AppResources.getContext().startActivity(intent);
            }
        };
        //Config.setChildViewOnClickListener(linearLayoutShopItem, listener);

//        Picasso.get().load(notification.getImageUrl())
//                .placeholder(R.drawable.ic_notification)
//                .into(imageShop);
        txtShopName.setText(notification.getTitle());
        txtNotification.setText(notification.getContent());

    }

    @Override
    public void mappingUI() {
        linearLayoutShopItem = itemView.findViewById(R.id.notification_layout);
        imageShop = itemView.findViewById(R.id.shopImage);
        txtShopName = itemView.findViewById(R.id.textShopName);
        txtNotification = itemView.findViewById(R.id.textNotification);
    }

    @Override
    public void setupUI() {
    }
}
