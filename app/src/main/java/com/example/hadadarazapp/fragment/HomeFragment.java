package com.example.hadadarazapp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.hadadarazapp.adapter.MyAdapter;
import com.example.hadadarazapp.adapter.RecyclerviewAdapter;
import com.example.hadadarazapp.adapter.RecyclerviewProductAdapter;
import com.example.hadadarazapp.modal.CollectionModal;
import com.example.hadadarazapp.modal.ProductModal;
import com.example.hadadarazapp.retrofit_class.ApiInterface;
import com.example.hadadarazapp.retrofit_class.Client;
import com.example.hadadarazapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    RecyclerView recyclerViewCollection, recyclerViewProduct;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] slideImage= {R.drawable.slider1,R.drawable.slider2,R.drawable.slider3,
            R.drawable.slider4,R.drawable.slider5,R.drawable.slider6};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.colorWhite));// set status bac

        recyclerViewCollection = view.findViewById(R.id.recyclerView_collection);
        recyclerViewProduct = view.findViewById(R.id.recyclerView_product);

        recyclerViewCollection.setNestedScrollingEnabled(false);
        recyclerViewProduct.setNestedScrollingEnabled(false);
        getCollection();
        getProduct();

        return view;
    }

    // collection json
    public void getCollection(){
        ApiInterface retrofitApiInterface = Client.getRetrofit().create(ApiInterface.class);
        Call<List<CollectionModal>> productModalCall = retrofitApiInterface.getCollection();
        productModalCall.enqueue(new Callback<List<CollectionModal>>() {
            @Override
            public void onResponse(Call<List<CollectionModal>> call, Response<List<CollectionModal>> response) {
                System.out.println("Collection list " + response.body());
                RecyclerviewAdapter recyclerviewAdapter = new RecyclerviewAdapter(getActivity(),response.body());
                // elevation design
                LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.HORIZONTAL, false);
                recyclerViewCollection.setLayoutManager(horizontalLayoutManager);
                recyclerViewCollection.setHasFixedSize(true);
                recyclerViewCollection.setAdapter(recyclerviewAdapter);
                recyclerviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CollectionModal>> call, Throwable t) {

            }
        });
    }

    // product json
    public void getProduct(){
        ApiInterface retrofitApiInterface = Client.getRetrofit().create(ApiInterface.class);
        Call<List<ProductModal>> productModalCall = retrofitApiInterface.getProduct();
        productModalCall.enqueue(new Callback<List<ProductModal>>() {
            @Override
            public void onResponse(Call<List<ProductModal>> call, Response<List<ProductModal>> response) {
                System.out.println("Product list " + response.body());
                RecyclerviewProductAdapter recyclerviewAdapter = new RecyclerviewProductAdapter(getActivity(),response.body());
                RecyclerView.LayoutManager mlayoutManager = new GridLayoutManager(getContext(), 2);
                recyclerViewProduct.setLayoutManager(mlayoutManager);
                recyclerViewProduct.setHasFixedSize(true);
                recyclerViewProduct.setAdapter(recyclerviewAdapter);
                recyclerviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ProductModal>> call, Throwable t) {

            }
        });
    }
}