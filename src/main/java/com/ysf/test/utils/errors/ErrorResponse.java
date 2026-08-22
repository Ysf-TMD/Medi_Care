package com.ysf.test.utils.errors;

import java.time.LocalDate;
import java.util.List;

public record ErrorResponse(
        LocalDate timestamp ,
        int status,
        String error ,
        String message ,
        String chemin ,
        List<String> details
) {
}
