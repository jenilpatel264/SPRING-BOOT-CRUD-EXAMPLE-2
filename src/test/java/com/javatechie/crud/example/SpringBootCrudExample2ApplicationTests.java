package com.javatechie.crud.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.repository.ProductRepository;
import com.javatechie.crud.example.service.ProductService;
import java.util.*;  
@SpringBootTest

class SpringBootCrudExample2ApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@MockBean
	private ProductRepository productRepository;
	
	@Autowired
	public ProductService productService;
	
	@Nested
	@DisplayName("ADDITION")
	class additionDisplay
	{
		

	

		@Test
		@DisplayName("save data")
		void addProduct()
		{
			Product product=new Product(15, "cell phone",10, 200000);
			when(productRepository.save(product)).thenReturn(product);
		
			Product actual= productService.saveProduct(product);
			
			System.out.println(actual);
			assertThat(product).isEqualTo(actual);
			
		}
	}
	
	@Nested
	@DisplayName("List All")
	class AllDisplay
	{
		

		@Test
		void ListProduct()
		{
			List<Product> list=new ArrayList<>();
			list.add(new Product());
			list.add(new Product());
			Mockito.when(productRepository.findAll()).thenReturn(list);
			
			List<Product> actual=productService.getProducts();
			
			assertThat(list.size()).isEqualTo(actual.size());
			
		}
	}
	
	
	@Test
	void ListProductById()
	{
		Product product=new Product(2, "cell phone",10, 200000);
		Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
		
		Product actual=this.productService.getProductById(2);
		System.out.println(actual);
		assertThat(product).isEqualTo(actual);
		
	}
	
	@Test
	void updateProduct()
	{
		Product product=new Product(2, "cell phone",10, 200000);
		Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
		
		Product newProduct=new Product();
		newProduct.setId(2);
		
		
		newProduct.setQuantity(100);
		
		Mockito.when(productRepository.save(newProduct)).thenReturn(newProduct);
		System.out.println("newproduct "+newProduct);
		Product actual=this.productService.updateProduct(newProduct);
		System.out.println(actual);
		
		assertThat(newProduct).isEqualTo(actual);
		
	}
	
	@Test
	void deleteProductById()
	{
		Product product=new Product(2, "cell phone",10, 200000);
		Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
		
		String actual=this.productService.deleteProduct(2);
		System.out.println(actual);
		String expect="product removed !! 2";
		assertEquals(expect, actual);;
		verify(productRepository, times(1)).deleteById(2);
		
	}

}
