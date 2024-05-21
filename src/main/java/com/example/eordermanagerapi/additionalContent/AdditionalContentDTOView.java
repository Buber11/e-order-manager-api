package com.example.eordermanagerapi.additionalContent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalContentDTOView  implements Serializable {
    private Long contentId;
    private String contentURL;
}
