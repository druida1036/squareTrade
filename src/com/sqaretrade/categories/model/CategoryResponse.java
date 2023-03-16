package com.sqaretrade.categories.model;

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
