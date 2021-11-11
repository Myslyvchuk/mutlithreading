package com.myslyv4uk.threads.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
	
	private final Random random = new Random();
	
	private final Object lock1 = new Object();
	private final Object lock2 = new Object();
	
	List<Integer> list1 = new ArrayList<>();
	List<Integer> list2 = new ArrayList<>();
	
	// No need to add synchronized key word.
	// Synchronized - synchronization on instinct lock, synch on object, synch on many not related objects
	//public synchronized void stageOne() {
	public void stageOne() {
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
	}
	
	//public synchronized void stageTwo() {
	public void stageTwo() {
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}
	}
	
	public void process() {
		for (int i = 0; i < 1000; i++) {
			//acquire intrinsic lock which blocks another thread to run method stageTwo as it is synchronized as well
			//solution is not to use synchronized key word, but monitor object
			stageOne();
			stageTwo();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Worker worker = new Worker();
		System.out.println("Starting...");
		long start = System.currentTimeMillis();
		Thread t1 = new Thread(worker::process);
		Thread t2 = new Thread(worker::process);
		
		t1.start();
		t2.start();
		
		
		t1.join();
		t2.join();
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time take: " + (end - start));
		System.out.println("List1: " + worker.list1.size() + "; List2: " + worker.list2.size());
		
	}
}
