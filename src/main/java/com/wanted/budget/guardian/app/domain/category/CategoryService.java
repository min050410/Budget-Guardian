package com.wanted.budget.guardian.app.domain.category;

import com.wanted.budget.guardian.app.web.dto.category.CategoryResponseDto;
import com.wanted.budget.guardian.common.exception.CategoryNotFoundException;
import com.wanted.budget.guardian.common.response.ListResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * 모든 카테고리 조회
     */
    public ListResponse<CategoryResponseDto> findAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDto> categoryResponseDtos = CategoryResponseDto.listOf(
            categories);

        return ListResponse.of(categoryResponseDtos);
    }

    @Transactional(readOnly = true)
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
            .orElseThrow(CategoryNotFoundException::new);
    }

}
