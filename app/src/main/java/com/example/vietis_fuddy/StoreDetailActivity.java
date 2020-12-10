package com.example.vietis_fuddy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.entity.Comment;
import com.example.vietis.Data.entity.Rating;
import com.example.vietis.Data.entity.Shop;
import com.example.vietis.Data.inteface.IView;
import com.example.vietis.Data.view_model.ListActivityModel;
import com.example.vietis.Data.view_model.StoreDetailActivityModel;
import com.example.vietis.UI.adapter.CommentAdapter;
import com.example.vietis.UI.adapter.SearchAdapter;
import com.example.vietis.Utilities.common.UserApp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

// To display a message in the log (logcat)

/**
 *
 */
public class StoreDetailActivity extends AppCompatActivity implements IView {
    /**
     * Local param
     */
    private NestedScrollView nsvStoreView;
    private final String TAG = "Store Detail";
    private boolean isVisible = true;

    /**
     * About for store
     */
    private ImageView imageStoreDetailIcon;
    private TextView txtStoreName;
    private TextView txtStoreAddress;
    private TextView txtStorePhone;
    private Button btnOrder;
    private Button btnGGMap;

    /**
     * Rating for store
     */
   /* private LinearLayout rate;
    private RatingBar ratingBar1;
    private TextView txtRating1;
    private RatingBar ratingBar2;
    private TextView txtRating2;
    private RatingBar ratingBar3;
    private TextView txtRating3;
    private RatingBar ratingBar4;
    private TextView txtRating4;
    private RatingBar ratingBar5;
    private TextView txtRating5;*/

    /**
     * Comment for store
     */
    private RecyclerView CommentRecyclerView;
    private CommentAdapter commentAdapter;

    /**
     * Description for store
     */
    private TextView txtStoreDescription;
    private TextView txtDescriptionVisible;

    /**
     * Food of store
     */
    //UI holders
    private RecyclerView FoodRecyclerView;
    //RecyclerView components
    private SearchAdapter foodAdapter;
    //View Model
    private ListActivityModel foodActivityModel;

    /**
     * Store Repository of store
     */
    private List<Rating> listRate = null;

    private StoreDetailActivityModel storeDetailActivityModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);

            new Thread(new Runnable() {
                public void run() {
                    mappingUI();
                    setupUI();
                    setUpData();
                }
            }).start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mappingUI();
        setupUI();
        setUpData();
    }


    @Override
    public void mappingUI() {
        nsvStoreView = findViewById(R.id.nsvStoreView);

        /**
         * About for store
         */
        imageStoreDetailIcon = findViewById(R.id.imageStoreDetailIcon);
        txtStoreName = findViewById(R.id.txtStoreName);
        txtStoreAddress = findViewById(R.id.txtStoreAddress);
        txtStorePhone = findViewById(R.id.txtStorePhone);
        btnOrder = findViewById(R.id.btnOrder);
        btnGGMap = findViewById(R.id.btnGGMap);

        /**
         * Rating for store
         */
        /*rate = findViewById(R.id.rate);
        ratingBar1 = findViewById(R.id.ratingBar1);
        txtRating1 = findViewById(R.id.txtRating1);
        ratingBar2 = findViewById(R.id.ratingBar2);
        txtRating2 = findViewById(R.id.txtRating2);
        ratingBar3 = findViewById(R.id.ratingBar3);
        txtRating3 = findViewById(R.id.txtRating3);
        ratingBar4 = findViewById(R.id.ratingBar4);
        txtRating4 = findViewById(R.id.txtRating4);
        ratingBar5 = findViewById(R.id.ratingBar5);
        txtRating5 = findViewById(R.id.txtRating5);*/

        /**
         * Comment for store
         */
        CommentRecyclerView = findViewById(R.id.CommentRecyclerView);
        commentAdapter = new CommentAdapter(new ArrayList<Comment>());


        /**
         * Description for store
         */
        txtStoreDescription = findViewById(R.id.txtStoreDescription);
        txtDescriptionVisible = findViewById(R.id.txtDescriptionVisible);

        /**
         * Food of store
         */
        //      FoodRecyclerView = findViewById(R.id.FoodRecyclerView);
//        foodAdapter = new SearchAdapter(new ArrayList<Shop>());
//        foodActivityModel = new ViewModelProvider(this).get(ListActivityModel.class);

        /**
         * Layout RecyclerView
         */
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        CommentRecyclerView.setLayoutManager(layoutManager2);
        CommentRecyclerView.setAdapter(commentAdapter);
        storeDetailActivityModel = new StoreDetailActivityModel();
    }
    @Override
    public void setupUI() {
        /*rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingFragment dialog = new RatingFragment(StoreDetailActivity.this);
                int width = (int) (getResources().getDisplayMetrics().widthPixels);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.5);
                dialog.getWindow().setLayout(width, height);
                dialog.show();
            }
        });*/
    }

    /**
     *
     */
    public void setUpData() {
        /**
         *  get store detail
         */
        Intent parent = getIntent();
        Bundle b = parent.getExtras();
        String id = null;
        if (b != null) {
            id = b.getString("id");
        }
        storeDetailActivityModel.getStoreDetail(UserApp.user.getTokenKey(), id);
        storeDetailActivityModel.getShop().observe(this, new Observer<Shop>() {
            @Override
            public void onChanged(Shop shop) {
                setUpStoreDetail(shop);
            }
        });
        storeDetailActivityModel.getComments().observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                setUpStoreComment(comments);
            }
        });
    }

    public void setUpStoreComment(List<Comment> comments) {
        commentAdapter.setCommentArray(comments);
        commentAdapter.notifyDataSetChanged();
        nsvStoreView.scrollTo(0, 0);
    }

    public void setUpStoreDetail(Shop shop) {
//        listRate = new ArrayList<>();
//        for (int i = 1; i < objects.size(); i++) {
//            listRate.add((Rating) objects.get(i));
//        }

        //Store description
        txtStoreName.setText(shop.getName());
        txtStoreAddress.setText(shop.getAddress());
        txtStorePhone.setText("Hotline: " + shop.getPhoneNumber());
        txtStoreDescription.setText(shop.getDescription());
        Picasso.get().load(shop.getImageURL())
                .placeholder(R.drawable.ic_launcher_foreground)
                .resize(150, 150)
                .centerCrop()
                .into(imageStoreDetailIcon);
        //Rating section
//        txtRating1.setText(listRate.get(0).getVoteCount() + " lượt đánh giá");
//        txtRating2.setText(listRate.get(1).getVoteCount() + " lượt đánh giá");
//        txtRating3.setText(listRate.get(2).getVoteCount() + " lượt đánh giá");
//        txtRating4.setText(listRate.get(3).getVoteCount() + " lượt đánh giá");
//        txtRating5.setText(listRate.get(4).getVoteCount() + " lượt đánh giá");

    }
}