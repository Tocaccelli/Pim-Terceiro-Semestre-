package br.com.localfarm.productmovment.interfaces.controllers;

import br.com.localfarm.productmovment.application.services.ProductMovementService;
import br.com.localfarm.productmovment.domain.events.ProductUpdatedEvent;
import br.com.localfarm.productmovment.domain.models.ProductMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@DisplayName("Tests for ProductMovementController")
class ProductMovementControllerTest {

    @InjectMocks
    private ProductMovementController productMovementController;

    @Mock
    private ProductMovementService productMovementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve criar um novo movimento de produto e retornar CREATED")
    void createProductMovement_ShouldReturnCreated() {
        ProductMovement productMovement = new ProductMovement();
        BDDMockito.given(productMovementService.createProductMovement(any(ProductMovement.class)))
                .willReturn(productMovement);

        ResponseEntity<ProductMovement> response = productMovementController.createProductMovement(productMovement);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(productMovementService, times(1)).createProductMovement(productMovement);
    }

    @Test
    @DisplayName("Deve atualizar um movimento de produto e retornar OK")
    void updateProductMovement_ShouldReturnOk() {
        ProductMovement productMovement = new ProductMovement();
        BDDMockito.given(productMovementService.updateProductMovement(anyLong(), any(ProductMovement.class)))
                .willReturn(productMovement);

        ResponseEntity<ProductMovement> response = productMovementController.updateProductMovement(1L, productMovement);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productMovementService, times(1)).updateProductMovement(1L, productMovement);
    }

    @Test
    @DisplayName("Deve retornar NOT_FOUND ao tentar atualizar um movimento de produto inexistente")
    void updateProductMovement_ShouldReturnNotFound() {
        BDDMockito.given(productMovementService.updateProductMovement(anyLong(), any(ProductMovement.class)))
                .willThrow(new IllegalArgumentException("ProductMovement not found"));

        ResponseEntity<ProductMovement> response = productMovementController.updateProductMovement(1L, new ProductMovement());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Deve excluir um movimento de produto e retornar NO_CONTENT")
    void deleteProductMovement_ShouldReturnNoContent() {
        ResponseEntity<Void> response = productMovementController.deleteProductMovement(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productMovementService, times(1)).deleteProductMovement(1L);
    }

    @Test
    @DisplayName("Deve retornar NOT_FOUND ao tentar excluir um movimento de produto inexistente")
    void deleteProductMovement_ShouldReturnNotFound() {
        doThrow(new IllegalArgumentException("ProductMovement not found")).when(productMovementService).deleteProductMovement(anyLong());

        ResponseEntity<Void> response = productMovementController.deleteProductMovement(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Deve retornar todos os movimentos de produto e retornar OK")
    void getAllProductMovements_ShouldReturnOk() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ProductMovement> productMovements = new PageImpl<>(List.of(new ProductMovement()));
        BDDMockito.given(productMovementService.getAllProductMovements(pageable)).willReturn(productMovements);

        ResponseEntity<Page<ProductMovement>> response = productMovementController.getAllProductMovements(pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productMovements, response.getBody());
    }

    @Test
    @DisplayName("Deve retornar um movimento de produto pelo ID e retornar OK")
    void getProductMovementById_ShouldReturnOk() {
        ProductMovement productMovement = new ProductMovement();
        BDDMockito.given(productMovementService.getProductMovementById(anyLong())).willReturn(productMovement);

        ResponseEntity<ProductMovement> response = productMovementController.getProductMovementById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productMovement, response.getBody());
    }

    @Test
    @DisplayName("Deve retornar NOT_FOUND ao tentar buscar um movimento de produto inexistente por ID")
    void getProductMovementById_ShouldReturnNotFound() {
        BDDMockito.given(productMovementService.getProductMovementById(anyLong()))
                .willReturn(null);

        ResponseEntity<ProductMovement> response = productMovementController.getProductMovementById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Deve processar evento de atualização de produto e retornar OK")
    void handleProductUpdatedEvent_ShouldReturnOk() {
        ProductUpdatedEvent event = new ProductUpdatedEvent(100L, "Produto Teste", "PROD100", "Categoria Teste");

        BDDMockito.given(productMovementService.productUpdated()).willReturn((Consumer<ProductUpdatedEvent>) (evt) -> {
            assertEquals(event.getProductId(), evt.getProductId());
            assertEquals(event.getProductName(), evt.getProductName());
            assertEquals(event.getProductCode(), evt.getProductCode());
            assertEquals(event.getCategory(), evt.getCategory());
        });

        ResponseEntity<Void> response = productMovementController.handleProductUpdatedEvent(event);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productMovementService, times(1)).productUpdated();
    }
}