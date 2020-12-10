package Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.entity.Notification;
import com.example.vietis.Data.inteface.IView;
import com.example.vietis.Data.view_model.MutableArray;
import com.example.vietis.Data.view_model.NotificationActivityViewModel;
import com.example.vietis.R;
import com.example.vietis.UI.adapter.NotificationAdapter;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment implements IView {
    private static View view;
    private RecyclerView notificationRecyclerView;
    private NotificationActivityViewModel notificationActivityViewModel;
    private ArrayList<Notification> notifications;
    private static NotificationAdapter notificationAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
            View root = inflater.inflate(R.layout.fragment_notifications, container, false);
            view = root;
            new Thread(new Runnable() {
                public void run() {
                    mappingUI();
                    setupUI();
                }
            }).start();
            return root;


    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        MutableArray.clearData();
    }
    @Override
    public void mappingUI() {
        notificationRecyclerView = view.findViewById(R.id.notification_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                view.getContext(),
                RecyclerView.VERTICAL, false);
        notificationAdapter = new NotificationAdapter(new ArrayList<>());
        notificationRecyclerView.setLayoutManager(layoutManager);
        notificationRecyclerView.setAdapter(notificationAdapter);
    }

    @Override
    public void setupUI() {
        getData();
    }

    public void getData() {
        notificationActivityViewModel = new NotificationActivityViewModel(this);
        notificationActivityViewModel.getList();
    }

    public void setUpData(ArrayList<Notification> list) {
        notificationAdapter.setNotifications(list);
        notificationAdapter.notifyItemInserted(list.size());
        notificationAdapter.notifyDataSetChanged();
    }


}