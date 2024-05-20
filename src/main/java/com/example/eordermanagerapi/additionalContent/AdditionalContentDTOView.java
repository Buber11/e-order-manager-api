package com.example.eordermanagerapi.additionalContent;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdditionalContentDTOView {
    private Long contentId;
    private String contentURL;
}
