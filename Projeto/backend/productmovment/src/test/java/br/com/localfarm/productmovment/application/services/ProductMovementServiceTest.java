package br.com.localfarm.productmovment.application.services;

import br.com.localfarm.productmovment.domain.events.ProductUpdatedEvent;
import br.com.localfarm.productmovment.domain.models.ProductMovement;
import br.com.localfarm.productmovment.domain.repositories.ProductMovementRepository;
import br.com.localfarm.productmovment.application.exceptions.ProductMovementNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.BDDMockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@DisplayName("Tests for ProductMovementService")
class ProductMovementServiceTest {

    @Mock
    private ProductMovementRepository productMovementRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ProductMovementService productMovementService;

    private ProductMovement productMovement;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productMovement = new ProductMovement();
        productMovement.setId(1L);
        productMovement.setProductId(100L);
        productMovement.setQuantity(10);
        productMovement.setMovementDate(LocalDate.now().atStartOfDay());
        productMovement.setType("Entry");
        productMovement.setPurpose("Purchase");
    }

    @Test
    @DisplayName("Deve criar um movimento de produto com sucesso")
    void shouldCreateProductMovementSuccessfully() {
        BDDMockito.given(restTemplate.postForObject(anyString(), any(ProductMovement.class), eq(ProductMovement.class)))
                .willReturn(productMovement);
        BDDMockito.given(productMovementRepository.save(any(ProductMovement.class)))
                .willReturn(productMovement);

        ProductMovement createdMovement = productMovementService.createProductMovement(productMovement);

        assertThat(createdMovement).isNotNull();
        verify(productMovementRepository, times(1)).save(createdMovement);
        verify(restTemplate, times(1)).postForObject(anyString(), any(ProductMovement.class), eq(ProductMovement.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar criar um movimento com dados inválidos")
    void shouldThrowExceptionWhenCreatingInvalidProductMovement() {
        productMovement.setQuantity(-1); // Dados inválidos

        assertThatThrownBy(() -> productMovementService.createProductMovement(productMovement))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Quantity must be greater than zero");
    }

    @Test
    @DisplayName("Deve atualizar um movimento de produto com sucesso")
    void shouldUpdateProductMovementSuccessfully() {
        BDDMockito.given(productMovementRepository.findById(1L))
                .willReturn(Optional.of(productMovement));
        BDDMockito.given(productMovementRepository.save(any(ProductMovement.class)))
                .willReturn(productMovement);

        ProductMovement updatedMovement = productMovementService.updateProductMovement(1L, productMovement);

        assertThat(updatedMovement).isNotNull();
        verify(restTemplate, times(1)).put(anyString(), any(ProductMovement.class));
        verify(productMovementRepository, times(1)).save(productMovement);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar um movimento não existente")
    void shouldThrowExceptionWhenUpdatingNonExistentProductMovement() {
        BDDMockito.given(productMovementRepository.findById(1L))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> productMovementService.updateProductMovement(1L, productMovement))
                .isInstanceOf(ProductMovementNotFoundException.class)
                .hasMessageContaining("ProductMovement not found with id");
    }

    @Test
    @DisplayName("Deve deletar um movimento de produto com sucesso")
    void shouldDeleteProductMovementSuccessfully() {
        BDDMockito.given(productMovementRepository.existsById(1L)).willReturn(true);

        productMovementService.deleteProductMovement(1L);

        verify(productMovementRepository, times(1)).deleteById(1L);
        verify(restTemplate, times(1)).delete(anyString());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar um movimento não existente")
    void shouldThrowExceptionWhenDeletingNonExistentProductMovement() {
        BDDMockito.given(productMovementRepository.existsById(1L)).willReturn(false);

        assertThatThrownBy(() -> productMovementService.deleteProductMovement(1L))
                .isInstanceOf(ProductMovementNotFoundException.class)
                .hasMessageContaining("ProductMovement not found with id");
    }

    @Test
    @DisplayName("Deve retornar uma lista paginada de movimentos de produto")
    void shouldReturnPagedListOfProductMovements() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ProductMovement> page = new PageImpl<>(List.of(productMovement));

        BDDMockito.given(productMovementRepository.findAll(pageable)).willReturn(page);

        Page<ProductMovement> result = productMovementService.getAllProductMovements(pageable);

        assertThat(result).isNotEmpty();
        verify(productMovementRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Deve retornar um movimento de produto pelo ID")
    void shouldReturnProductMovementById() {
        BDDMockito.given(productMovementRepository.findById(1L)).willReturn(Optional.of(productMovement));

        ProductMovement foundMovement = productMovementService.getProductMovementById(1L);

        assertThat(foundMovement).isEqualTo(productMovement);
        verify(productMovementRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção quando movimento de produto pelo ID não encontrado")
    void shouldThrowExceptionWhenProductMovementNotFoundById() {
        BDDMockito.given(productMovementRepository.findById(1L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> productMovementService.getProductMovementById(1L))
                .isInstanceOf(ProductMovementNotFoundException.class)
                .hasMessageContaining("ProductMovement not found with id");
    }

    @Test
    @DisplayName("Deve processar evento de atualização de produto e atualizar movimentos")
    void shouldProcessProductUpdatedEventAndUpdateMovements() {
        ProductUpdatedEvent event = new ProductUpdatedEvent(100L, "Produto Teste", "PROD100", "Categoria Teste");

        ProductMovement movementToUpdate = new ProductMovement();
        movementToUpdate.setId(1L);
        movementToUpdate.setProductId(100L);

        BDDMockito.given(productMovementRepository.findByProductId(event.getProductId()))
                .willReturn(List.of(movementToUpdate));
        BDDMockito.given(productMovementRepository.save(any(ProductMovement.class)))
                .willReturn(movementToUpdate);

        Consumer<ProductUpdatedEvent> consumer = productMovementService.productUpdated();
        consumer.accept(event);

        verify(productMovementRepository, times(1)).findByProductId(100L);
        verify(productMovementRepository, times(1)).save(movementToUpdate);
    }
}