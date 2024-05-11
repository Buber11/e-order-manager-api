package com.example.eordermanagerapi.payload.response;

import jakarta.persistence.Column;
import lombok.Builder;

@Builder
public record EbookResposne(
        String imagineUrl,
        String title,
        String author,
        Long rating,
        String tag
) {
}
