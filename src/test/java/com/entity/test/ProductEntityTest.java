package com.entity.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.entity.Product;

public class ProductEntityTest {
	

	    @Test
	    void testProductConstructorAndGetters() {
	        Product product = new Product(1L, "Laptop", "Gaming laptop", 1200.0, 10);

	        assertEquals(1L, product.getId());
	        assertEquals("Laptop", product.getName());
	        assertEquals("Gaming laptop", product.getDescription());
	        assertEquals(1200.0, product.getPrice());
	        assertEquals(10, product.getQuantity());
	    }

	    @Test
	    void testSetters() {
	        Product product = new Product();
	        product.setId(2L);
	        product.setName("Phone");
	        product.setDescription("Smartphone");
	        product.setPrice(800.0);
	        product.setQuantity(20);

	        assertEquals(2L, product.getId());
	        assertEquals("Phone", product.getName());
	        assertEquals("Smartphone", product.getDescription());
	        assertEquals(800.0, product.getPrice());
	        assertEquals(20, product.getQuantity());
	    }
	
}
