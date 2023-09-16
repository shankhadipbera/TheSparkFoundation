package com.shankha.tsfbankingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView RecycleHistory;
     ArrayList<Modelhistory> HistoryDetails=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        RecycleHistory=findViewById(R.id.RVHistory);
        RecycleHistory.setLayoutManager(new LinearLayoutManager(this));
        HistoryDbHelper hdb=new HistoryDbHelper(this);
        HistoryDetails = hdb.getHistory();
        HistoryCustomAdapter Hadpt= new HistoryCustomAdapter(this,HistoryDetails);
        RecycleHistory.setAdapter(Hadpt);
    }
}