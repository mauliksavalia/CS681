HomeWork 13:

Seat Booking Reservation System

This application is a Ticket Reservation System designed for managing seat reservations. 
It demonstrates handling race conditions in a multi-threaded environment.


Original Version: SimpleSeatBookingSystem.java

The original version allows multiple threads to access and modify the seat reservation state concurrently. 
This can lead to a race condition where two threads might reserve or release the same seat simultaneously, leading to inconsistent reservation states.


Revised Version: SafeSeatBookingSystem.java

The use of ReentrantLock allows explicit locking and unlocking of resources, ensuring that only one thread can access the seat reservation data at a time. 
This mechanism effectively prevents the race conditions experienced in the original version.