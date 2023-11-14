package com.wanted.budget.guardian.app.web.dto.category;

import com.wanted.budget.guardian.app.domain.category.Category;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CategoryResponseDto {

    private Long categoryId;

    private String value;

    public static CategoryResponseDto of(Category category) {
        return new CategoryResponseDto(
            category.getId(),
            category.getValue()
        );
    }

    public static List<CategoryResponseDto> listOf(List<Category> categories) {
        return categories.stream()
            .map(CategoryResponseDto::of)
            .toList();
    }

}
