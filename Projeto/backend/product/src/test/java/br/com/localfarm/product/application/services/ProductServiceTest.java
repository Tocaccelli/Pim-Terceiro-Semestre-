package br.com.localfarm.product.application.services;

import br.com.localfarm.product.domain.models.Product;
import br.com.localfarm.product.domain.repositories.ProductRepository;
import br.com.localfarm.product.application.exceptions.ProductNotFoundException;
import br.com.localfarm.product.application.exceptions.InvalidProductException;
import br.com.localfarm.product.domain.events.ProductUpdatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentCaptor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Tests for ProductService")
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private StreamBridge streamBridge;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Given a valid product, when creating the product, then it should be saved successfully")
    void givenValidProduct_whenCreatingProduct_thenSavesSuccessfully() {
        Product product = new Product(1L, "ProductName", "P123", "Category1");
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.createProduct(product);

        assertEquals(product, savedProduct);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    @DisplayName("Given an invalid product, when creating the product, then it should throw InvalidProductException")
    void givenInvalidProduct_whenCreatingProduct_thenThrowsInvalidProductException() {
        Product product = new Product(1L, "", "P123", "Category1");

        assertThrows(InvalidProductException.class, () -> productService.createProduct(product));
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    @DisplayName("Given an existing product, when updating it, then it should be updated and event sent")
    void givenExistingProduct_whenUpdating_thenUpdatesAndSendsEvent() {
        Product product = new Product(1L, "UpdatedName", "P123", "Category1");
        Product existingProduct = new Product(1L, "OldName", "P123", "Category1");

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        Product updatedProduct = productService.updateProduct(1L, product);

        assertEquals("UpdatedName", updatedProduct.getName());
        verify(productRepository, times(1)).save(existingProduct);
        verify(streamBridge, times(1)).send(eq("productUpdated-out-0"), any(ProductUpdatedEvent.class));
        verify(restTemplate, times(1)).postForEntity(anyString(), any(ProductUpdatedEvent.class), eq(Void.class));
    }

    @Test
    @DisplayName("Given a non-existing product, when updating it, then it should throw ProductNotFoundException")
    void givenNonExistingProduct_whenUpdating_thenThrowsProductNotFoundException() {
        Product product = new Product(1L, "UpdatedName", "P123", "Category1");

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(1L, product));
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    @DisplayName("Given an existing product, when deleting it, then it should be deleted successfully")
    void givenExistingProduct_whenDeleting_thenDeletesSuccessfully() {
        when(productRepository.existsById(1L)).thenReturn(true);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Given a non-existing product, when deleting it, then it should throw ProductNotFoundException")
    void givenNonExistingProduct_whenDeleting_thenThrowsProductNotFoundException() {
        when(productRepository.existsById(1L)).thenReturn(false);

        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(1L));
        verify(productRepository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("Given pageable request, when getting all products, then it should return a page of products")
    void givenPageableRequest_whenGettingAllProducts_thenReturnsPagedProducts() {
        Pageable pageable = PageRequest.of(0, 10);
        Product product1 = new Product(1L, "Product1", "P1", "Category1");
        Product product2 = new Product(2L, "Product2", "P2", "Category2");
        Page<Product> productsPage = new PageImpl<>(Arrays.asList(product1, product2), pageable, 2);

        when(productRepository.findAll(pageable)).thenReturn(productsPage);

        Page<Product> result = productService.getAllProducts(pageable);

        assertEquals(2, result.getTotalElements());
        verify(productRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Given an existing product ID, when getting product by ID, then it should return the product")
    void givenExistingProductId_whenGettingProductById_thenReturnsProduct() {
        Product product = new Product(1L, "ProductName", "P123", "Category1");
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(1L);

        assertTrue(result.isPresent());
        assertEquals(product, result.get());
    }

    @Test
    @DisplayName("Given a non-existing product ID, when getting product by ID, then it should throw ProductNotFoundException")
    void givenNonExistingProductId_whenGettingProductById_thenThrowsProductNotFoundException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(1L));
    }

    @Test
    @DisplayName("Given a product, when updating product movements, then it should call RestTemplate with correct URL")
    void givenProduct_whenUpdatingProductMovements_thenCallsRestTemplate() {
        Product product = new Product(1L, "ProductName", "P123", "Category1");
        ArgumentCaptor<ProductUpdatedEvent> eventCaptor = ArgumentCaptor.forClass(ProductUpdatedEvent.class);

        productService.updateProductMovements(product);

        verify(restTemplate, times(1)).postForEntity(eq("http://localhost:8084/product-movements/product-updated"), eventCaptor.capture(), eq(Void.class));
        ProductUpdatedEvent capturedEvent = eventCaptor.getValue();
        assertEquals(product.getId(), capturedEvent.getProductId());
        assertEquals(product.getName(), capturedEvent.getName());
        assertEquals(product.getCode(), capturedEvent.getCode());
        assertEquals(product.getCategory(), capturedEvent.getCategory());
    }

    @Test
    @DisplayName("Given an invalid product, when validating product, then it should throw InvalidProductException")
    void givenInvalidProduct_whenValidatingProduct_thenThrowsInvalidProductException() {
        Product invalidProduct = new Product(null, "", "", "");

        assertThrows(InvalidProductException.class, () -> productService.validateProduct(invalidProduct));
    }
}