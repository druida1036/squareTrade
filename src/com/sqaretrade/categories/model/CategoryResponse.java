package com.sqaretrade.categories.model;

/**
 * Model that support the search operation
 * This model contains the level and the Category Node.
 */
public class CategoryResponse {
    private int level;
    private CategoryNode categoryNode;

    public CategoryResponse(int level, CategoryNode categoryNode) {
        this.level = level;
        this.categoryNode = categoryNode;
    }

    public int getLevel() {
        return level;
    }

    public CategoryNode getCategoryNode() {
        return categoryNode;
    }
}
