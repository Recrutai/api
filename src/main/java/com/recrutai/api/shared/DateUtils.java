package com.recrutai.api.shared;

import java.time.YearMonth;

public class DateUtils {

    public static int convertYearMonthToNumber(YearMonth yearMonth) {
        return yearMonth.getYear() * 100 + yearMonth.getMonthValue();
    }

    public static YearMonth convertNumberToYearMonth(int number) {
        return YearMonth.of(number / 100, number % 100);
    }

}
