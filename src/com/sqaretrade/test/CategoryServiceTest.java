package com.sqaretrade.test;

import java.util.Set;

import com.sqaretrade.categories.model.CategoryNode;
import com.sqaretrade.categories.CategorySearchHelper;

public class CategoryServiceTest {


    public static void main(String[] args) {
        getKeywordsByCategoryTest();
        getKeywordsByCategoryNoKeywordsTest();
        getLevelByCategoryTest();
        getLevelByCategoryNotFoundTest();
    }

    public static void getKeywordsByCategoryTest(){
        CategoryNode root = CategorySearchHelper.createCategories();

        Set<String> kitchenKeywords = CategorySearchHelper.getKeyWordsByCategory(root, "Kitchen");

        assertKeyWords("Kitchen", kitchenKeywords);
        assertKeyWords("Oven", kitchenKeywords);
    }

    public static void getKeywordsByCategoryNoKeywordsTest(){
        CategoryNode root = CategorySearchHelper.createCategories();

        Set<String> kitchenKeywords = CategorySearchHelper.getKeyWordsByCategory(root, "Appliances");

        assertKeyWords("Mayor Appliances", kitchenKeywords);
    }

    public static void getLevelByCategoryTest(){
        CategoryNode root = CategorySearchHelper.createCategories();

        int level = CategorySearchHelper.getLevelByCategory(root, "Kitchen");

        assertLevel(2, level);
    }

    public static void getLevelByCategoryNotFoundTest(){
        CategoryNode root = CategorySearchHelper.createCategories();

        int level = CategorySearchHelper.getLevelByCategory(root, "Kitchen1");

        assertLevel(-1, level);
    }

    private static void assertLevel(int expectedLevel, int level) {
        if (expectedLevel != level){
            System.out.printf("The level should be: %s%n", expectedLevel);
        }
    }

    private static void assertKeyWords(String expectedKeyword, Set<String> keywords) {
        if (!keywords.contains(expectedKeyword)){
            System.out.printf("keywords response should contain %s%n", expectedKeyword);
        }
    }
}
