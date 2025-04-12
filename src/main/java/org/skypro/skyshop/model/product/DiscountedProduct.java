package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discount;

    public DiscountedProduct(UUID id, String name, int basePrice, int discount) {
        super(id, name);
        this.basePrice = basePrice;
        this.discount = discount;
    }

    public int getPrice() {
        return basePrice * (100 - discount) / 100;
    }

    @Override
    public String getSearchTerm() {
        return getName();
    }
}