package com.valuados.tickets.entities;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by valua on 4/5/2017.
 */

public class Session {
    private int id;
    private String time;
    private String name;
    int [][]place;

    public Session() {
        place= new int [15][15];
        Arrays.fill(place, 0);
    }

    public Session(int id, String time, String name) {
        this.id = id;
        this.time = time;
        this.name = name;
        place = new int[15][15];
        Arrays.fill(place, 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[][] getPlace() {
        return place;
    }

    public void setPlace(int[][] place) {
        this.place = place;
    }

    public int getPlace(int i, int j){
        return place[i][j];
    }

    public void setPlace(int i, int j, int param){
        place[i][j]=param;
    }

    public void setStatusBusy(Booking booking){
        for (int i=0;i<booking.getPlaces().size();i++){
            int row=booking.getPlaces().get(i).getRow();
            int seatPlace=booking.getPlaces().get(i).getSeat();
            place[row][seatPlace]=1;

        }
    }
    public void cancelReservedPlaces(ArrayList<Place> places){
        for (int i=0; i<places.size(); i++){
            this.place[places.get(i).getRow()][places.get(i).getSeat()]=0;
        }
    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("------------------------------------------------------------------");
        stringBuilder.append("The movie: \""+this.name+"\"\n");
        stringBuilder.append("Session time: "+ this.time+".\n\n");
        stringBuilder.append("   ");
        for (int i=0; i<10; i++){
            stringBuilder.append("  "+String.valueOf(i+1)+" ");
        }
        for (int i=10; i<15; i++){
            stringBuilder.append("  "+String.valueOf(i+1));
        }
        stringBuilder.append("\n");
        for (int i=0; i<15;i++){
            stringBuilder.append(String.valueOf(i+1)+": ");
            for(int j=0; j<15;j++){
                stringBuilder.append("["+place[i][j]+"] ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
