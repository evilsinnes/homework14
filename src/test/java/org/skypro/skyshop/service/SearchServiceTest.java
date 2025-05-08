package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.SearchService;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.StorageService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    public void searchWhenQueryIsBlankReturnsEmptyList() {

        Collection<SearchResult> result = searchService.search("  ");

        assertTrue(result.isEmpty());

        verifyNoInteractions(storageService);
    }
    @Test
    public void searchWhenStorageEmptyReturnsEmptyList() {

        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());

        Collection<SearchResult> result = searchService.search("яблоко");

        assertTrue(result.isEmpty());

        verify(storageService, times(1)).getAllSearchables();
    }
    @Test
    public void searchWhenProductMatchesReturnsProduct() {

        UUID productId = UUID.randomUUID();
        Searchable product = new SimpleProduct(productId, "Яблоко", 100);


        when(storageService.getAllSearchables()).thenReturn(List.of(product));


        Collection<SearchResult> result = searchService.search("ябл");


        assertEquals(1, result.size());


        SearchResult foundItem = result.iterator().next();
        assertEquals(productId.toString(), foundItem.getId());
        assertEquals("PRODUCT", foundItem.getContentType());
    }


    @Test
    public void searchWhenArticleMatchesReturnsArticle() {

        UUID articleId = UUID.randomUUID();
        Searchable article = new Article(articleId, "Польза яблок", "Яблоки полезны для здоровья");


        when(storageService.getAllSearchables()).thenReturn(List.of(article));

        Collection<SearchResult> result = searchService.search("здоровья");


        assertEquals(1, result.size());
        assertEquals("ARTICLE", result.iterator().next().getContentType());
    }

}



