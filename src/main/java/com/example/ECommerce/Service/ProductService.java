package com.example.ECommerce.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ECommerce.Model.Product;
import com.example.ECommerce.Repository.ProductRepo;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
	
	private ProductRepo repo;
	
	@Autowired
	public ProductService(ProductRepo repo) {
		this.repo = repo;
	}
	
	public List<Product> getAllProducts(){
		return repo.findAll();
	}
	
	public Product getProductById(int prodId) {
		return repo.findById(prodId).orElse(null);
	}
	
	public String deleteProductById(int prodId) {
			repo.deleteById(prodId);
			return "Product deleted successfully...";
	}
	
	public Product updateProductById(int prodId, Product prod, MultipartFile imageFile) {
		Product temp = repo.findById(prodId).orElse(null);
		
		try {
			temp.setImageFile(imageFile.getBytes());
			temp.setFileType(imageFile.getContentType());
			temp.setFileName(imageFile.getName());
			temp.setProductAvailable(prod.isProductAvailable());
			temp.setBrand(prod.getBrand());
			temp.setCategory(prod.getCategory());
			temp.setDescription(prod.getDescription());
			temp.setPrice(prod.getPrice());
			temp.setName((prod.getName()));
			temp.setStockQuantity(prod.getStockQuantity());
			temp.setReleaseDate(prod.getReleaseDate());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		repo.save(temp);
		
		return repo.findById(prodId).orElse(null);
	}
	
	public Product addProduct(Product prod, MultipartFile imageFile) {
		try {
			prod.setImageFile(imageFile.getBytes());
			prod.setFileType(imageFile.getContentType());
			prod.setFileName(imageFile.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return repo.save(prod);		
	}
	
	public byte[] getImageByProduct(int prodId) {
		Product prod1 = repo.findById(prodId).orElse(null);
		byte[] imageFile = prod1.getImageFile();
		
		return imageFile;
	}
	
	@Transactional
	public List<Product> getProductsByKeyword(String keyword){
		return repo.searchProductByKeyword(keyword);
	}
}
