package com.valuados.tickets;

import com.valuados.tickets.entities.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String INPUTFILENAME = "input.txt";

    public static void main( String[] args )

    {
        Schedule schedule = new Schedule();
        BookingList bookingList= new BookingList();
        readFile(INPUTFILENAME, schedule, bookingList);
        System.out.println("Welcome to Ticket Booking System");
        System.out.println("To see all commands type help");
        while(true){
            doCommand(schedule, bookingList);
        }
    }
    public static void readFile(String filename, Schedule schedule, BookingList bookingList){
        try (Scanner sc = new Scanner(new File(INPUTFILENAME))) {

            String buf = new String();
            if(sc.hasNextLine()){
                if(sc.nextLine().equals("Sessions")){
                    schedule = new Schedule();
                    ArrayList<Session> sessionList = new ArrayList<>();
                    while (!sc.nextLine().equals("Booking")){
                        int id = sc.nextInt();
                        String time = sc.nextLine();
                        String name = sc.nextLine();
                        sessionList.add(new Session(id,time,name));
                    }
                    schedule.setShedule(sessionList);
                }
                ArrayList<Booking> bookings= new ArrayList<>();
                while(sc.hasNext()){
                    sc.nextLine();
                    int id=sc.nextInt();
                    sc.nextLine();
                    int sessionId = sc.nextInt();
                    ArrayList<Place> places = new ArrayList<>();
                    while(!sc.nextLine().equals("id") || sc.hasNext()){
                        Place place = new Place(sc.nextInt(),sc.nextInt());
                        places.add(place);
                    }
                    Booking booking = new Booking(id,sessionId,places);
                    bookings.add(booking);

                    schedule.setStatusBusy(booking);

                }
                bookingList.setBookings(bookings);
            }

            sc.close();
        } catch (IOException e) {
            System.out.println("Error while reading file.");
            e.printStackTrace();

        }
    }

    public static void doCommand (Schedule schedule, BookingList bookingList){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the command: ");
        String command = in.nextLine();

        switch (command){
            case "help":

                System.out.println("To exit from the app type: exit");
                System.out.println("To see timetable type: see");
                System.out.println("To reserve places type: res");
                System.out.println("To to cancel reservation type: unres");
                System.out.println("To get information by your reservation number type: info");


                break;
            case "see":
                schedule.seeTimetable();
                break;
            case "res":
                reservePlaces(schedule, bookingList);
                break;
            case "unres":
                removeReservation(schedule, bookingList);
                break;
            case "info":
                infoByReservation(schedule, bookingList);
                break;
            case "exit":
                System.exit(0);
                break;

            default:
                System.out.println("Wrong command!\n");
                break;
        }

    }

    public static void reservePlaces(Schedule schedule, BookingList bookingList) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the date, you wish to do to session(dd-mm-yyyy):");
        StringBuilder date =new StringBuilder();
        date.append(in.nextLine()+" ");
        System.out.println("Enter the time(mm:hh):");
        date.append(in.nextLine());
        Session session = schedule.getSessionByDate(date.toString());
        if(session.getId()==-1){
            System.out.println("Incorrect date. Type res again to reserve seats.");
        }
        else
        {
            System.out.println(session.toString());
            System.out.println("By [0] marked free spaces.");
            System.out.println("Enter the number of tickets, you wish to by:");
            ArrayList<Place> placesToReserve= new ArrayList<>();
            if(in.hasNextInt()){
                int n= in.nextInt();
                for (int i=0; i<n;i++){
                    System.out.println("Enter row:");
                    int row= in.nextInt();
                    System.out.println("Enter seat:");
                    int seat= in.nextInt();
                    if(session.getPlace(row, seat)==0){
                        placesToReserve.add(new Place(row,seat));
                    }
                    else{
                        System.out.println("This seat is busy, choose another seat");
                        i--;
                    }
                }
                int id = bookingList.getBooking(bookingList.getBookingListSize()-1).getId();
                Booking currentBooking= new Booking(id,session.getId(),placesToReserve);
                bookingList.addBooking(currentBooking);
                schedule.setStatusBusy(currentBooking);
                System.out.println(currentBooking.toString(session.getName(),session.getTime()));

            }

            else {
                System.out.println("Incorrect number type. Type res again to reserve seats.");
            }
        }

    }
    public static void removeReservation(Schedule schedule, BookingList bookingList){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the id of reservation, you wish to remove:");
        if(in.hasNextInt()){
            int id = in.nextInt();
            Booking bufBooking;
            bufBooking= bookingList.findByIdAndRemove(id);
            if(bufBooking.getId()!=-1){
                schedule.cancelReservedPlaces(bufBooking.getSessionId(), bufBooking.getPlaces());
                System.out.println("Reservation is removed.");
            }
            else{
                System.out.println("Incorrect id. Try this command again.");

            }
        }
        else{
            System.out.println("Incorrect id. Try this command again.");
        }
    }
    public static void infoByReservation(Schedule schedule, BookingList bookingList){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the id of reservation, you wish to see information:");
        if(in.hasNextInt()){
            int id = in.nextInt();
            Booking bufBooking;
            bufBooking= bookingList.findById(id);
            Session session=schedule.getSessionById(bufBooking.getSessionId());
            if(bufBooking.getId()!=-1){
                System.out.println(bufBooking.toString(session.getName(),session.getTime()));
            }
            else{
                System.out.println("Incorrect id. Try this command again.");

            }
        }
        else{
            System.out.println("Incorrect id. Try this command again.");
        }
    }
}
