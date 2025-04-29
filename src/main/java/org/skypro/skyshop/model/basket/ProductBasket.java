package org.skypro.skyshop.model.basket;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductBasket {
    private final Map<UUID, Integer> items = new HashMap<>();

    public void addItem(UUID productId) {
        items.merge(productId, 1, Integer::sum);
    }

    public Map<UUID, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public void clear() {
        items.clear();
    }
}
