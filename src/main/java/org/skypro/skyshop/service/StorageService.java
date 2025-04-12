package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> products = new HashMap<>();
    private final Map<UUID, Article> articles = new HashMap<>();

    public Collection<Product> getAllProducts() {
        return Collections.unmodifiableCollection(products.values());
    }

    public Collection<Article> getAllArticles() {
        return Collections.unmodifiableCollection(articles.values());
    }

    private void initTestData() {
        products.put(UUID.randomUUID(), new SimpleProduct(UUID.randomUUID(), "Яблоко", 50));
        products.put(UUID.randomUUID(), new DiscountedProduct(UUID.randomUUID(), "Банан", 100, 20));


        articles.put(UUID.randomUUID(), new Article(UUID.randomUUID(),
                "Как выбрать яблоки", "Советы по выбору спелых яблок"));
        articles.put(UUID.randomUUID(), new Article(UUID.randomUUID(),
                "Польза бананов", "О полезных свойствах бананов"));
    }

    public StorageService() {
        initTestData();
    }


    public Collection<Searchable> getAllSearchables() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(products.values());
        result.addAll(articles.values());
        return result;
    }

}
