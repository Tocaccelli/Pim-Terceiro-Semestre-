package br.com.localfarm.app.application.services;

import br.com.localfarm.app.domain.models.Product;
import br.com.localfarm.app.infrastructure.persistence.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepositoryImpl productRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public Product updateProduct(Long id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            product.setId(id);
            return productRepository.save(product);
        }
        return null;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}