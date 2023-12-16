package edu.umb.cs681.hw13;

public class SimpleSeatBookingSystem {
    private boolean[] reservedSeats;

    public SimpleSeatBookingSystem(int totalSeats) {
        reservedSeats = new boolean[totalSeats];
    }

    public String reserveSeat(int seatNumber) {
        if (seatNumber < 0 || seatNumber >= reservedSeats.length) {
            return "Invalid seat number.";
        }
    
        try {
            Thread.sleep(100); // 100 milliseconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!reservedSeats[seatNumber]) {
            reservedSeats[seatNumber] = true;
            return "Seat " + seatNumber + " reserved successfully.";
        } else {
            return "Seat is already reserved.";
        }
    }
    
    public String showReservedSeats() {
        StringBuilder sb = new StringBuilder();
        boolean hasReserved = false;
        for (int i = 0; i < reservedSeats.length; i++) {
            if (reservedSeats[i]) {
                if (hasReserved) {
                    sb.append(", ");
                }
                sb.append(i);
                hasReserved = true;
            }
        }
        return hasReserved ? "Reserved seats: " + sb.toString() : "No seats reserved.";
    }

     public static void main(String[] args) {
        
        System.out.println("Thread Unsafe:");
        SimpleSeatBookingSystem system = new SimpleSeatBookingSystem(10);

        Runnable task = () -> {
            String response = system.reserveSeat(5);
            System.out.println(Thread.currentThread().getName() + ": " + response);
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }

        System.out.println(system.showReservedSeats());
    }
    
}
