package com.example.hadadarazapp.retrofit_class;

import com.example.hadadarazapp.modal.CollectionModal;
import com.example.hadadarazapp.modal.ProductModal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    // Node API for products
    @GET("daraz_products")
    Call<List<ProductModal>> getProduct();

    // Node API for collections
    @GET("daraz_collections")
    Call<List<CollectionModal>> getCollection();

}