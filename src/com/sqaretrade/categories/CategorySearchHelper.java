package com.sqaretrade.categories;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

import com.sqaretrade.categories.model.CategoryNode;
import com.sqaretrade.categories.model.CategoryResponse;

public class CategorySearchHelper {


    private CategorySearchHelper() {
    }

    /**
     * This method help to create a Category tree
     * The Category tree created is fixed and should be enhancement to
     * be read from a datasource such as: config file or dataBase
     * @return
     */
    public static CategoryNode createCategories(){

        /**
         *
         * Tree representation for testing
         *                                   Home Appliances
         *
         *            Mayor Appliances             Minor Appliances      Lawn Garden
         *
         *    Kitchen            Appliances
         *
         *
         */

        Set<String> keywords = new HashSet<>();
        keywords.add("Home Appliances");
        CategoryNode  home = new CategoryNode("Home Appliances", keywords);

        keywords = new HashSet<>();
        keywords.add("Mayor Appliances");
        CategoryNode  mayorAppliances = new CategoryNode("Mayor Appliances", keywords);

        keywords = new HashSet<>();
        keywords.add("Minor Appliances");
        CategoryNode  minorAppliances = new CategoryNode("Minor Appliances", keywords);

        keywords = new HashSet<>();
        keywords.add("Lawn Garden");
        CategoryNode  lawnGarden = new CategoryNode("Lawn Garden", keywords);

        home.getSubCategories().add(mayorAppliances);
        home.getSubCategories().add(minorAppliances);
        home.getSubCategories().add(lawnGarden);

        keywords = new HashSet<>();
        keywords.add("Kitchen");
        keywords.add("Oven");
        CategoryNode  kitchen = new CategoryNode("Kitchen", keywords);

        keywords = new HashSet<>();
        CategoryNode  appliances = new CategoryNode("Appliances", keywords);

        mayorAppliances.getSubCategories().add(kitchen);
        mayorAppliances.getSubCategories().add(appliances);

        return home;
    }

    /**
     * Search the keywords associated to the category node that match with category name
     * @param root
     * @param category
     * @return
     */
    public static Set<String> getKeyWordsByCategory(final CategoryNode root, final String category){
        return getNodeByCategory(root, category)
            .map(CategoryResponse::getCategoryNode)
            .map(CategoryNode::getKeywords)
            .orElse(Collections.emptySet());
    }

    /**
     * Calculate the level of a category node that match with the name given
     * @param root
     * @param category
     * @return a positive integer when there is a match otherwise return -1
     */
    public static int getLevelByCategory(final CategoryNode root, final String category){
        return getNodeByCategory(root, category)
            .map(CategoryResponse::getLevel)
            .orElse(-1);
    }

    /**
     * Internal method that search into a categoryTree
     *
     * @param root Category Node used as base node to perform the search
     * @param category Category Name filter
     */
    private static Optional<CategoryResponse> getNodeByCategory(final CategoryNode root, final String category) {
        if (root == null){
            return Optional.empty();
        }

        // this queue stores all subcategories for a given node
        final Queue<CategoryNode> nodes = new LinkedList<>();
        nodes.add(root);
        // level counter
        int level = 0;
        Set<String> parentKeyWords = new HashSet<>();
        while (!nodes.isEmpty()){
            CategoryNode categoryNode = nodes.poll();
            
            if (categoryNode.getName().equals(category)){
                if (categoryNode.getKeywords().size() == 0 ){
                    categoryNode.getKeywords().addAll(parentKeyWords);

                }
                return Optional.of(new CategoryResponse(level, categoryNode));
            }

            if ( categoryNode.getSubCategories().size() > 0){
                parentKeyWords = categoryNode.getKeywords();
                nodes.addAll(categoryNode.getSubCategories());
                level++;
            }
        }
        // Category not found
        return Optional.empty();
    }


}
