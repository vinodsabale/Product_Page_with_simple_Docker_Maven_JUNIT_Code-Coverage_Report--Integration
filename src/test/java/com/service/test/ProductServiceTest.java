package com.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.entity.Product;
import com.repository.ProductRepository;
import com.service.ProductService;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    public ProductServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProduct() {
        Product product = new Product(1L, "Laptop", "Gaming laptop", 1200.0, 10);
        productService.saveProduct(product);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testGetAllProducts() {
        Product product1 = new Product(1L, "Laptop", "Gaming laptop", 1200.0, 10);
        Product product2 = new Product(2L, "Phone", "Smartphone", 800.0, 20);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
        assertEquals("Laptop", products.get(0).getName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testDeleteProduct() {
        Long id = 1L;
        productService.deleteProduct(id);
        verify(productRepository, times(1)).deleteById(id);
    }
}
