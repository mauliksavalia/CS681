package edu.umb.cs681.hw13;

public class SafeSeatBookingSystemRunnable implements Runnable {
    private final SafeSeatBookingSystem bookingSystem;
    private volatile boolean done = false;
    private final int seatNumber;

    public SafeSeatBookingSystemRunnable(SafeSeatBookingSystem bookingSystem, int seatNumber) {
        this.bookingSystem = bookingSystem;
        this.seatNumber = seatNumber;
    }

    public void setDone() {
        done = true;
    }

    @Override
    public void run() {
        while(true) {
            if(done) {
                break;
            }
            String answer = bookingSystem.seatBooking(seatNumber);
            System.out.println(Thread.currentThread().getName() + ": " + answer);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break; 
            }
        }
    }
}
