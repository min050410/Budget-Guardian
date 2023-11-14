package com.wanted.budget.guardian.common.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListResponse<T> {

    private List<T> list;

    public static <T> ListResponse<T> of(List<T> list) {
        return new ListResponse<>(list);
    }

}
