package org.skypro.skyshop.model.article;

import net.minidev.json.annotate.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;
    private final String title;
    private final String text;

    public Article(UUID id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return title;
    }
    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return title + " " + text;
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "ARTICLE";
    }
}