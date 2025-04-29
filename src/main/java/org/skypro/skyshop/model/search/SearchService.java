package org.skypro.skyshop.model.search;

import org.skypro.skyshop.service.StorageService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }
    public Collection<SearchResult> search(String pattern) {
        if (pattern == null || pattern.isBlank()) {
            return Collections.emptyList();
        }

        String lowerPattern = pattern.toLowerCase();

        return storageService.getAllSearchables().stream()
                .filter(Objects::nonNull)
                .filter(s -> s.getSearchTerm().toLowerCase().contains(lowerPattern))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}


