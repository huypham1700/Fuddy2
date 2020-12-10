package Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.entity.Food;
import com.example.vietis.Data.inteface.IListView;
import com.example.vietis.Data.inteface.IView;
import com.example.vietis.Data.view_model.ListActivityModel;
import com.example.vietis.Data.view_model.MutableArray;
import com.example.vietis.R;
import com.example.vietis.UI.adapter.SearchAdapter;
import com.example.vietis.Utilities.common.AppResources;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements IView, IListView {

    //private TextView txtSeeMore;
    private SearchView svSearch;
    private static View view;
    private RecyclerView recyclerViewSearch;
    private static SearchAdapter<Food> foodAdapter;
    private ListActivityModel foodActivityModel;
    private int offset = 0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
            View root = inflater.inflate(R.layout.fragment_home, container, false);
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
    public void onStop() {
        super.onStop();
        offset = 0;
        MutableArray.clearData();
    }
    

    @Override
    public void mappingUI() {
        svSearch = view.findViewById(R.id.svSearch);
        //txtSeeMore = view.findViewById(R.id.txtSeeMore);
        recyclerViewSearch = view.findViewById(R.id.recyclerViewFoodSearch);
        foodAdapter = new SearchAdapter(this, new ArrayList<Food>(), Food.class);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.VERTICAL, false);

        recyclerViewSearch.setLayoutManager(layoutManager);
        recyclerViewSearch.setAdapter(foodAdapter);
    }

    @Override
    public void setupUI() {
        svSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        getData();
    }

    public void getData() {
        foodActivityModel = new ListActivityModel(com.example.vietis.activities.Home.ui.home.HomeFragment.this);
        foodActivityModel.searchFoodFormServerWithPage("", offset);
    }

    public void setUpData(ArrayList<Food> list) {
        foodAdapter.setObjectArray(list);
        foodAdapter.notifyItemInserted(list.size());
        foodAdapter.notifyDataSetChanged();
    }

    @Override
    public void navigateToStoreDetail(Integer idStore) {
        Intent intent = new Intent(view.getContext(), FoodDetailActivity.class);
        intent.putExtra("id", idStore + "");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppResources.getContext().startActivity(intent);
    }

    @Override
    public void navigateToFoodDetail(Integer idFood) {
        //lalalalala
    }
}