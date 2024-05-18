package com.example.pmd_se_a_java.ClassTask7;

import android.content.Context; // Import correct Context class
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pmd_se_a_java.R;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<product> productList;
    private Context context;
    private List<product> productListFiltered;



    public ProductAdapter(List<product> productList, Context context) {
        this.productList = productList;
        this.context = context;
        this.productListFiltered = new ArrayList<>(productList);

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        product product = productListFiltered.get(position);
        holder.nameTextView.setText(product.getName());
        holder.companyTextView.setText(product.getCompany());
        holder.priceTextView.setText(product.getPrice());

        Glide.with(context)
                .load(product.getImageUrl())
                .into(holder.imageView);
    }
    public void filter(String query) {
        productListFiltered.clear();
        if (TextUtils.isEmpty(query)) {
            productListFiltered.addAll(productList);
        } else {
            String filterPattern = query.toLowerCase().trim();
            for (product Product : productList) {
                if (Product.getName().toLowerCase().contains(filterPattern)) {
                    productListFiltered.add(Product);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productListFiltered.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, companyTextView, priceTextView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            companyTextView = itemView.findViewById(R.id.companyTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
