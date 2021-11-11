package com.myslyv4uk.threads.creation;

class RunnerThread extends java.lang.Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Hello thread " + i);
			try {
				java.lang.Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class RunnerRunnable implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Hello runnable " + i);
			try {
				java.lang.Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class Thread {
	public static void main(String[] args) {
		RunnerThread runnerThread1 = new RunnerThread();
		runnerThread1.start();
		
		RunnerThread runnerThread2 = new RunnerThread();
		runnerThread2.start();
		
		java.lang.Thread t1 = new java.lang.Thread(new RunnerRunnable());
		java.lang.Thread t2 = new java.lang.Thread(new RunnerRunnable());
		
		t1.start();
		t2.start();
		
		java.lang.Thread t3 = new java.lang.Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println("Hello anonymous thread " + i);
				try {
					java.lang.Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t3.start();
		
	}
}
