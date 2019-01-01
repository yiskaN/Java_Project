package com.example.sarah.javaproject.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sarah.javaproject.model.backEnd.Backend;
import com.example.sarah.javaproject.model.backEnd.BackendFactory;
import com.example.sarah.javaproject.model.enteties.Trip;
import com.example.sarah.javaproject.R;

public class RideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride);
        Button buttonOK=(Button)findViewById(R.id.button2);
        buttonOK.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        //uploading the trip details from the UI
        SharedPreferences prefs = getSharedPreferences("GeTaxiTemp",MODE_PRIVATE);

        final EditText name =(EditText)findViewById(R.id.nameText);
        final EditText phone =(EditText)findViewById(R.id.PhoneText);
        final EditText email =(EditText)findViewById(R.id.EMailText);
        final EditText begin =(EditText)findViewById(R.id.beginTripText);
        final EditText end = (EditText)findViewById(R.id.endTripText);
        name.setText(prefs.getString( "nameVal",""));
        phone.setText(prefs.getString( "phoneVal",""));
        email.setText(prefs.getString( "emailVal",""));
        begin.setText(prefs.getString( "sourceVal",""));
        end.setText(prefs.getString( "destVal",""));

        buttonOK.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try {

                        BackendFactory.mode = "fb";
                        final Trip trip = new Trip(begin.getText().toString(), end.getText().toString(), name.getText().toString(), phone.getText().toString(), email.getText().toString());
                        final Backend instance = BackendFactory.getInstance(getApplicationContext());//get the instance of Backend
                        new AsyncTask<Context,Void,Void>(){//open "thread" to add data to database
                            /**
                             * this function start the thread action
                             * @return void
                             * @param contexts the context of the activity
                             */
                            @Override
                            protected Void doInBackground(Context... contexts)
                            {
                                try {
                                    instance.addTrip(trip);//add to database
                                    return null;
                                }
                                catch(Exception e)
                                {
                                    Toast.makeText(RideActivity.this, e.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                    return null;
                                }
                            }

                        }.execute();//run thread
                        name.setText("");//clean input EditText
                        phone.setText("");//clean input EditText
                        email.setText("");//clean input EditText
                        begin.setText("");//clean input EditText
                        end.setText("");//clean input EditText
                        Toast.makeText(RideActivity.this, "Your order is being processed",
                                Toast.LENGTH_LONG).show();//message to user

                }
                catch(Exception e)
                {
                    System.out.print(e.getMessage());
                }
            }
        });
    }



}
