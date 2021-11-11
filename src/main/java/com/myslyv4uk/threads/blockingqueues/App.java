package com.myslyv4uk.threads.blockingqueues;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
	
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(App::producer);
		Thread t2 = new Thread(App::consumer);
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
	
	private static void producer()  {
		
		Random random = new Random();
		while (true) {
			try {
				queue.put(random.nextInt(100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void consumer()  {
		Random random = new Random();
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(random.nextInt(10) == 0) {
				Integer take = null;
				try {
					take = queue.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Taken value: " + take + "; Queue size is: " + queue.size());
			}
		}
	}
}
