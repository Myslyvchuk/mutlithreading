package com.myslyv4uk.threads.waitnotifylowlevelsync;

public class ThreadRunner {
	
	public static void main(String[] args) throws InterruptedException {
		
		Processor handler = new Processor();
		
		Thread thread1 = new Thread(() -> {
			try {
				handler.producer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		Thread thread2 = new Thread(() -> {
			try {
				handler.consumer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread1.start();
		thread2.start();
		
		thread1.join();
		thread2.join();
		//will be executed in the beginning if join is commented
		System.out.println("main thread finished");
	}
}