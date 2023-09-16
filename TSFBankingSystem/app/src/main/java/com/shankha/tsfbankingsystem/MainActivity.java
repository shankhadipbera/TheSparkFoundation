package com.shankha.tsfbankingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    RecyclerView rv;
    TextView history;
    ArrayList<Model> demoCustomer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.recycleView);
        history=findViewById(R.id.history);
        rv.setLayoutManager(new LinearLayoutManager(this));

        MyDbHelper dbHelper = new MyDbHelper(this);
        dbHelper.addCustomer("Shankhadip Bera", "shankha123@gmail.com", 25000);
        dbHelper.addCustomer("Bidyut Mondal", "bidyut123@gmail.com", 35000);
        dbHelper.addCustomer("Pradyut pal", "Pradyut123@gmail.com", 55000);
        dbHelper.addCustomer("Sandip Khanda", "sandip123@gmail.com", 45000);
        dbHelper.addCustomer("Anubrata Basu", "anubrata123@gmail.com", 50000);
        dbHelper.addCustomer("Rudra Mandal", "rudra123@gmail.com", 70000);
        dbHelper.addCustomer("Himadri Mahata", "himadri123@gmail.com", 45000);
        dbHelper.addCustomer("Sourav Bera", "sourav123@gmail.com", 40000);
        dbHelper.addCustomer("Surajit Sasmal", "surajit123@gmail.com", 30000);
        dbHelper.addCustomer("Sayan Hazra", "sayan23@gmail.com", 60000);
        dbHelper.addCustomer("Ayan Samanta", "Ayan123@gmail.com", 25000);
        dbHelper.addCustomer("Subhajit Malik", "subhajit123@gmail.com", 35000);
        dbHelper.addCustomer("Soumyadip Bera", "soumya123@gmail.com", 50000);
        dbHelper.addCustomer("Biswajit Maity", "bisu123@gmail.com", 80000);
        dbHelper.addCustomer("Suman Das", "suman123@gmail.com", 70000);
        dbHelper.addCustomer("Soumyadip Ghara", "soumyadip123@gmail.com", 60000);
        dbHelper.addCustomer("Akash Biswas", "akash123@gmail.com", 90000);
        dbHelper.addCustomer("Suman Pan", "psuman123@gmail.com", 50000);
        dbHelper.addCustomer("Shankha Bera", "Bshankha123@gmail.com", 25000);
        dbHelper.addCustomer("Aditya Kundu", "Aditya123@gmail.com", 35000);
        dbHelper.addCustomer("Satyajit Jana", "satyajit123@gmail.com", 25000);
        dbHelper.addCustomer("Md Hamza", "hamza123@gmail.com", 40000);
        dbHelper.addCustomer("Susobhan Das", "susobhan123@gmail.com", 25000);
        dbHelper.addCustomer("Ali Imran", "imran123@gmail.com", 50000);
        dbHelper.addCustomer("Abhijit Manna", "abhijit123@gmail.com", 40000);
        dbHelper.addCustomer("Ranajit Betal", "ranajit123@gmail.com", 30000);
        dbHelper.addCustomer("Rajesh Sasmal", "shankha123@gmail.com", 50000);
        dbHelper.addCustomer("Malay Bera", "malay123@gmail.com", 65000);
        dbHelper.addCustomer("Shankhadip Bera", "Bshankha123@gmail.com", 25000);


        demoCustomer = dbHelper.getCustomer();
        RecyclerCustomerAdapter adpt = new RecyclerCustomerAdapter(this, demoCustomer, this::onItemClick);

        rv.setAdapter(adpt);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,HistoryActivity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onItemClick(int position) {
        Model clickedCustomer = demoCustomer.get(position);
        Intent intent = new Intent(MainActivity.this, TransferActivity.class);
        intent.putExtra("customerId", clickedCustomer.getId());
        intent.putExtra("customerName", clickedCustomer.getName());
        intent.putExtra("customerEmail", clickedCustomer.getEmail());
        intent.putExtra("customerBallance", clickedCustomer.getBallance());

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}