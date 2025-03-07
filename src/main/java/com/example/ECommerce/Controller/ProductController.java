package com.example.ECommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ECommerce.Model.Product;
import com.example.ECommerce.Service.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
	
	private ProductService prodService;
	
	@Autowired
	public ProductController(ProductService prodService) {
		this.prodService = prodService;
	}
	
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to my website..!";
	}
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return prodService.getAllProducts();
	}
	
	@GetMapping("/product/{prodId}")
	public Product getProductById(@PathVariable int prodId) {
		return prodService.getProductById(prodId);
	}
	
	@DeleteMapping("/product/delete/{prodId}")
	public String deleteProductById(@PathVariable int prodId){
		return prodService.deleteProductById(prodId);
	}
	
	@PutMapping("/product/update/{prodId}")
	public Product updateProdById(@PathVariable int prodId, @RequestPart Product prod, @RequestPart MultipartFile imageFile) {
		return prodService.updateProductById(prodId, prod, imageFile);
	}
	
	@PostMapping(value="/product")
	public ResponseEntity<?> addProduct(@RequestPart(value="prod") Product prod, @RequestPart(value="imageFile") MultipartFile imageFile){
		try {
			Product prod1 = prodService.addProduct(prod, imageFile);
			return new ResponseEntity<>(prod1, HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/product/{prodID}/image")
	public ResponseEntity<byte[]> getImgByProduct(@PathVariable int prodID){
		
		byte[] image = prodService.getImageByProduct(prodID);
		return ResponseEntity.ok().body(image);
	}
	
	@GetMapping("/product/search")
	public List<Product> getProductByKeyword(@RequestParam String keyword){
		return prodService.getProductsByKeyword(keyword);
	}
}
