package com.example.diu.stopwatch;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SubmitActivity extends Activity {
    DatabaseHelper myDb;
    EditText Name, Contact, Location, Details;

    Button Submit2, View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        myDb = new DatabaseHelper(this);
        Name = (EditText)findViewById(R.id.name);
        Contact = (EditText)findViewById(R.id.contact);
        Location = (EditText)findViewById(R.id.location);
        Details = (EditText)findViewById(R.id.details);
        Submit2 = (Button)findViewById(R.id.submit2);
        View = (Button)findViewById(R.id.btn_view);
        AddData();
    viewAll();}
        public  void AddData() {
            Submit2.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean isInserted = myDb.insertData(Name.getText().toString(),
                                    Contact.getText().toString(),
                                    Location.getText().toString(),Details.getText().toString());
                            if(isInserted == true)
                                Toast.makeText(SubmitActivity.this,"Data Inserted Successfully!",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(SubmitActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                        }
                    }
            );
        }
    public void viewAll() {
        View.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Contact :"+ res.getString(2)+"\n");
                            buffer.append("Location :"+ res.getString(3)+"\n");
                            buffer.append("Details :"+ res.getString(4)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    }

