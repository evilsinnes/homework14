package org.skypro.skyshop.service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.exception.ShopControllerAdvice;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private StorageService storageService;  // Мок хранилища товаров

    @Mock
    private ProductBasket productBasket;    // Мок корзины

    @InjectMocks
    private BasketService basketService;    // Тестируемый сервис

    // Тест 1: Попытка добавить несуществующий товар
    @Test
    public void addProduct_WhenProductNotExists_ThrowsException() {

        UUID fakeId = UUID.randomUUID();
        when(storageService.getProductById(fakeId)).thenReturn(null);


        assertThrows(NoSuchProductException.class, () ->
                basketService.addProduct(fakeId));

        // Убеждаемся, что корзина не модифицировалась
        verifyNoInteractions(productBasket);
    }

    // Тест 2: Успешное добавление товара в корзину
    @Test
    public void addProduct_WhenProductExists_AddsToBasket() {

        UUID productId = UUID.randomUUID();
        Product product = new SimpleProduct(productId, "Яблоко", 100);


        when(storageService.getProductById(productId)).thenReturn(Optional.of(product));


        basketService.addProduct(productId);


        verify(productBasket, times(1)).addItem(productId);
    }

    // Тест 3: Получение пустой корзины
    @Test
    public void getUserBasket_WhenBasketEmpty_ReturnsEmptyList() {

        when(productBasket.getItems()).thenReturn((Map<UUID, Integer>) emptyList());

        var result = basketService.getUserBasket();


        assertTrue(result.getItems().isEmpty());
    }
}
