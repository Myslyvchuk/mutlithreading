package com.myslyv4uk.threads.synchronization;

import java.util.Scanner;

class Processor extends Thread {
	// should be volatile as could be cached and val could be ignored by thread
	private volatile boolean running = true;
	
	@Override
	public void run() {
		while (running) {
			System.out.println("Hello");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown() {
		this.running = false;
	}
}

public class VolatileApp {
	public static void main(String[] args) {
		Processor processor1 = new Processor();
		processor1.start();
		
		System.out.println("Press return key to stop...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		processor1.shutdown();
	}
}
