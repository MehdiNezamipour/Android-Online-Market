package com.nezamipour.mehdi.digikala.util;

import com.nezamipour.mehdi.digikala.data.model.product.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryUtil {

    public static int numberOfBaseCategories(List<Category> categories) {
        int number = 0;
        for (Category category : categories) {
            if (category.getParent() == 0)
                number++;
        }
        return number;
    }

    public static List<Category> parentsCategory(List<Category> categories) {
        List<Category> parentsCategory = new ArrayList<>();
        for (Category category : categories) {
            if (category.getParent() == 0)
                parentsCategory.add(category);
        }
        return parentsCategory;
    }


    public static List<Category> childCategories(List<Category> allCategories, int parentId) {
        List<Category> childCategories = new ArrayList<>();
        for (Category category : allCategories) {
            if (category.getParent().equals(parentId))
                childCategories.add(category);
        }
        return childCategories;
    }


}
