package com.example.sarah.javaproject.model.dataSource;

import java.util.ArrayList;

import com.example.sarah.javaproject.model.enteties.Trip;
import com.example.sarah.javaproject.model.backEnd.Backend;

public class DatabaseList implements Backend
{
    static public int TripCounter=0;
    ArrayList<Trip> trips = new ArrayList<Trip>();
    @Override
    public void addTrip(Trip trip) throws Exception {

        trip.setId(TripCounter++);
        for (Trip t: trips) {
            if (t.getId()==trip.getId())
                throw new Exception("this trip is already exists!");
        }
        trips.add(trip);
    }
    public void setList() {
        try {
           // this.addTrip(new Trip("MOTION","jerusalem","//",...));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
