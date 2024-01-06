package com.fullcycle.admin.catalogo.application.category.update;

import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.category.CategoryID;

public record UpdateCategoryOutput(
        String id
) {

    public static UpdateCategoryOutput from(final String aCategory) {
        return new UpdateCategoryOutput(aCategory);
    }

    public static UpdateCategoryOutput from(final Category aCategory) {
        return new UpdateCategoryOutput(aCategory.getId().getValue());
    }
}
