package com.shankha.tsfbankingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerCustomerAdapter extends RecyclerView.Adapter<RecyclerCustomerAdapter.ViewHolder> {

    Context context;
    ArrayList<Model> arrCustomer;

    private OnItemClickListener itemClickListener;

    public RecyclerCustomerAdapter(Context context, ArrayList<Model> arrCustomer,OnItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener=itemClickListener;
        this.arrCustomer = arrCustomer;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int a=arrCustomer.get(position).getId();
        int b=arrCustomer.get(position).getBallance();
        holder.id.setText(Integer.toString(a));
        holder.name.setText(arrCustomer.get(position).getName());
        holder.email.setText(arrCustomer.get(position).getEmail());
        holder.ammount.setText(Integer.toString(b));
        holder.llout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null){
                    itemClickListener.onItemClick(position);
                }
            }
        });

    }


    @Override

    public int getItemCount() {
        return arrCustomer.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id,name,email,ammount;
        LinearLayout llout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.transferName);
            id = itemView.findViewById(R.id.txtvid);
            email = itemView.findViewById(R.id.txtvemail);
            ammount = itemView.findViewById(R.id.txtvammount);
            llout=itemView.findViewById(R.id.llout);

        }
    }
}



