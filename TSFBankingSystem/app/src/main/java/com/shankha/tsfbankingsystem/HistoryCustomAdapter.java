package com.shankha.tsfbankingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryCustomAdapter extends RecyclerView.Adapter<HistoryCustomAdapter.ViewHolder> {

    Context context;

    public HistoryCustomAdapter(Context context, ArrayList<Modelhistory> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    ArrayList<Modelhistory> historyList;
    @NonNull
    @Override
    public HistoryCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.historylist,parent,false);
       ViewHolder viewHolder=new ViewHolder(view);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryCustomAdapter.ViewHolder holder, int position) {
            holder.s_name.setText(historyList.get(position).getS_name());
            holder.r_name.setText(historyList.get(position).getR_name());
            int a=historyList.get(position).getId();
            int b=historyList.get(position).getT_amount();
            holder.id.setText(Integer.toString(a));
            holder.t_amount.setText(Integer.toString(b));
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id, s_name,r_name,t_amount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.historyid);
            s_name=itemView.findViewById(R.id.senderName);
            r_name=itemView.findViewById(R.id.reciverName);
            t_amount=itemView.findViewById(R.id.transferammount);

        }
    }
}
