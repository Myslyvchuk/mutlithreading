package com.myslyv4uk.threads.synchronization;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterSynchronization {
	
	private AtomicInteger atomicCount = new AtomicInteger();
	//Not thread safe
	private int count = 0;
	
	public static void main(String[] args) {
		CounterSynchronization app = new CounterSynchronization();
		app.doWork();
	}
	
	//Blocking solution
	private synchronized void increment() {
		count++;
	}
	
	public void doWork() {
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				//Not thread safe as not atomic operation
				//count++
				//atomicCount.incrementAndGet();
				increment();
			}
		});
		
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				//Not thread safe as not atomic operation
				//count++
				atomicCount.incrementAndGet();
				//increment();
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			//main thread will wait while t1 and t2 finish execution
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Count is: " +
						//count
						atomicCount.get()
		);
	}
}
