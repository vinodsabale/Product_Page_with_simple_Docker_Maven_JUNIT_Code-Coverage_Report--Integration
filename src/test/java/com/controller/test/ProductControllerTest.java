package com.controller.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.controller.ProductController;
import com.entity.Product;
import com.service.ProductService;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void testShowForm() throws Exception {
        mockMvc.perform(get("/products/add"))
               .andExpect(status().isOk())
               .andExpect(view().name("add-product"))
               .andExpect(model().attributeExists("product"));
    }

    @Test
    void testSaveProduct() throws Exception {
        Product product = new Product(1L, "Laptop", "Gaming laptop", 1200.0, 10);

        mockMvc.perform(post("/products/save")
                .flashAttr("product", product))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/products/list"));

        verify(productService, times(1)).saveProduct(product);
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(get("/products/delete/1"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/products/list"));

        verify(productService, times(1)).deleteProduct(1L);
    }

    @Test
    void testListProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(
            Arrays.asList(new Product(1L, "Laptop", "Gaming laptop", 1200.0, 10))
        );

        mockMvc.perform(get("/products/list"))
               .andExpect(status().isOk())
               .andExpect(model().attributeExists("products"))
               .andExpect(view().name("product-list"));

        verify(productService, times(1)).getAllProducts();
    }
}
