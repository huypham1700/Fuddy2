package Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.entity.Order;
import com.example.vietis.Data.inteface.IView;
import com.example.vietis.Data.view_model.MutableArray;
import com.example.vietis.Data.view_model.OrderActivityModel;
import com.example.vietis.R;
import com.example.vietis.UI.adapter.OrderAdapter;

import java.util.ArrayList;

public class OrderFragment extends Fragment implements IView {

    //UI holders
    private static View view;
    private RecyclerView recyclerOrderViewSearch;
    private static OrderAdapter orderAdapter;
    //View Model
    private OrderActivityModel orderViewModel;
    private int PAGE = 0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
            View root = inflater.inflate(R.layout.fragment_order, container, false);
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
        PAGE = 0;
        MutableArray.clearData();
    }


    @Override
    public void mappingUI() {
        recyclerOrderViewSearch = view.findViewById(R.id.recyclerOrderViewSearch);
        orderAdapter = new OrderAdapter(new ArrayList<>());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerOrderViewSearch.setLayoutManager(layoutManager);
        recyclerOrderViewSearch.setAdapter(orderAdapter);
    }


    @Override
    public void setupUI() {
        getData();
    }

    public void getData() {
        orderViewModel = new OrderActivityModel(this);
        orderViewModel.getAll();
    }

    public void setUpData(ArrayList<Order> list) {
        orderAdapter.setOrderArray(list);
        orderAdapter.notifyItemInserted(list.size());
        orderAdapter.notifyDataSetChanged();
    }
}