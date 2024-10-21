package br.com.localfarm.product.application.services;

import br.com.localfarm.product.domain.models.Product;
import br.com.localfarm.product.domain.models.dto.Client;
import br.com.localfarm.product.domain.repositories.ProductRepository;
import br.com.localfarm.product.application.exceptions.ProductNotFoundException;
import br.com.localfarm.product.application.exceptions.InvalidProductException;
import br.com.localfarm.product.interfaces.controllers.feign.ClientFeignClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
public class ProductService {

    private final  ProductRepository productRepository;
    private final ClientFeignClient clientFeignClient;

    @Autowired
    public ProductService(ProductRepository productRepository, ClientFeignClient clientFeignClient) {
        this.productRepository = productRepository;
        this.clientFeignClient = clientFeignClient;
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_GERENCIAL')")
    @Transactional
    public Product createProduct(@Valid Product product, Long clientId) {
        // Verifica se o cliente existe antes de criar o produto
        Client client = clientFeignClient.getClientById(clientId);
        if (client == null) {
            throw new InvalidProductException("Client not found with id: " + clientId);
        }

        validateProduct(product);
        product.setId(clientId);
        return productRepository.save(product);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_GERENCIAL')")
    @Transactional
    public Product updateProduct(Long id, @Valid Product product, Long clientId) {
        validateProduct(product);

        // Verifica se o cliente existe antes de atualizar o produto
        Client client = clientFeignClient.getClientById(clientId);
        if (client == null) {
            throw new InvalidProductException("Client not found with id: " + clientId);
        }

        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            product.setId(id);
            product.setId(clientId);
            return productRepository.save(product);
        }
        throw new ProductNotFoundException("Product not found with id: " + id);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_GERENCIAL') or hasRole('ROLE_OPERACIONAL')")
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_GERENCIAL') or hasRole('ROLE_OPERACIONAL')")
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    private void validateProduct(Product product) {
        // Validação do nome do produto
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new InvalidProductException("Product name must not be null or empty");
        }
        if (product.getName().length() > 100) {
            throw new InvalidProductException("Product name must not exceed 100 characters");
        }

        // Validação do código do produto
        if (product.getCode() == null || product.getCode().trim().isEmpty()) {
            throw new InvalidProductException("Product code must not be null or empty");
        }
        if (product.getCode().length() > 50) {
            throw new InvalidProductException("Product code must not exceed 50 characters");
        }

        // Validação da categoria do produto
        if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
            throw new InvalidProductException("Product category must not be null or empty");
        }
        if (product.getCategory().length() > 50) {
            throw new InvalidProductException("Product category must not exceed 50 characters");
        }
    }
}