package com.ecosun.controller;

import com.ecosun.dto.ProductCategoryDTO;
import com.ecosun.dto.response.ProductCategoryResponseDTO;
import com.ecosun.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-categories")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductCategoryController {
	@Autowired
	private ProductCategoryService productCategoryService;

	@GetMapping
	public List<ProductCategoryDTO> getAllProductCategories() {
		return productCategoryService.getAllProductCategories();
	}

	@GetMapping("/{id}")
	public ProductCategoryDTO getProductCategoryById(@PathVariable Long id) {
		return productCategoryService.getProductCategoryById(id);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ProductCategoryDTO createProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) {
		return productCategoryService.createProductCategory(productCategoryDTO);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ProductCategoryDTO updateProductCategory(@PathVariable Long id,
			@RequestBody ProductCategoryDTO productCategoryDTO) {
		return productCategoryService.updateProductCategory(id, productCategoryDTO);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Void> deleteProductCategory(@PathVariable Long id) {
		productCategoryService.deleteProductCategory(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search/{query}")
	public ResponseEntity<?> searchProductCategoryByName(@PathVariable String query) {

		List<ProductCategoryResponseDTO> searchElementByCategoryName = productCategoryService
				.searchElementByCategoryName(query);

		return new ResponseEntity<>(searchElementByCategoryName, HttpStatus.OK);
	}
	
}
