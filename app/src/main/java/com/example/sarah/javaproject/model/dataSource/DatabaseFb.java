package com.example.sarah.javaproject.model.dataSource;
import com.example.sarah.javaproject.model.backEnd.Backend;
import com.example.sarah.javaproject.model.enteties.Trip;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class DatabaseFb implements Backend{//this class implement Backend. save the data in FireBase.
    static public long rideCounter=0;
    FirebaseDatabase database=FirebaseDatabase.getInstance();//the instance of the firebase
    DatabaseReference myRef=database.getReference("Trip");//the root
    /**
     * this function add a ride to the FireBase DataBase
     * @return void
     * @param trip the ride to save
     */
    @Override
    public void addTrip(Trip trip) throws Exception {
        trip.setId(rideCounter);
        String key= Long.toString((rideCounter++));
        myRef.child(key).setValue(trip);
    }
}