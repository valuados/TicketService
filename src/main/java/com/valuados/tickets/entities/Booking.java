package com.valuados.tickets.entities;

import java.util.ArrayList;
import java.util.List;

public class Booking {
    private int id;
    private int sessionId;
    private ArrayList<Place> places;

    public Booking() {
        places=new ArrayList<Place>();
    }

    public Booking(int id, int sessionId, ArrayList<Place> places) {
        this.id = id;
        this.sessionId = sessionId;
        this.places = places;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }
    public String toString(String name, String date){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("------------------------------------------------------------------");
        stringBuilder.append("Booking id: \""+this.id+"\"\n");
        stringBuilder.append("Movie name: \""+name+"\"\n");
        stringBuilder.append("Session date: "+ date+".\n");
        stringBuilder.append("Bought seats([№row, №seat]):\n");
        for (int i=0; i<places.size();i++){
            stringBuilder.append("["+places.get(i).getRow()+", "+places.get(i).getSeat()+"] ");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
