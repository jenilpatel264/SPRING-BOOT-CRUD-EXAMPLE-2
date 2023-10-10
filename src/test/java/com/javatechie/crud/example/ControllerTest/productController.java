package com.javatechie.crud.example.ControllerTest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.repository.ProductRepository;
import com.javatechie.crud.example.service.ProductService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class productController {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	public ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	@Test
	void productById() throws Exception
	{
		
		Product product=new Product(2, "cell phone",10, 200000);
		Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
		
		System.out.println("JENIL");
		mockMvc.perform(get("/productById/2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("cell phone"));
		
	
				
		
	}
	
	@Test
	void deleteById() throws Exception
	{
		

		Product product=new Product(2, "cell phone",10, 200000);
		Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
		
		mockMvc.perform(delete("/delete/2"))
				.andExpect(status().isOk());
				
		
	
				
		
	}
	
	@Test
	void AddProduct() throws Exception
	{
		
		Product product=new Product(15, "cell phone",10, 200000);
		when(productRepository.save(any(Product.class))).thenReturn(product);
		
		mockMvc.perform(post("/addProduct").content("{\r\n"
				+ "    \"id\":1,\r\n"
				+ "    \"name\":\"cell phone\",\r\n"
				+ "    \"quantity\":10,\r\n"
				+ "    \"price\":100000.0\r\n"
				+ "\r\n"
				+ "}").contentType("application/json"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.quantity",is(10)));
				
		
	
				
		
	}

}
