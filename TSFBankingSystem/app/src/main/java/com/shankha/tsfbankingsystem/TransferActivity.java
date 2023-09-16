package com.shankha.tsfbankingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TransferActivity extends AppCompatActivity {

    TextView name, email, ammount;
    Spinner pay_email;
    EditText Pay_ammount;
    AutoCompleteTextView pay_name;
    Button btnTransfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        name = findViewById(R.id.transferName);
        email = findViewById(R.id.TransferEmail);
        ammount = findViewById(R.id.TransferBallance);
        pay_name = findViewById(R.id.autotxtPyee);
        pay_email = findViewById(R.id.txtVPayeEmail);
        Pay_ammount = findViewById(R.id.edTTransfer);
        btnTransfer = findViewById(R.id.btn);

        Intent getIntent = getIntent();

        int id = getIntent.getIntExtra("customerId", -1);
        name.setText(getIntent.getStringExtra("customerName"));
        email.setText(getIntent.getStringExtra("customerEmail"));
        int balance = getIntent.getIntExtra("customerBallance", -1);
        ammount.setText(String.valueOf(balance));


        MyDbHelper dbHelper = new MyDbHelper(this);


        int specificIdToExclude = id;

        List<String> names = dbHelper.getAllNamesExceptId(specificIdToExclude);

        ArrayAdapter<String> nameS = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        pay_name.setThreshold(1);
        pay_name.setAdapter(nameS);

        final String[] Reciver = new String[1];

        pay_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                Reciver[0] =selectedName;
                List<String> emailIds = dbHelper.getEmailIdsByName(selectedName);

                ArrayAdapter<String> emailAdapter = new ArrayAdapter<>(TransferActivity.this, android.R.layout.simple_spinner_item, emailIds);
                emailAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                pay_email.setAdapter(emailAdapter);
            }
        });

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

       String Sender= name.getText().toString();



        HistoryDbHelper historyDbHelper=new HistoryDbHelper(this);
        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String selectedEmail = pay_email.getSelectedItem().toString();
                    String transferAmountStr = Pay_ammount.getText().toString();

                    if (selectedEmail.isEmpty() ) {
                        Toast.makeText(TransferActivity.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (transferAmountStr.isEmpty()) {
                        Toast.makeText(TransferActivity.this, "Transfer amount is empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int transferAmount = Integer.parseInt(transferAmountStr);
                    if (transferAmount <= 0) {
                        Toast.makeText(TransferActivity.this, "Transfer amount must be greater than 0", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int senderId = getIntent().getIntExtra("customerId", -1);
                    int senderBalance = dbHelper.getCustomerBalance(senderId);
                    if (senderBalance >= transferAmount) {
                        int receiverId = dbHelper.getIdByEmail(selectedEmail);
                        if (receiverId != -1) {
                            int newSenderBalance = senderBalance - transferAmount;
                            dbHelper.updateBalance(senderId, newSenderBalance);
                            int receiverBalance = dbHelper.getCustomerBalance(receiverId);
                            int newReceiverBalance = receiverBalance + transferAmount;
                            dbHelper.updateBalance(receiverId, newReceiverBalance);
                            Toast.makeText(TransferActivity.this, "Transfer successful!", Toast.LENGTH_SHORT).show();
                           historyDbHelper.addHistory(Sender, Reciver[0],transferAmount);
                            Intent inext = new Intent(TransferActivity.this, MainActivity.class);
                            startActivity(inext);
                        } else {
                            Toast.makeText(TransferActivity.this, "Receiver not found!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(TransferActivity.this, "Insufficient balance!", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(TransferActivity.this, "Invalid transfer amount", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(TransferActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });








    }
}
