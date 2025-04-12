package org.skypro.skyshop.model.search;

import net.minidev.json.annotate.JsonIgnore;

import java.util.UUID;

public interface Searchable {
  UUID getId();

  String getName();

  String getSearchTerm();

  String getContentType();

}
