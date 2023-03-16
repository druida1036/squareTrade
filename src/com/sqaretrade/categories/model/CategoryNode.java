package com.sqaretrade.categories.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Model class that represent a Category Node
 * Each node can have o or many nodes
 */
public class CategoryNode {
    private String name;
    private Set<String> keywords = new HashSet<>();
    private List<CategoryNode> subCategories = new ArrayList<>();

    public CategoryNode(String key, Set<String> keywords) {
        this.name = key;
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public List<CategoryNode> getSubCategories() {
        return subCategories;
    }
}
