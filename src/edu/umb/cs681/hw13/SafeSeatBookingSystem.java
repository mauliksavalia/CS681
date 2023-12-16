package edu.umb.cs681.hw13;

import java.util.concurrent.locks.ReentrantLock;

public class SafeSeatBookingSystem {
    private final boolean[] reservedSeats;
    private final ReentrantLock lock = new ReentrantLock();

    public SafeSeatBookingSystem(int totalSeats) {
        reservedSeats = new boolean[totalSeats];
    }

    public String reserveSeat(int seatNumber) {
        lock.lock();
        try {
            if (seatNumber < 0 || seatNumber >= reservedSeats.length) {
                return "Invalid seat number.";
            }
            if (!reservedSeats[seatNumber]) {
                reservedSeats[seatNumber] = true;
                return "Seat " + seatNumber + " reserved successfully.";
            } else {
                return "Seat is already reserved.";
            }
        } catch (Exception e) {
            return "Error in reserving seat: " + e.getMessage();
        } finally {
            lock.unlock();
        }
    }

    public String showReservedSeats() {
        lock.lock();
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < reservedSeats.length; i++) {
                if (reservedSeats[i]) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(i);
                }
            }
            return sb.length() > 0 ? "Reserved seats: " + sb.toString() : "No seats reserved.";
        } catch (Exception e) {
            return "Error in showing reserved seats: " + e.getMessage();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        System.out.println("\nThread Safe:");
        SafeSeatBookingSystem safeSystem = new SafeSeatBookingSystem(10);

        // Create a task that attempts to reserve a seat
        Runnable task = () -> {
            String response = safeSystem.reserveSeat(5);
            System.out.println(Thread.currentThread().getName() + ": " + response);
        };

        // Creating two threads that will attempt to reserve the same seat
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

        // Show reserved seats
        System.out.println(safeSystem.showReservedSeats());
    }
}
