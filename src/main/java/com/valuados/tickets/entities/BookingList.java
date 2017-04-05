package com.valuados.tickets.entities;

import java.util.ArrayList;
import java.util.List;

public class BookingList {
    List<Booking> bookings;

    public BookingList() {
        bookings=new ArrayList<>();
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Booking getBooking(int i) {
        return bookings.get(i);
    }

    public Integer getBookingListSize() {
        return bookings.size();
    }
    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    public Booking findByIdAndRemove(int id){
        Booking booking;
        for (int i=0; i< bookings.size(); i++){
            if(bookings.get(i).getId()==id){
                booking= bookings.get(i);
                bookings.remove(i);
                return  booking;
            }
        }
        booking= new Booking(-1,-1,new ArrayList<Place>());
        return booking;
    }
    public Booking findById(int id){
        Booking booking;
        for (int i=0; i< bookings.size(); i++){
            if(bookings.get(i).getId()==id){
                booking= bookings.get(i);
                return  booking;
            }
        }
        booking= new Booking(-1,-1,new ArrayList<Place>());
        return booking;
    }
}
