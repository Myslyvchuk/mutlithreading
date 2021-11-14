package com.myslyv4uk.threads.forkjoinpoolframework;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Task extends RecursiveTask {
	
	private List<Product> products;
	
	private int first;
	private int last;
	
	private double increment;
	
	public Task(List<Product> products, int first, int last, double incerment) {
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = incerment;
	}
	
	@Override
	protected Object compute() {
		if (last - first < 10) {
			updatePrices();
		} else {
			int middle = (last + first) / 2;
			System.out.printf("Task: Pending tasks: %s\n", getQueuedTaskCount());
			Task t1 = new Task(products, first, middle + 1, increment);
			Task t2 = new Task(products, middle + 1, last, increment);
			invokeAll(t1, t2);
		}
		return null;
	}
	
	
	private void updatePrices() {
		for (int i = first; i < last; i++) {
			Product product = products.get(i);
			product.setPrice(product.getPrice() * (1 + increment));
		}
	}
}
