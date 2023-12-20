HomeWork 13:

Application: Seat Booking System

This application is a Seat Booking System designed for managing seat reservations. 
It demonstrates handling race conditions in a multi-threaded environment.


Original(Thread Unsafe) Version: SimpleSeatBookingSystem.java
The original version allows multiple threads to access and modify the seat booking state concurrently. 
This can lead to a race condition where two threads might reserve or release the same seat simultaneously, leading to inconsistent booking states.


ThreadSafe Version: SafeSeatBookingSystem.java
I used ReentrantLock which allows locking and unlocking of resources, ensuring that only one thread can access the seat booking data at a time. 
This mechanism effectively prevents the race conditions experienced in the thread Unsafe version. For 2-step Thread Termination, I've used Volatile Flag.