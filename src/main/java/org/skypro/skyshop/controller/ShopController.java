package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ShopController {
    private final BasketService basketService;
    private final StorageService storageService;

    @Autowired
    public ShopController(BasketService basketService, StorageService storageService) {
        this.basketService = basketService;
        this.storageService = storageService;
    }

    @GetMapping("/basket/add/{productId}")
    public ResponseEntity<String> addProductToBasket(@PathVariable UUID productId) {
        try {
            String result = basketService.addProduct(productId);
            return ResponseEntity.ok(result);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Ошибка сервера: " + e.getMessage()
            );
        }
    }

    @GetMapping("/basket")
    public ResponseEntity<UserBasket> getUserBasket() {
        return ResponseEntity.ok(basketService.getUserBasket());
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(new ArrayList<>(storageService.getAllProducts()));
    }
}

