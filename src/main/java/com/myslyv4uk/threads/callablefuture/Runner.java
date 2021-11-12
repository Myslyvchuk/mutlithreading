package com.myslyv4uk.threads.callablefuture;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

public class Runner {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		final Future<Integer> future = executorService.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws IOException {
				Random random = new Random();
				int duration = random.nextInt(4000);
				if(duration > 2000) {
					throw new IOException("Sleeping too long.");
				}
				System.out.println("Starting...");
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Finished.");
				return duration;
			}
		});
		executorService.shutdown();
		
		try {
			System.out.println("Result is: " + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
