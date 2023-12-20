package edu.umb.cs681.hw13;

public class SimpleSeatBookingSystem {
    private boolean[] bookedSeats;

    public SimpleSeatBookingSystem(int totalSeats) {
        bookedSeats = new boolean[totalSeats];
    }

    public String seatBooking(int seatNumber) {
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
    }

    public static void main(String[] args) {

        System.out.println("Thread Unsafe:");
        SimpleSeatBookingSystem simpleSystem = new SimpleSeatBookingSystem(10);
        Runnable task = () -> {
            String answer = simpleSystem.seatBooking(5);
            System.out.println(Thread.currentThread().getName() + ": " + answer);
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
