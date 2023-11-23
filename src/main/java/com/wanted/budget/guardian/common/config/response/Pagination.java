package com.wanted.budget.guardian.common.config.response;

import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

@Getter
@NoArgsConstructor
public class Pagination {

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_SIZE = 10;
    private static final int MIN_SIZE = 100;
    private static final int MAX_SIZE = 100;

    private Integer page;
    private Integer size;
    private Long totalCount;
    private Integer totalPages;

    public static Pagination create(Integer page, Integer size) {
        Pagination pagination = new Pagination();
        pagination.page = checkPage(page);
        pagination.size = checkSize(size);
        return pagination;
    }

    private static Integer checkSize(Integer size) {
        if (Objects.isNull(size) || size < MIN_SIZE || size > MAX_SIZE) {
            return DEFAULT_SIZE;
        }

        return size;
    }

    private static Integer checkPage(Integer page) {
        if (Objects.isNull(page) || page < MIN_SIZE) {
            return DEFAULT_PAGE;
        }

        return page;
    }


    public PageRequest toPageRequest() {
        return PageRequest.of(this.page - 1, this.size);
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

}
