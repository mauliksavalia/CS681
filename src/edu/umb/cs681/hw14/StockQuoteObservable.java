package edu.umb.cs681.hw14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable<StockEvent> {
    
    private Map<String, Double> stockValue = new HashMap<>();
    private ReentrantLock lockTQ = new ReentrantLock();

    public Map<String, Double> getHashmap() {
        lockTQ.lock();
        try {
            return this.stockValue;
        }finally{
            lockTQ.unlock();
        }
    }

    public void changeQuote(String t, double q) {
        lockTQ.lock();
        try {
            stockValue.put(t, q);
        }finally{
            lockTQ.unlock();
        }
        notifyObservers(new StockEvent(t, q));
    }

    public static void main(String[] args) {
        StockQuoteObservable stockObservable = new StockQuoteObservable();

        LineChartObserver lineChartObserver = new LineChartObserver();
        Table3DObserver table3DObserver = new Table3DObserver();
        TableObserver tableObserver = new TableObserver();
        
        stockObservable.addObserver(lineChartObserver);
        stockObservable.addObserver(table3DObserver);
        stockObservable.addObserver(tableObserver);

        String[] stocks = {"Costco", "Walmart", "Target", "Star Market", "Stop&Shop", "Walgreens"};

        Thread[] threads = new Thread[12];

        for (int i = 0; i < 12; i++) {
            threads[i] = new Thread(() -> {
                Random random = new Random();
                String ticker = stocks[random.nextInt(stocks.length)];
                double quote = 100 + random.nextDouble() * 100;
                stockObservable.changeQuote(ticker, quote);
                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stockObservable.removeObserver(lineChartObserver);
        stockObservable.removeObserver(table3DObserver);
        stockObservable.removeObserver(tableObserver);

        System.out.println("\nFinal Stock Values: " + stockObservable.getHashmap());
    }
}

