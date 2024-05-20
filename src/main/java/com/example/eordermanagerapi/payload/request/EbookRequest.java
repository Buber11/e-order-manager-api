package com.example.eordermanagerapi.payload.request;

import jakarta.validation.constraints.*;

import java.util.List;

public record EbookRequest(
        @NotBlank(message = "image_url is empty")
        String imageUrl,
        @NotBlank(message = "title is empty")
        String title,
//        @EachPositive(message = "Each author ID must be positive")
        List<Long> author,
        @Min(value = 0,message = "min value is 0")
        @Max(value = 10,message = "max value is 10")
        Long rating,
        @NotBlank(message = "tag is empty")
        String tag,

        List<String> contentURL,
        @NotEmpty(message = "main content is empty")
        String mainContent
) {
}
