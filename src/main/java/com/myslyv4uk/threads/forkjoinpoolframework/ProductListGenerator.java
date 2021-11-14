package com.myslyv4uk.threads.forkjoinpoolframework;

import java.util.ArrayList;
import java.util.List;

public class ProductListGenerator {
	
	public List<Product> generate(int size) {
		List<Product> result = new ArrayList<>();
		
		for (int i = 0; i < size; i++) {
			result.add(new Product("Product " + i, 10 * i));
		}
		return result;
	}
}
