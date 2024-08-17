package com.recrutaibackend.dto;

import java.util.Date;

public record CourseResponse(
        String name,
        String description,
        String sender,
        Date conclusion
) {
}
