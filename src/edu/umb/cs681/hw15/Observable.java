package edu.umb.cs681.hw15;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable<StockEvent> {
    private LinkedList<Observer<StockEvent>> observers = new LinkedList<>();
    private ReentrantLock lockObs = new ReentrantLock();

    public void addObserver(Observer<StockEvent> o) {
        lockObs.lock();
        try {
            observers.add(o);
        }finally{
            lockObs.unlock();
        }
    }

    public void removeObserver(Observer<StockEvent> o) {
        lockObs.lock();
        try {
            observers.remove(o);
        }finally{
            lockObs.unlock();
        }
    }

    public int countObservers() {
        lockObs.lock();
        try {
            return observers.size();
        }finally{
            lockObs.unlock();
        }
    }

    public List<Observer<StockEvent>> getObservers() {
        lockObs.lock();
        try {
            return observers;
        }finally{
            lockObs.unlock();
        }
    }

    public void clearObservers() {
        lockObs.lock();
        try {
            observers.clear();
        }finally{
            lockObs.unlock();
        }
    }

    public void notifyObservers(StockEvent event) {
        LinkedList<Observer<StockEvent>> observersLocal;

        lockObs.lock();
        try {
            observersLocal = new LinkedList<>(observers); 
        }finally{
            lockObs.unlock();
        }

        observersLocal.forEach(
                (observer) -> {
                    observer.update(this, event);});
    }
}
