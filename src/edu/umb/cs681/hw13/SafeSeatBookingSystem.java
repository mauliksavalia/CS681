package edu.umb.cs681.hw13;

import java.util.concurrent.locks.ReentrantLock;

public class SafeSeatBookingSystem {
    private final boolean[] bookedSeats;
    private final ReentrantLock lock = new ReentrantLock();

    public SafeSeatBookingSystem(int totalSeats) {
        bookedSeats = new boolean[totalSeats];
    }

    public String seatBooking(int seatNumber) {
        lock.lock();
        try {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!bookedSeats[seatNumber]) {
                bookedSeats[seatNumber] = true;
                return "Seat " + seatNumber + " reserved successfully.";
            } else {
                return "Seat is already reserved.";
            }
        }finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        System.out.println("\nThread Safe:");
        SafeSeatBookingSystem safeSystem = new SafeSeatBookingSystem(10);

        SafeSeatBookingSystemRunnable runnable1 = new SafeSeatBookingSystemRunnable(safeSystem, 5);
        SafeSeatBookingSystemRunnable runnable2 = new SafeSeatBookingSystemRunnable(safeSystem, 5);

        Thread thread1 = new Thread(runnable1, "Thread-1");
        Thread thread2 = new Thread(runnable2, "Thread-2");

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(100); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runnable1.setDone();
        runnable2.setDone();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



