package com.example.eordermanagerapi.payload.request;

import jakarta.mail.event.MailEvent;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public record EbookRequest(
        @NotBlank(message = "image_url is empty")
        String imagineUrl,
        @NotBlank(message = "title is empty")
        String title,
        @Positive(message = "author_id have to be positive")
        Long author,
        @Min(value = 0,message = "min value is 0")
        @Max(value = 10,message = "max value is 10")
        Long rating,
        @NotBlank(message = "tag is empty")
        String tag
) {
}
