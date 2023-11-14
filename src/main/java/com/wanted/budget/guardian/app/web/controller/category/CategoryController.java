package com.wanted.budget.guardian.app.web.controller.category;

import com.wanted.budget.guardian.app.domain.category.CategoryService;
import com.wanted.budget.guardian.app.web.dto.category.CategoryResponseDto;
import com.wanted.budget.guardian.app.web.path.ApiPath;
import com.wanted.budget.guardian.common.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "카테고리")
@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "카테고리 목록 조회")
    @GetMapping(ApiPath.CATEGORY)
    public ResponseEntity<ListResponse<CategoryResponseDto>> findAllCategory() {
        return ResponseEntity.ok(categoryService.findAllCategory());
    }

}
