package com.example.pmd_se_a_java.RestAPI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmd_se_a_java.R;

import java.util.List;

public class MyRestAdapter extends RecyclerView.Adapter<MyRestAdapter.viewholder> {
    public List<MyRestAPIModel> list;
    public Context context;


    public MyRestAdapter(List<MyRestAPIModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.myrestlayout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.Name.setText(list.get(position).getTitle());
        holder.Context.setText(list.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class viewholder extends RecyclerView.ViewHolder {
        TextView Name,Context;

        public viewholder(@NonNull View itemView, TextView name, TextView context) {
            super(itemView);
            Name = name;
            Context = context;
        }

        public viewholder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.myrestnametxt);
            Context=itemView.findViewById(R.id.myrestcontenttxt);
        }

    }
}
