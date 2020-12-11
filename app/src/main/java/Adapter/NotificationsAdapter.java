//package Adapter;
//
//import android.app.Notification;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.vietis_fuddy.R;
//
//import java.util.ArrayList;
//
//import view_holder.NotificationsViewHolder;
//
//
//public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsViewHolder> {
//    private ArrayList<Notification> notifications;
//
//    public NotificationsAdapter(ArrayList<Notification> notifications) {
//        this.notifications = notifications;
//    }
//
//    public void setNotifications(ArrayList<Notification> notifications) {
//        this.notifications = notifications;
//    }
//
//    @NonNull
//    @Override
//    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup itemView, int viewType) {
//        View view = LayoutInflater.from(itemView.getContext())
//                .inflate(R.layout.view_notification_item, itemView, false);
//        return new NotificationsViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull NotificationsViewHolder holder, int position) {
//        holder.setNotification(notifications.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return this.notifications.size();
//    }
//}
