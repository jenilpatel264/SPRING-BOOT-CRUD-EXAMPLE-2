package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.entity.MoviesResponse;
import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.entity.addVO;
import com.javatechie.crud.example.entity.addressVO;
import com.javatechie.crud.example.entity.results;
import com.javatechie.crud.example.entity.studentVO;
import com.javatechie.crud.example.repository.ProductRepository;
import com.javatechie.crud.example.repository.addressDAO;
import com.javatechie.crud.example.repository.moviesDAO;
import com.javatechie.crud.example.repository.studentDAO;
import com.javatechie.crud.example.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;

	@Autowired
	private moviesDAO moviesDAO;

	@Autowired
	private studentDAO studentDAO;

	@Autowired
	private addressDAO addressDAO;

	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product) {
		return service.saveProduct(product);
	}

	@PostMapping("/addProducts")
	public List<Product> addProducts(@RequestBody List<Product> products) {
		return service.saveProducts(products);
	}

	@GetMapping("/products")
	public List<Product> findAllProducts() {
		return service.getProducts();
	}

	@GetMapping("/productById/{id}")
	public Product findProductById(@PathVariable int id) {

		return service.getProductById(id);
	}

	@GetMapping("/product/{name}")
	public Product findProductByName(@PathVariable String name) {
		return service.getProductByName(name);
	}

	@PutMapping("/update")
	public Product updateProduct(@RequestBody Product product) {
		return service.updateProduct(product);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);
	}

	@GetMapping("/")
	public String greeting() {

		String uri = "https://ott-details.p.rapidapi.com/advancedsearch";
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("X-RapidAPI-Key", "1a50e8da05msh37ee4cebf7262d8p11bc2djsn68a36cce3556");
		httpHeaders.set("X-RapidAPI-Host", "ott-details.p.rapidapi.com");

		HttpEntity<com.javatechie.crud.example.entity.MoviesResponse> request = new HttpEntity<>(httpHeaders);
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("hello");
		ResponseEntity<com.javatechie.crud.example.entity.MoviesResponse> responseEntity = restTemplate.exchange(uri,
				HttpMethod.GET, request, com.javatechie.crud.example.entity.MoviesResponse.class);
		MoviesResponse ans = responseEntity.getBody();
		System.out.println(ans.getResults().size());

		for (results r : ans.getResults()) {
			this.moviesDAO.save(r);
		}

//		for (MoviesResponse moviesResponse : ans) {
//			System.out.println(moviesResponse.getPage());
//		}

		return "";

//		RestTemplate restTemplate=new RestTemplate();
//		
//		ResponseEntity<PostOfficeResponseBean[]> responseEntity=
//		restTemplate.getForEntity(uri, PostOfficeResponseBean[].class);
//		
//		PostOfficeResponseBean[] officeResponseBeans=responseEntity.getBody();
//		
//		for (PostOfficeResponseBean postOfficeResponseBean : officeResponseBeans) {
//			List<PostOfficeDetailBean> detailBeans=postOfficeResponseBean.getPostOffice();
//			for (PostOfficeDetailBean re : detailBeans) {
//				
//				this.responseDAO.save(re);
//			}
//		}
//	return officeResponseBeans[0];

//		HttpHeaders httpHeaders=new HttpHeaders();
//		httpHeaders.set("X-RapidAPI-Key", "1a50e8da05msh37ee4cebf7262d8p11bc2djsn68a36cce3556");
//		httpHeaders.set("X-RapidAPI-Host", "imdb-top-100-movies.p.rapidapi.com");
//		
//		HttpEntity<requestVO> request=new HttpEntity<>(httpHeaders);

//		ResponseEntity<requestVO> responseEntity=restTemplate.exchange(url,HttpMethod.GET, request,requestVO.class,1);
//		
//		System.out.println(responseEntity.getBody());
//		
//	String json=responseEntity.getBody().getTitle();
//		return new  ResponseEntity<String>(json,HttpStatus.ACCEPTED);
	}

	@PostMapping("/student")
	public ResponseEntity<List<studentVO>> addProduct(@RequestBody List<studentVO> studentVO) {
		List<studentVO> studentVO2 = this.studentDAO.saveAll(studentVO);

		return new ResponseEntity<List<studentVO>>(studentVO2, HttpStatus.CREATED);
	}

	@GetMapping("/student/{id}")
	public ResponseEntity<List<addVO>> addProduct(@PathVariable String id) {
		
		//List<Object[]> ls=this.addressDAO.allList();
		List<addVO> ls2=this.addressDAO.allList();
		return new ResponseEntity<List<addVO>>(ls2,HttpStatus.OK);
	}

}
