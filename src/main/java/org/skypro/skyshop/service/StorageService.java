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
        // Создаем UUID один раз для каждого продукта
        UUID appleId = UUID.randomUUID();
        UUID bananaId = UUID.randomUUID();

        products.put(appleId, new SimpleProduct(appleId, "Яблоко", 50));
        products.put(bananaId, new DiscountedProduct(bananaId, "Банан", 100, 20));

        // Аналогично для статей
        UUID appleArticleId = UUID.randomUUID();
        UUID bananaArticleId = UUID.randomUUID();

        articles.put(appleArticleId, new Article(appleArticleId,
                "Как выбрать яблоки", "Советы по выбору спелых яблок"));
        articles.put(bananaArticleId, new Article(bananaArticleId,
                "Польза бананов", "О полезных свойствах бананов"));
    }

    public StorageService() {
        initTestData();
        //logProducts();
    }


    public Collection<Searchable> getAllSearchables() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(products.values());
        result.addAll(articles.values());
        return result;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }
    public boolean productExists(UUID id) {
        return products.containsKey(id);
    }
}
