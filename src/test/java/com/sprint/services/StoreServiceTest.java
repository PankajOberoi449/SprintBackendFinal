package com.sprint.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sprint.dao.StoreRepository;
import com.sprint.model.Store;

public class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreService storeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllStores() {
        // Arrange
        List<Store> stores = new ArrayList<>();
        Store store1 = new Store();
        store1.setStoreId(1);
        store1.setStoreName("Store 1");

        Store store2 = new Store();
        store2.setStoreId(2);
        store2.setStoreName("Store 2");

        stores.add(store1);
        stores.add(store2);

        when(storeRepository.findAll()).thenReturn(stores);

        // Act
        List<Store> result = storeService.getAllStores();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Store 1", result.get(0).getStoreName());
        assertEquals("Store 2", result.get(1).getStoreName());
    }

    @Test
    public void testGetStoreByIdFound() {
        // Arrange
        Store store = new Store();
        store.setStoreId(1);
        store.setStoreName("Store 1");

        when(storeRepository.findById(1)).thenReturn(Optional.of(store));

        // Act
        Store result = storeService.getStoreById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getStoreId());
        assertEquals("Store 1", result.getStoreName());
    }

    @Test
    public void testGetStoreByIdNotFound() {
        // Arrange
        when(storeRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> storeService.getStoreById(1));
        assertEquals("Store not found", exception.getMessage());
    }
}
