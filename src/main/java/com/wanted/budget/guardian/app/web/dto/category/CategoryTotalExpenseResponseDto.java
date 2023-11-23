package com.wanted.budget.guardian.app.web.dto.category;

import com.wanted.budget.guardian.app.domain.category.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CategoryTotalExpenseResponseDto {

    private Long categoryId;

    private String value;

    private Long totalExpense;

    public static CategoryTotalExpenseResponseDto of(Category category, Long totalExpense) {
        return new CategoryTotalExpenseResponseDto(
            category.getId(),
            category.getValue(),
            totalExpense
        );
    }

    public static List<CategoryTotalExpenseResponseDto> listOf(
        Map<Category, Long> totalExpenseByCategory) {
        List<CategoryTotalExpenseResponseDto> categoryTotalExpenseResponseDtoList = new ArrayList<>();

        for (Map.Entry<Category, Long> entry : totalExpenseByCategory.entrySet()) {
            Category category = entry.getKey();
            Long totalCategoryExpense = entry.getValue();
            categoryTotalExpenseResponseDtoList.add(
                CategoryTotalExpenseResponseDto.of(
                    category,
                    totalCategoryExpense
                )
            );
        }

        return categoryTotalExpenseResponseDtoList;

    }

}