package com.myslyv4uk.threads.waitnotify;

import java.util.Scanner;

public class Handler {
	
	public void producer() throws InterruptedException {
		Thread.sleep(1000);
		synchronized (this) {
			System.out.println("Producer started ######...");
			wait();
			System.out.println("Proceed running in current thread after notification and 5s sleep in consumer tread");
		}
	}
	
	public void consumer() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		synchronized (this) {
			System.out.println("Consumer started #####...");
			System.out.println("Waiting for return key...");
			scanner.nextLine();
			System.out.println("Return key pressed...");
			notify();
			Thread.sleep(5000);
		}
		
	}
}
