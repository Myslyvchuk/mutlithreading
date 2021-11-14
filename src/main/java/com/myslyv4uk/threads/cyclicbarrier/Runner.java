package com.myslyv4uk.threads.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class Runner {
	
	public static void main(String[] args) {
		
		System.out.println(Thread.currentThread().getName() + " has started");
		
		//Number of threads to reach cyclicbarrier and be processed all together
		CyclicBarrier barrier = new CyclicBarrier(4);
		
		//the longest thread sleep is 4000 ms. the fastest 4 threads will be served first (t1, t2, t5, t6)
		//as they have the lowest sleep and cyclic barrier has only 4 thread limit
		ThreadRunner t1 = new ThreadRunner(1000, barrier, "t1-sleep-1000");
		ThreadRunner t2 = new ThreadRunner(2000, barrier, "t2-sleep-2000");
		ThreadRunner t3 = new ThreadRunner(3000, barrier, "t3-sleep-3000");
		ThreadRunner t4 = new ThreadRunner(4000, barrier, "t4-sleep-4000");
		
		ThreadRunner t5 = new ThreadRunner(1000, barrier, "t5-sleep-1000");
		ThreadRunner t6 = new ThreadRunner(2000, barrier, "t6-sleep-2000");
		ThreadRunner t7 = new ThreadRunner(3000, barrier, "t7-sleep-3000");
		ThreadRunner t8 = new ThreadRunner(4000, barrier, "t8-sleep-4000");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
			t7.join();
			t8.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println(Thread.currentThread().getName() + " has finished");
	}
}
