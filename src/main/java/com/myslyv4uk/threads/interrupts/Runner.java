package com.myslyv4uk.threads.interrupts;

import java.util.Random;
import java.util.concurrent.*;

public class Runner {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting...");
		
		Thread t = new Thread(() -> {
			Random random = new Random();
			for (int i = 0; i < 1E8; i++) {
				if (Thread.currentThread().isInterrupted()) {
					System.out.println("Interrupted!");
					break;
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					System.out.println("We've been interrupted");
					break;
				}
				Math.sin(random.nextDouble());
			}
		});
		
		t.start();
		//in 4 ms it happens to pass check for interrupted
		Thread.sleep(4);
		t.interrupt();
		
		t.join();
		
		System.out.println("Finished.");
	}
}

class Handler {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting...");
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<?> future = executorService.submit((Callable<Void>) () -> {
			Random random = new Random();
			for (int i = 0; i < 1E8; i++) {
				if (Thread.currentThread().isInterrupted()) {
					System.out.println("Interrupted!");
					break;
				}
				Math.sin(random.nextDouble());
			}
			return null;
		});
		
		executorService.shutdown();
		
		Thread.sleep(500);
		
		//sets interrupted as in previous example
		//future.cancel(true);
		//executorService.shutdownNow();
		
		executorService.awaitTermination(1, TimeUnit.MINUTES);
		
		System.out.println("Finished.");
	}
}
