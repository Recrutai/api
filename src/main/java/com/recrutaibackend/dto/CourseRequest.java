package com.recrutaibackend.dto;

import java.util.Date;

public record CourseRequest(
        Integer userId,
        String name,
        String description,
        String sender,
        Date conclusion
) {
}
