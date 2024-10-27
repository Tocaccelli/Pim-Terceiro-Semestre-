package br.com.localfarm.product.interfaces.controllers;

import br.com.localfarm.product.application.exceptions.InvalidProductException;
import br.com.localfarm.product.application.exceptions.ProductNotFoundException;
import br.com.localfarm.product.application.services.ProductService;
import br.com.localfarm.product.domain.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Tests for ProductController")
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Given a valid product, when creating product, then it should return created product")
    void givenValidProduct_whenCreatingProduct_thenReturnsCreatedProduct() {
        Product product = new Product(1L, "ProductName", "P123", "Category1");
        when(productService.createProduct(product)).thenReturn(product);

        ResponseEntity<Product> response = productController.createProduct(product);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).createProduct(product);
    }

    @Test
    @DisplayName("Given an existing product ID, when updating product, then it should return updated product")
    void givenExistingProductId_whenUpdatingProduct_thenReturnsUpdatedProduct() {
        Long productId = 1L;
        Product product = new Product(productId, "UpdatedName", "P123", "Category1");
        when(productService.updateProduct(eq(productId), any(Product.class))).thenReturn(product);

        ResponseEntity<Product> response = productController.updateProduct(productId, product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).updateProduct(productId, product);
    }

    @Test
    @DisplayName("Given a non-existing product ID, when updating product, then it should return 404 Not Found")
    void givenNonExistingProductId_whenUpdatingProduct_thenReturnsNotFound() {
        Long productId = 1L;
        Product product = new Product(productId, "UpdatedName", "P123", "Category1");
        when(productService.updateProduct(eq(productId), any(Product.class))).thenThrow(new ProductNotFoundException("Product not found"));

        ResponseEntity<Product> response = productController.updateProduct(productId, product);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productService, times(1)).updateProduct(productId, product);
    }

    @Test
    @DisplayName("Given an invalid product, when updating product, then it should return 400 Bad Request")
    void givenInvalidProduct_whenUpdatingProduct_thenReturnsBadRequest() {
        Long productId = 1L;
        Product product = new Product(productId, "", "P123", "Category1");
        when(productService.updateProduct(eq(productId), any(Product.class))).thenThrow(new InvalidProductException("Invalid product"));

        ResponseEntity<Product> response = productController.updateProduct(productId, product);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(productService, times(1)).updateProduct(productId, product);
    }

    @Test
    @DisplayName("Given an existing product ID, when deleting product, then it should return 204 No Content")
    void givenExistingProductId_whenDeletingProduct_thenReturnsNoContent() {
        Long productId = 1L;
        doNothing().when(productService).deleteProduct(productId);

        ResponseEntity<Void> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(productId);
    }

    @Test
    @DisplayName("Given a non-existing product ID, when deleting product, then it should return 404 Not Found")
    void givenNonExistingProductId_whenDeletingProduct_thenReturnsNotFound() {
        Long productId = 1L;
        doThrow(new ProductNotFoundException("Product not found")).when(productService).deleteProduct(productId);

        ResponseEntity<Void> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(productId);
    }

    @Test
    @DisplayName("Given pageable request, when getting all products, then it should return paginated products")
    void givenPageableRequest_whenGettingAllProducts_thenReturnsPagedProducts() {
        Pageable pageable = PageRequest.of(0, 10);
        Product product1 = new Product(1L, "Product1", "P1", "Category1");
        Product product2 = new Product(2L, "Product2", "P2", "Category2");
        Page<Product> productsPage = new PageImpl<>(Arrays.asList(product1, product2), pageable, 2);

        when(productService.getAllProducts(pageable)).thenReturn(productsPage);

        ResponseEntity<Page<Product>> response = productController.getAllProducts(pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productsPage, response.getBody());
        verify(productService, times(1)).getAllProducts(pageable);
    }

    @Test
    @DisplayName("Given an existing product ID, when getting product by ID, then it should return the product")
    void givenExistingProductId_whenGettingProductById_thenReturnsProduct() {
        Long productId = 1L;
        Product product = new Product(productId, "ProductName", "P123", "Category1");
        when(productService.getProductById(productId)).thenReturn(Optional.of(product));

        ResponseEntity<Product> response = productController.getProductById(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).getProductById(productId);
    }

    @Test
    @DisplayName("Given a non-existing product ID, when getting product by ID, then it should return 404 Not Found")
    void givenNonExistingProductId_whenGettingProductById_thenReturnsNotFound() {
        Long productId = 1L;
        when(productService.getProductById(productId)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.getProductById(productId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productService, times(1)).getProductById(productId);
    }
}