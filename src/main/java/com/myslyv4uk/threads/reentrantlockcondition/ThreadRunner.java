package com.myslyv4uk.threads.reentrantlockcondition;

public class ThreadRunner {
	
	public static void main(String[] args) throws InterruptedException {
		
		Runner runner = new Runner();
		
		Thread thread1 = new Thread(() -> {
			try {
				runner.firstThread();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		Thread thread2 = new Thread(() -> {
			try {
				runner.secondThread();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread1.start();
		thread2.start();
		
		thread1.join();
		thread2.join();
		
		runner.finished();
		//will be executed in the beginning if join is commented
		System.out.println("main thread finished");
	}
}