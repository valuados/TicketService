package com.valuados.tickets.entities;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    List<Session> schedule;

    public Schedule() {
        schedule= new ArrayList<>();
    }

    public List<Session> getShedule() {
        return schedule;
    }

    public void setShedule(List<Session> schedule) {
        this.schedule = schedule;
    }

    public void setStatusBusy(Booking booking){
        for (int i=0; i< schedule.size();i++){
            if(booking.getSessionId()==schedule.get(i).getId()){
                schedule.get(i).setStatusBusy(booking);
            }
        }
    }

    public void seeTimetable(){

        StringBuilder sentence= new StringBuilder();
        sentence.append("------------------------------------------------------------------");
        for(int i =0; i< schedule.size();i++){
            Session session= schedule.get(i);
            sentence.append("The movie: \""+session.getName()+"\"\n");
            sentence.append("Session time: "+ session.getTime()+".\n\n\n");

        }
    }

    public Session getSessionByDate(String date){
        Session session;
        for (int i=0;i<schedule.size();i++){
            session= schedule.get(i);
            if(session.getTime().equals(date)){
                return session;
            }
        }
        session= new Session(-1,"","");
        return session;
    }

    public Session getSessionById(Integer sessionId){
        Session session;
        for (int i=0;i<schedule.size();i++){
            session= schedule.get(i);
            if(session.getId()==sessionId){
                return session;
            }
        }
        session= new Session(-1,"","");
        return session;
    }

    public void cancelReservedPlaces(int sessionId, ArrayList<Place> places){
        int i=0;
        while(schedule.get(i).getId()!=sessionId && i<schedule.size()){
            i++;
        }
        schedule.get(i).cancelReservedPlaces(places);
    }
}
