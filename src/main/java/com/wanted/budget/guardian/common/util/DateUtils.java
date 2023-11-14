package com.wanted.budget.guardian.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class DateUtils {

    public Date now() {
        return new Date();
    }

    public Date addTime(Date date, Long second) {
        return new Date(date.getTime() + second);
    }

    public String formatToHyphenSeparatedDate(LocalDateTime createdDate) {
        return createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String formatToDotSeparatedDate(LocalDateTime createdDate) {
        return createdDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }

}
