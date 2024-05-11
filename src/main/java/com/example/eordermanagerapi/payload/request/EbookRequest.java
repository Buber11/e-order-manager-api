package com.example.eordermanagerapi.payload.request;

public record EbookRequest(
        String imagineUrl,
        String title,
        long author,
        Long rating,
        String tag
) {
}
