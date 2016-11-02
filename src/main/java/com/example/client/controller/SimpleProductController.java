package com.example.client.controller;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.service.SimpleProductService;
import com.example.common.entity.SimpleProduct;

@RequestMapping("/simpleproduct")
@RestController
public class SimpleProductController {

	@Autowired
	SimpleProductService productService;
	
	// create
	@RequestMapping(value = "/create/", method = RequestMethod.PUT)
	public SimpleProduct create(@RequestBody SimpleProduct product) {
		// won't have insertDate created so set that here
		product.setInsertTime(new Date());
		
		long start = System.nanoTime();
		
		SimpleProduct persistedProduct = productService.create(product);
		
		System.out.println(String.format("SimpleProductController#create(product) took: %s millis", (TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start))));
		
		return persistedProduct;
	}

	// read
	@RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
	public SimpleProduct read(@PathVariable("id") Long id) {
		long start = System.nanoTime();
		
		SimpleProduct product = productService.read(id);
		
		System.out.println(String.format("SimpleProductController#read(id) took: %s millis", (TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start))));
		
		return product;
	}
	
	// update
	@RequestMapping(value = "/update/", method = RequestMethod.PUT)
	public SimpleProduct update(@RequestBody SimpleProduct product) {
		long start = System.nanoTime();
		
		SimpleProduct updatedProduct = productService.update(product);
		
		System.out.println(String.format("SimpleProductController#update(product) took: %s millis", (TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start))));
		
		return updatedProduct;
	}
	
	// delete
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
		long start = System.nanoTime();
		
		productService.delete(id);
		
		System.out.println(String.format("SimpleProductController#delete(id) took: %s millis", (TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start))));
	}
	
	// get the map
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public Map<Long, SimpleProduct> map() {
		long start = System.nanoTime();
		
		Map<Long, SimpleProduct> map = productService.getMap();
		
		System.out.println(String.format("SimpleProductController#map() took: %s millis", (TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start))));
		
		return map;
	}
}
