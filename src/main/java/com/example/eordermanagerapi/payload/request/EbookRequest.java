package com.example.eordermanagerapi.payload.request;

public record EbookRequest(
        String imagineUrl,
        String title,
        String author,
        Long rating,
        String tag
) {
}
