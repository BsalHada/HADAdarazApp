package com.example.hadadarazapp.modal;

public class ProductModal {
    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProduct_image() {
        return image;
    }

    public void setProduct_image(String product_image) {
        this.image = product_image;
    }

    private String product_id;
    private String name;
    private String price;
    private String image;



//    @Override
//    public String toString() {
//        return "tbl_product [product_id = "+product_id+", product_name = "+product_name+"," +
//                " product_price = "+product_price+",  product_image = "+product_image+"]";

}