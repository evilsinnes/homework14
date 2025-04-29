package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.*;
import org.skypro.skyshop.model.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private static final Logger logger = LoggerFactory.getLogger(BasketService.class);
    private final ProductBasket productBasket;
    private final StorageService storageService;


    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
        logger.info("BasketService initialized");
    }

    public String addProduct(UUID productId) {
        logger.info("Attempting to add product with ID: {}", productId);
        Product product = storageService.getProductById(productId)
                .orElseThrow(() -> {
                    logger.error("Product not found with ID: {}", productId);
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Продукт не найден"
                    );
                });

        productBasket.addItem(product.getId());
        logger.info("Product added to basket: {}", product.getName());
        return product.getName() + " добавлен в корзину";

    }

    public UserBasket getUserBasket() {
        List<BasketItem> basketItems = productBasket.getItems().entrySet().stream()
                .map(entry -> {
                    Product product = storageService.getProductById(entry.getKey())
                            .orElseThrow(() -> new IllegalStateException("Product not found"));
                    return new BasketItem(product, entry.getValue());
                })
                .collect(Collectors.toList());

        return new UserBasket(basketItems);
    }
}
