package br.com.localfarm.product.application.services;

import br.com.localfarm.product.domain.models.Product;
import br.com.localfarm.product.domain.models.dto.Client;
import br.com.localfarm.product.domain.repositories.ProductRepository;
import br.com.localfarm.product.application.exceptions.ProductNotFoundException;
import br.com.localfarm.product.application.exceptions.InvalidProductException;
import br.com.localfarm.product.domain.events.ProductUpdatedEvent;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;
    private final StreamBridge streamBridge;

    @Autowired
    public ProductService(ProductRepository productRepository, RestTemplate restTemplate, StreamBridge streamBridge) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
        this.streamBridge = streamBridge;
    }

    @Transactional
    public Product createProduct(@Valid Product product) {
        validateProduct(product);
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, @Valid Product product) {
        validateProduct(product);

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        // Atualiza o produto existente
        existingProduct.setName(product.getName());
        existingProduct.setCode(product.getCode());
        existingProduct.setCategory(product.getCategory());

        Product updatedProduct = productRepository.save(existingProduct);

        // Envia o evento de atualização do produto
        ProductUpdatedEvent event = new ProductUpdatedEvent(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getCode(),
                updatedProduct.getCategory()
        );
        streamBridge.send("productUpdated-out-0", event);

        // Atualiza o movimento de produto em cascata
        updateProductMovements(updatedProduct);

        return updatedProduct;
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id)
                .or(() -> {
                    throw new ProductNotFoundException("Product not found with id: " + id);
                });
    }

    private void updateProductMovements(Product product) {
        // Chama o serviço de movimento de produtos para atualizar em cascata
        String url = "http://localhost:8084/product-movements/product-updated";
        ProductUpdatedEvent event = new ProductUpdatedEvent(
                product.getId(),
                product.getName(),
                product.getCode(),
                product.getCategory()
        );

        // Envia a atualização para o serviço de movimento de produtos
        restTemplate.postForEntity(url, event, Void.class);
    }

    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new InvalidProductException("Product name must not be null or empty");
        }
        if (product.getName().length() > 100) {
            throw new InvalidProductException("Product name must not exceed 100 characters");
        }

        if (product.getCode() == null || product.getCode().trim().isEmpty()) {
            throw new InvalidProductException("Product code must not be null or empty");
        }
        if (product.getCode().length() > 50) {
            throw new InvalidProductException("Product code must not exceed 50 characters");
        }

        if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
            throw new InvalidProductException("Product category must not be null or empty");
        }
        if (product.getCategory().length() > 50) {
            throw new InvalidProductException("Product category must not exceed 50 characters");
        }
    }
}