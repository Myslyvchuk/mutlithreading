package com.myslyv4uk.threads.forkjoinpoolframework;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Runner {
	public static void main(String[] args) {
		ProductListGenerator generator = new ProductListGenerator();
		List<Product> products= generator.generate(10000);
		
		Task task = new Task(products, 0,products.size(), 0.20);
		
		ForkJoinPool pool = new ForkJoinPool();
		
		pool.execute(task);
		
		do {
			System.out.printf("*************************************\n");
			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
			System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
			System.out.printf("Main: Task count: %d\n", pool.getStealCount());
			System.out.printf("**************************************\n");
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());
		pool.shutdown();
		
		//check if the task has completed normally
		if(task.isCompletedNormally()) {
			System.out.printf("Main: The process has completed normally.\n");
		}
		
		for(int i=0; i<products.size();i++){
			Product product=products.get(i);
			if(product.getPrice() != 12) {
				System.out.printf("Product %s: %f\n", product.getName(), product.getPrice());
			}
		}
		
		System.out.println("Main: End of program.");
		
	}
}
